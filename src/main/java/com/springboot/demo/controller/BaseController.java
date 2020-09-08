package com.springboot.demo.controller;

import com.springboot.demo.service.ArticleService;
import com.springboot.demo.service.CategoryService;
import com.springboot.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 *    基础控制器，包含了Controller层中共有的一些Service
 *
 *
 */
public class BaseController {
    @Autowired
     ArticleService articleService;
    @Autowired
    CommentService commentService;
    @Autowired
    CategoryService categoryService;
}
