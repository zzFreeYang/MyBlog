package com.springboot.demo.service.impl;

import com.github.pagehelper.PageInfo;
import com.springboot.demo.entity.ArticleComment;
import com.springboot.demo.entity.ArticleCommentExample;
import com.springboot.demo.entity.CommentExample;
import com.springboot.demo.dto.ArticleCommentDto;
import com.springboot.demo.entity.Comment;
import com.springboot.demo.mapper.ArticleCommentMapper;
import com.springboot.demo.mapper.CommentMapper;
import com.springboot.demo.service.CommentService;
import com.springboot.demo.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@CacheConfig(cacheNames = "Comment")   //cache的名字
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    ArticleCommentMapper articleCommentMapper;

    /**
     * 增加一条留言信息
     *
     * @param comment
     */
    @Override
    public void addComment(Comment comment) {
        commentMapper.insertSelective(comment);
    }

    /**
     * 增加一条文章评论信息
     * 说明：ArticleCommentDto中封装了tbl_comment和tbl_article_comment中的基础数据
     *
     * @param articleCommentDto
     */
    @Override
    public void addArticleComment(ArticleCommentDto articleCommentDto) {
        // 先增加Comment留言数据
        Comment comment = new Comment();
        comment.setIp(articleCommentDto.getIp());
        comment.setName(articleCommentDto.getName());
        comment.setEmail(articleCommentDto.getEmail());
        comment.setContent(articleCommentDto.getContent());
        comment.setCreateBy(BeanUtil.getNowDate());
        comment.setIsEffective(true);
        addComment(comment);
        // 再更新tbl_article_comment作关联
        CommentExample example = new CommentExample();
        example.setOrderByClause("id desc");
        Long lastestCommentId = commentMapper.selectByExample(example).get(0).getId();
        ArticleComment articleComment = new ArticleComment();
        articleComment.setCommentId(lastestCommentId);
        articleComment.setArticleId(articleCommentDto.getArticleId());
        articleComment.setCreateBy(BeanUtil.getNowDate());
        articleComment.setIsEffective(true);
        articleCommentMapper.insertSelective(articleComment);
    }

    /**
     * 通过留言ID删除一条数据
     * 说明：并不是直接删除数据库中的数据而是直接将isEffective字段置为false
     *
     * @param id
     */
    @Override
    public void deleteCommentById(Long id) {
        Comment comment = commentMapper.selectByPrimaryKey(id);
        comment.setIsEffective(false);
        commentMapper.updateByPrimaryKeySelective(comment);
    }

    /**
     * 删除文章评论信息
     * 说明：说明：并不是直接删除数据库中的数据而是直接将isEffective字段置为false
     * 注意：这里需要设置两个表的字段
     *
     * @param id tbl_article_comment表主键
     */
    @Override
    public void deleteArticleCommentById(Long id) {
        // 设置ArticleComment表中的字段为false
        ArticleComment articleComment = articleCommentMapper.selectByPrimaryKey(id);
        articleComment.setIsEffective(false);
        articleCommentMapper.updateByPrimaryKeySelective(articleComment);
        // 删除Comment表中对应的数据
        deleteCommentById(articleComment.getCommentId());
    }

    /**
     * 列举返回所有的留言信息
     *
     * @return
     */
    @Cacheable     //listAllComment方法的返回，会放到缓存里面去
    @Override
    public List<Comment> listAllComment() {
        // 无条件查询即返回所有
        CommentExample example = new CommentExample();
        return commentMapper.selectByExample(example);
    }

    //CacheEvict注解清除缓存
    @CacheEvict
    @Override
    public void reloadComment(){}


    /**
     * 获取对应文章下的所有评论信息
     * 说明：需要返回一个自己封装好的用来与前端交互的ArticleCommentDto集合
     *
     * @param id 文章ID
     * @return
     */
    @Override
    public List<ArticleCommentDto> listAllArticleCommentById(Long id) {
        List<ArticleCommentDto> comments = new ArrayList<>();
        ArticleCommentExample example = new ArticleCommentExample();
        example.or().andArticleIdEqualTo(id);
        List<ArticleComment> articleComments = articleCommentMapper.selectByExample(example);
        // 填充对应的评论信息
        for (ArticleComment articleComment : articleComments) {
            if (true == ( articleComment.getIsEffective())){
                ArticleCommentDto articleCommentDto = new ArticleCommentDto();
                articleCommentDto.setArticleCommentId(articleComment.getId());
                articleCommentDto.setArticleId(articleComment.getArticleId());
                articleCommentDto.setCreateBy(articleComment.getCreateBy());
                // 查询对应的评论信息
                Comment comment = commentMapper.selectByPrimaryKey(articleComment.getCommentId());
                if(BeanUtil.isNotEmpty(comment)){
                    articleCommentDto.setId(comment.getId());
                    articleCommentDto.setContent(comment.getContent());
                    articleCommentDto.setEmail(comment.getEmail());
                    articleCommentDto.setIp(comment.getIp());
                    articleCommentDto.setName(comment.getName());
                    comments.add(articleCommentDto);
                }
            }
        }
        return comments;
    }

    /**
     * 分页查询所有留言--RowBounds
     * @param rowBounds
     * @return
     */
    @Override
    public List<Comment> findAllCommentWithRowBound(RowBounds rowBounds) {

        log.info("========================== start ================================");
        commentMapper.findAllCommentWithRowBound(new RowBounds(1,3)).forEach(c -> log.info("Page(1) Comment {}",c.getId()));
        log.info("每页三个，取第一页");
        commentMapper.findAllCommentWithRowBound(new RowBounds(3,3)).forEach(c -> log.info("Page(2) Comment {}",c.getId()));
        log.info("每页三个，取第二页");
        commentMapper.findAllCommentWithRowBound(new RowBounds(1,0)).forEach(c -> log.info("Page(1) Comment {}",c.getId()));
        log.info("当limt=0时返回所有");
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        commentMapper.findAllCommentWithParam(1,3).forEach(c->log.info("Page(1) Comment {}",c.getId()));
        List<Comment> list = commentMapper.findAllCommentWithParam(2,3);
        PageInfo page = new PageInfo(list);
        log.info("PageInfo:{}",page);
        log.info("========================== end ================================");


        CommentExample example = new CommentExample();
        return commentMapper.selectByExample(example);
    }

    /**
     *分页查询所有留言--pagenNum
     * @param pagenNum
     * @param pageSize
     * @return
     */
    @Override
    public List<Comment> findAllCommentWithParam(int pagenNum, int pageSize) {
        // 无条件查询即返回所有
        CommentExample example = new CommentExample();
        return commentMapper.selectByExample(example);
    }
}
