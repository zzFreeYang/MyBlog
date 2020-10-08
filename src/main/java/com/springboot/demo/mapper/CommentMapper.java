package com.springboot.demo.mapper;

import com.springboot.demo.entity.CommentExample;
import com.springboot.demo.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    @Select("select * from tbl_article_comment order by id ")
    List<Comment> findAllCommentWithRowBound(RowBounds rowBounds);

    @Select("select * from tbl_article_comment order by id ")
    List<Comment> findAllCommentWithParam(@Param("pageNum") int pagenNum ,
                                          @Param("pageSize")int pageSize);

}
