package com.capi.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.capi.model.Customer;
import com.github.pagehelper.Page;

@Repository
public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);
    
    int delete(Map<String, Object> params);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);
    
    Page<Customer> query(Map<String, Object> params);
    
    List<Date> selectRecordDate();
    
    List<String> selectMediaNumber();
    
    Date selectFirstCreateDate();
    
    Date selectLastCreateDate();

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
}