package com.capi.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.capi.model.User;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> query(Map<String, Object> params);
}