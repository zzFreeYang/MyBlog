package com.springboot.demo.service;

import com.springboot.demo.dto.ArticleCommentDto;
import com.springboot.demo.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * 留言的Service
 */
public interface CommentService {
    void addComment(Comment comment);

    void addArticleComment(ArticleCommentDto articleCommentDto);

    void deleteCommentById(Long id);

    void deleteArticleCommentById(Long id);

    List<Comment> listAllComment();

    List<ArticleCommentDto> listAllArticleCommentById(Long id);

    List<Comment> findAllCommentWithRowBound(RowBounds rowBounds);
    List<Comment> findAllCommentWithParam(@Param("pageNum") int pagenNum ,
                                          @Param("pageSize")int pageSize);

    void  reloadComment();
}
