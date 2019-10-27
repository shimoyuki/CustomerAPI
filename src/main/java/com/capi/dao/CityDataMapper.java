  package com.capi.dao;

import org.springframework.stereotype.Repository;

import com.capi.model.CityData;

@Repository
public interface CityDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CityData record);

    int insertSelective(CityData record);

    CityData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CityData record);

    int updateByPrimaryKey(CityData record);
    
    String selectByCity(String city);
}