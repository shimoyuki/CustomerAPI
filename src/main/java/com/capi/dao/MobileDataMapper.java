package com.capi.dao;

import org.springframework.stereotype.Repository;

import com.capi.model.MobileData;

@Repository
public interface MobileDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MobileData record);

    int insertSelective(MobileData record);

    MobileData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileData record);

    int updateByPrimaryKey(MobileData record);
    
    MobileData selectByNumber(String mobileNumber);
}