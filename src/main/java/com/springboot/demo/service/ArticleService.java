package com.springboot.demo.service;

import com.springboot.demo.dto.ArticleDto;
import com.springboot.demo.dto.ArticleWithPictureDto;
import com.springboot.demo.entity.ArticlePicture;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章Service
 * 说明：ArticleInfo里面封装了picture/content/category等信息
 */
public interface ArticleService {

    void addArticle(ArticleDto articleDto);

    void deleteArticleById(Long id);

    void updateArticle(ArticleDto articleDto);

    void updateArticleCategory(Long articleId, Long categoryId);

    ArticleDto getOneById(Long id);

    ArticlePicture getPictureByArticleId(Long id);

    List<ArticleWithPictureDto> listAll();

    List<ArticleWithPictureDto> listByCategoryId(Long id);

    List<ArticleWithPictureDto> listLastest();
}
