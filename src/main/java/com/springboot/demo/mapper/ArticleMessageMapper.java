package com.springboot.demo.mapper;

import com.springboot.demo.entity.ArticleMessage;
import com.springboot.demo.entity.ArticleMessageExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface ArticleMessageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ArticleMessage record);

    int insertSelective(ArticleMessage record);

    List<ArticleMessage> selectByExample(ArticleMessageExample example);

    ArticleMessage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ArticleMessage record);

    int updateByPrimaryKey(ArticleMessage record);
}