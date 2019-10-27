package com.capi.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.capi.model.Statistics;

@Repository
public interface StatisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Statistics record);

    int insertSelective(Statistics record);

    Statistics selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Statistics record);

    int updateByPrimaryKey(Statistics record);
    
    Statistics selectByAITD(@Param("transDate")  Date transDate, @Param("adsId")  String adsId);
    
    Statistics selectBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("adsId")  String adsId);
}