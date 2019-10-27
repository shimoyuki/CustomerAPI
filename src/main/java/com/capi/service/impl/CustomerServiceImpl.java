package com.capi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capi.dao.CityDataMapper;
import com.capi.dao.CustomerMapper;
import com.capi.dao.MobileDataMapper;
import com.capi.dao.StatisticsMapper;
import com.capi.model.Customer;
import com.capi.model.MobileData;
import com.capi.model.Statistics;
import com.capi.service.CustomerService;
import com.capi.utils.DataUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	CustomerMapper customerDAO;
	@Autowired
	MobileDataMapper mobileDAO;
	@Autowired
	StatisticsMapper statisticsDAO;
	@Autowired
	CityDataMapper cityDAO;
	
	@Override
	public int addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return this.customerDAO.insertSelective(customer);
	}

	@Override
	public int deleteCustomer(int id) {
		// TODO Auto-generated method stub
		return this.customerDAO.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteCustomerBy(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return this.customerDAO.delete(params);
	}

	@Override
	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub
		return this.customerDAO.selectByPrimaryKey(id);
	}

	@Override
	public PageInfo<Customer> getCustomers(Map<String, Object> params) {
		// TODO Auto-generated method stub
		this.startPage(params);
		Page<Customer> customerPage = this.customerDAO.query(params);
		return new PageInfo<>(customerPage);
	}
	
	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return this.getCustomers(null).getList();
	}

	@Override
	public List<Date> getRecordDateList() {
		// TODO Auto-generated method stub
		return this.customerDAO.selectRecordDate();
	}

	@Override
	public List<String> getMediaNumberList() {
		// TODO Auto-generated method stub
		return this.customerDAO.selectMediaNumber();
	}
	
	@Override
	public List<Date> getCreateDateList() {
		// TODO Auto-generated method stub
		List<Date> cdList = new ArrayList<>();
		cdList.add(this.customerDAO.selectFirstCreateDate());
		cdList.add(this.customerDAO.selectLastCreateDate());
		return cdList;
	}

	@Override
	public int updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return this.customerDAO.updateByPrimaryKeySelective(customer);
	}
	
	/**
	 * 启动分页查询
	 * @param params
	 */
	private void startPage(Map<String, Object> params) {
		if (params == null) {
			params = new TreeMap<>();
			params.put("pageSize", 0);
		}
		if (DataUtil.isEmpty(params.get("pageNum"))) {
			params.put("pageNum", 1);
		}
		if (DataUtil.isEmpty(params.get("pageSize"))) {
			params.put("pageSize", 12);
		}
		PageHelper.startPage(params);
	}

	@Override
	public MobileData getMobileData(String mobileNumber) {
		// TODO Auto-generated method stub
		return this.mobileDAO.selectByNumber(mobileNumber);
	}

	@Override
	public int addStatistics(Statistics statistics) {
		// TODO Auto-generated method stub
		Statistics oldData = this.statisticsDAO.selectByAITD(statistics.getTransDate(), statistics.getAdsId());
		if (oldData != null) {
			statistics.setSuccessCount(statistics.getSuccessCount() + oldData.getSuccessCount());
			statistics.setFailCount(statistics.getFailCount() + oldData.getFailCount());
		}
		return this.statisticsDAO.insertSelective(statistics);
	}

	@Override
	public Statistics selectBetween(Date startDate, Date endDate, String adsId) {
		// TODO Auto-generated method stub
		return this.statisticsDAO.selectBetween(startDate, endDate, adsId);
	}

	@Override
	public String getInitial(String city) {
		// TODO Auto-generated method stub
		return this.cityDAO.selectByCity(city);
	}
	
}
