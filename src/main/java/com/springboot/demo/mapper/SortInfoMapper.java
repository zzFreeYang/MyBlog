package com.springboot.demo.mapper;

import com.springboot.demo.entity.SortInfo;
import com.springboot.demo.entity.SortInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface SortInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SortInfo record);

    int insertSelective(SortInfo record);

    List<SortInfo> selectByExample(SortInfoExample example);

    SortInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SortInfo record);

    int updateByPrimaryKey(SortInfo record);
}