package com.example.dao;

import com.example.model.Section;

public interface SectionMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Section record);

    int insertSelective(Section record);

    Section selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Section record);

    int updateByPrimaryKey(Section record);
}