package com.example.dao;

import com.example.model.Reply;

public interface ReplyMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(Reply record);

    int insertSelective(Reply record);

    Reply selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);
}