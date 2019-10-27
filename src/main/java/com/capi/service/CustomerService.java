package com.capi.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.capi.model.Customer;
import com.capi.model.MobileData;
import com.capi.model.Statistics;
import com.github.pagehelper.PageInfo;

public interface CustomerService {
	public int addCustomer(Customer customer);
	
	public int deleteCustomer(int id);
	
	public int deleteCustomerBy(Map<String, Object> params);
	
	public Customer getCustomer(int id);
	
	public PageInfo<Customer> getCustomers(Map<String, Object> params);
	
	public List<Customer> getAllCustomers();
	
	public List<Date> getRecordDateList();
	
	public List<String> getMediaNumberList();
	
	public List<Date> getCreateDateList();
	
	public int updateCustomer(Customer customer);
	
	public MobileData getMobileData(String mobileNumber);
	
	public int addStatistics(Statistics statistics);
	
	public  Statistics selectBetween(Date startDate, Date endDate, String adsId);
	
	public String getInitial(String city);
}
