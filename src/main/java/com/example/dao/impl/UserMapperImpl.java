package com.example.dao.impl;

import com.example.dao.UserMapper;
import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by 讯 on 2017/4/9.
 */
public class UserMapperImpl implements UserMapper {
    @Autowired
    private UserMapper userMapper;


    @Override
    public int deleteByPrimaryKey(Integer uid) {
        return userMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }
}
