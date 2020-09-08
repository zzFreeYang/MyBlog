package com.springboot.demo.service;

import com.springboot.demo.dto.ArticleCommentDto;
import com.springboot.demo.entity.Comment;

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
}
