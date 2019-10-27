package com.capi.controller;


import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.capi.model.Customer;
import com.capi.model.CustomerDTO;
import com.capi.model.MobileData;
import com.capi.model.Statistics;
import com.capi.model.User;
import com.capi.service.CustomerService;
import com.capi.service.UserService;
import com.capi.utils.DataTablePageUtil;
import com.capi.utils.ExcelUtil;
import com.capi.utils.HttpClientUtil;
import com.capi.utils.IpUtil;
import com.capi.utils.MD5Util;
import com.capi.utils.SessionIdUtil;
import com.capi.utils.UnixTimeStampUtil;
import com.github.pagehelper.PageInfo;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getAll")
	@ResponseBody
	public List<Customer> getAllCustomers() {
		//System.out.println(this.customerService.getAllCustomers());
		return this.customerService.getAllCustomers();
	}
	
	@RequestMapping("toImport")
	public String toImportPage(HttpServletRequest request, Model model){
		String username = (String)request.getSession().getAttribute("username");
		Map<String, Object> params = new TreeMap<>();
		params.put("username", username);
		User user = this.userService.getUsers(params).get(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> frdList = new ArrayList<>();
		List<Date> rdList = this.customerService.getRecordDateList();
		Iterator<Date> iterator = rdList.iterator();
		while (iterator.hasNext()) {
			Date date = (Date) iterator.next();
			frdList.add(sdf.format(date));
		}
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> fcdList = new ArrayList<>();
		List<Date> cdList = this.customerService.getCreateDateList();
		iterator = cdList.iterator();
		while (iterator.hasNext()) {
			Date date = (Date) iterator.next();
			if (date != null) {
				fcdList.add(sdf.format(date));
			} else {
				fcdList.add("");
			}
		}
		List<String> mnList = this.customerService.getMediaNumberList();
		
		model.addAttribute("frdList", frdList);
		model.addAttribute("fcdList", fcdList);
		model.addAttribute("mnList", mnList);
		model.addAttribute("user", user);
		return "import";
	}
	
	@RequestMapping("toTransportSY")
	public String toSYTransportPage(HttpServletRequest request, Model model){
		String username = (String)request.getSession().getAttribute("username");
		Map<String, Object> params = new TreeMap<>();
		params.put("username", username);
		User user = this.userService.getUsers(params).get(0);
		List<String> mnList = this.customerService.getMediaNumberList();
		model.addAttribute("mnList", mnList);
		model.addAttribute("user", user);
		return "transportSY";
	}
	
	@RequestMapping("toTransportDR")
	public String toDRTransportPage(HttpServletRequest request, Model model){
		String username = (String)request.getSession().getAttribute("username");
		Map<String, Object> params = new TreeMap<>();
		params.put("username", username);
		User user = this.userService.getUsers(params).get(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> fcdList = new ArrayList<>();
		List<Date> cdList = this.customerService.getCreateDateList();
		Iterator<Date> iterator = cdList.iterator();
		while (iterator.hasNext()) {
			Date date = (Date) iterator.next();
			if (date != null) {
				fcdList.add(sdf.format(date));
			} else {
				fcdList.add("");
			}
		}
		
		model.addAttribute("user", user);
		model.addAttribute("fcdList", fcdList);
		return "transportDR";
	}
	
	@RequestMapping("toTransportPA")
	public String toPATransportPage(HttpServletRequest request, Model model){
		String username = (String)request.getSession().getAttribute("username");
		Map<String, Object> params = new TreeMap<>();
		params.put("username", username);
		User user = this.userService.getUsers(params).get(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<String> fcdList = new ArrayList<>();
		List<Date> cdList = this.customerService.getCreateDateList();
		Iterator<Date> iterator = cdList.iterator();
		while (iterator.hasNext()) {
			Date date = (Date) iterator.next();
			if (date != null) {
				fcdList.add(sdf.format(date));
			} else {
				fcdList.add("");
			}
		}
		
		model.addAttribute("user", user);
		model.addAttribute("fcdList", fcdList);
		return "transportPA";
	}
	
	@RequestMapping("toUpdate")
	public String toUpdatePage(HttpServletRequest request, Model model){
		String username = (String)request.getSession().getAttribute("username");
		Map<String, Object> params = new TreeMap<>();
		params.put("username", username);
		User user = this.userService.getUsers(params).get(0);
		int id = Integer.parseInt(request.getParameter("id"));
		Customer customer = this.customerService.getCustomer(id);
		model.addAttribute("customer", customer);
		model.addAttribute("operation", "customer/update");
		model.addAttribute("user", user);
		return "editor";
	}
	
	@RequestMapping("toAdd")
	public String toAddPage(HttpServletRequest request, Model model){
		String username = (String)request.getSession().getAttribute("username");
		Map<String, Object> params = new TreeMap<>();
		params.put("username", username);
		User user = this.userService.getUsers(params).get(0);
		model.addAttribute("operation", "customer/add");
		model.addAttribute("user", user);
		return "editor";
	}
	
	@RequestMapping("/getPage")
	@ResponseBody
	public DataTablePageUtil<CustomerDTO> getCustomersPage(HttpServletRequest request) {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, null);
		//使用DataTables的属性接收分页数据
		DataTablePageUtil<CustomerDTO> dataTable = new DataTablePageUtil<CustomerDTO>(request);
		//开始分页：PageHelper会处理接下来的第一个查询
		params.put("pageNum", dataTable.getPage_num());//查询页码
		params.put("pageSize", dataTable.getPage_size());//每页条数
		if ("未发送".equals(dataTable.getSearch())) {//查询条件(发送状态)
			params.put("search", "0");
		}else if ("已发送".equals(dataTable.getSearch())) {
			params.put("search", "1");
		}else {
			params.put("search", dataTable.getSearch());
		}
		//查询条件，录入时间、创建时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (request.getParameter("recordDate")!="" && request.getParameter("recordDate")!=null) {
				params.put("recordDate", sdf.parse(request.getParameter("recordDate")));
			}else {
				params.remove("recordDate");
			}
			if (request.getParameter("startCDate")!="" && request.getParameter("startCDate")!=null) {
				params.put("startCDate", sdf.parse(request.getParameter("startCDate")));
			}else {
				params.remove("startCDate");
			}
			if (request.getParameter("endCDate")!="" && request.getParameter("endCDate")!=null) {
				params.put("endCDate", sdf.parse(request.getParameter("endCDate")));
			}else {
				params.remove("endCDate");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//查询条件，地区
		if (request.getParameter("region") != "" && request.getParameter("region") != null) {
			String region = request.getParameter("region");
			if (region.contains("-")) {
				params.put("exceptRegion", region.replace("-", ""));
				params.remove("region");
			} else {
				params.put("region", region);
			}
		} else {
			params.remove("region");
		}
		//查询条件，媒体号
		if (request.getParameter("mediaNumber")!="" && request.getParameter("mediaNumber")!=null) {
			params.put("mediaNumber", request.getParameter("mediaNumber"));
		}else {
			params.remove("mediaNumber");
		}
		params.put("dir", request.getParameter("order[0][dir]"));
		int order = Integer.parseInt(request.getParameter("order[0][column]"));
		switch (order) {
		case 1:
			params.put("order", "name");
			break;
		case 2:
			params.put("order", "phone");
			break;
		case 3:
			params.put("order", "region");
			break;
		case 4:
			params.put("order", "gender");
			break;
		case 5:
			params.put("order", "birthday");
			break;
		case 6:
			params.put("order", "monthly_income");
			break;
		case 7:
			params.put("order", "career_type");
			break;
		case 8:
			params.put("order", "is_has_fund");
			break;
		case 9:
			params.put("order", "is_has_security");
			break;
		case 10:
			params.put("order", "payroll_form");
			break;
		case 11:
			params.put("order", "is_had_credit");
			break;
		case 12:
			params.put("order", "is_had_overdue");
			break;
		case 13:
			params.put("order", "residence_time");
			break;
		case 14:
			params.put("order", "house_property");
			break;
		case 15:
			params.put("order", "car_property");
			break;
		case 16:
			params.put("order", "insurance_policy");
			break;
		case 17:
			params.put("order", "demand_account");
			break;
		case 18:
			params.put("order", "media_number");
			break;
		case 19:
			params.put("order", "create_date");
			break;
		default:
			params.put("order", "transport_mark");
			break;
		}
		System.out.println(params);
		PageInfo<Customer> pageInfo = null;
		List<CustomerDTO> customerList = new ArrayList<>();
		pageInfo = this.customerService.getCustomers(params);
		Iterator<Customer> iterator = pageInfo.getList().iterator();
		while (iterator.hasNext()) {
			Customer customer = (Customer) iterator.next();
			customerList.add(new CustomerDTO(customer));
		}
		//封装数据给DataTables
		dataTable.setDraw(dataTable.getDraw());
		dataTable.setData(customerList);  
		dataTable.setRecordsTotal((int)pageInfo.getTotal());  
		dataTable.setRecordsFiltered(dataTable.getRecordsTotal()); 
		//返回数据到页面
		//JSONObject jsonObject = JSONObject.fromObject(dataTable);
		//System.out.println(pageInfo.getList().size());
		//model.addAttribute("pageInfo", pageInfo);
		return dataTable;
	}
	
	@RequestMapping("update")
	public String updateCustomer(Customer customer) {
		if (customerService.updateCustomer(customer) < 1) {
			System.out.println("修改失败！");
		}
		return "redirect:toImport";
	}
	
	@RequestMapping("updateSelect")
	@ResponseBody
	public String updateSelectCustomer(String select) {
		if (select == null  || "".equals(select)) {
			return "{\"result\":\"error\"}";
		}
		String[] ids = select.split(",");
		Customer customer = new Customer();
		for (int i = 0; i < ids.length; i++) {
			customer.setId(Integer.parseInt(ids[i]));
			customer.setTransportMark("0");
			if (customerService.updateCustomer(customer) < 1) {
				System.out.println("修改失败！");
				return "{\"result\":\"error\"}";
			}
		}
		return "{\"result\":\"success\"}";
	}
	
	@RequestMapping("add")
	public String addCustomer(Customer customer) {
		//System.out.println(customer);
		if (customer.getPhone() == "" || customer.getName() == "") {
			return "redirect:toImport";
		}
		MobileData md = customerService.getMobileData(customer.getPhone().substring(0,7));
		if (md != null && customer.getRegion() == "") {
			customer.setRegion(md.getProvince()+"|"+md.getCity());
		}
		customer.setCreateDate(Calendar.getInstance().getTime());
		customer.setRecordDate(Calendar.getInstance().getTime());
		customer.setTransportMark("0");
		if (customerService.addCustomer(customer) < 1) {
			System.out.println("添加失败！");
		}
		return "redirect:toImport";
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public String deleteCustomer(Integer id) {
		if (customerService.deleteCustomer(id) < 1) {
			System.out.println("删除失败！");
			return "{\"result\":\"error\"}";
		}
		return "{\"result\":\"success\"}";
	}
	
	@RequestMapping("deleteSelect")
	@ResponseBody
	public String deleteSelectCustomer(String select) {
		if (select == null  || "".equals(select)) {
			return "{\"result\":\"error\"}";
		}
		String[] ids = select.split(",");
		for (int i = 0; i < ids.length; i++) {
			if (customerService.deleteCustomer(Integer.parseInt(ids[i])) < 1) {
				System.out.println("删除失败！");
				return "{\"result\":\"error\"}";
			}
		}
		return "{\"result\":\"success\"}";
	}
	
	@RequestMapping("deleteBy")
	@ResponseBody
	public String deleteCustomerBy(HttpServletRequest request) throws ParseException {
		Map<String, Object> params = WebUtils.getParametersStartingWith(request, null);
		// 查询条件，录入时间、创建时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (request.getParameter("recordDate") != "" && request.getParameter("recordDate") != null) {
				params.put("recordDate", sdf.parse(request.getParameter("recordDate")));
			} else {
				params.remove("recordDate");
			}
			if (request.getParameter("startCDate")!="" && request.getParameter("startCDate")!=null) {
				params.put("startCDate", sdf.parse(request.getParameter("startCDate")));
			}else {
				params.remove("startCDate");
			}
			if (request.getParameter("endCDate")!="" && request.getParameter("endCDate")!=null) {
				params.put("endCDate", sdf.parse(request.getParameter("endCDate")));
			}else {
				params.remove("endCDate");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 查询条件，地区
		if (request.getParameter("region") != "" && request.getParameter("region") != null) {
			String region = request.getParameter("region");
			if (region.contains("-")) {
				params.put("exceptRegion", region.replace("-", ""));
				params.remove("region");
			} else {
				params.put("region", region);
			}
		} else {
			params.remove("region");
		}
		// 查询条件，媒体号
		if (request.getParameter("mediaNumber") != "" && request.getParameter("mediaNumber") != null) {
			params.put("mediaNumber", request.getParameter("mediaNumber"));
		} else {
			params.remove("mediaNumber");
		}
		if (this.customerService.deleteCustomerBy(params) < 1) {
			return "{\"result\":\"error\"}";
		}
		return "{\"result\":\"success\"}";
	}
	
	@RequestMapping("/upload1")
	@ResponseBody
	public String uploadFile1(@RequestParam("uploadFile") MultipartFile uploadFile){
		String result = "{\"result\":\"success\"}";
		try {
			List<Object> customerList = ExcelUtil.read(uploadFile, Customer.class, "region,recordDate,transportMark");
			Iterator<Object> iterator = customerList.iterator();
			int count = 0;
			MobileData md = null;
			Date recordDate = Calendar.getInstance().getTime();
			while (iterator.hasNext()) {
				Customer object = (Customer) iterator.next();
				md = customerService.getMobileData(object.getPhone().substring(0,7));
				//System.out.println(object);
				//System.out.println(md);
				if (md != null) {
					object.setRegion(md.getProvince()+"|"+md.getCity());
				}
				object.setRecordDate(recordDate);
				object.setTransportMark("0");
				if (object.getCreateDate() == null) {
					object.setCreateDate(recordDate);
				}
				System.out.print("录入数据"+object.getPhone()+"……                  ");
				int row = customerService.addCustomer(object);
				if (row >1) {
					System.out.println("已存在");
				}else if (row < 1) {
					System.out.println("失败");
				}else {
					System.out.println("成功");
				}
				count += row;
			}
			if (count < customerList.size()) {
				result = "{\"result\":\"error\"}";
			}else if (count > customerList.size()) {
				result = "{\"result\":\"exist\"}";
			}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/upload2")
	@ResponseBody
	public String uploadFile2(@RequestParam("uploadFile2") MultipartFile uploadFile2){
		String result = "{\"result\":\"success\"}";
		try {
			List<Object> customerList = ExcelUtil.read(uploadFile2, Customer.class, "recordDate,transportMark");
			Iterator<Object> iterator = customerList.iterator();
			int count = 0;
			Date recordDate = Calendar.getInstance().getTime();
			while (iterator.hasNext()) {
				Customer object = (Customer) iterator.next();
				object.setRecordDate(recordDate);
				object.setTransportMark("0");
				if (object.getCreateDate() == null) {
					object.setCreateDate(recordDate);
				}
				System.out.print("录入数据"+object.getPhone()+"……                  ");
				int row = customerService.addCustomer(object);
				if (row >1) {
					System.out.println("已存在");
				}else if (row < 1) {
					System.out.println("失败");
				}else {
					System.out.println("成功");
				}
				count += row;
			}
			if (count < customerList.size()) {
				result = "{\"result\":\"error\"}";
			}else if (count > customerList.size()) {
				result = "{\"result\":\"exist\"}";
			}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/transportSY")
	@ResponseBody
	public String transportDataToSY(HttpServletRequest request) throws Exception{
		String username = (String)request.getSession().getAttribute("username");
		if (username == null) {//登录状态失效
			return "{\"result\":\"lack\"}";
		}
		Map<String, Object> params = new TreeMap<>();
		params.put("username", username);
		User user = this.userService.getUsers(params).get(0);
		if (user.getChannelId() == null || (user.getAdsId1() == null && user.getAdsId2() == null)) {//渠道信息不完整
			return "{\"result\":\"lack\"}";
		}
		HttpClientUtil httpClientUtil = new HttpClientUtil(user.getDestUrl()+"?ChannelId=" + user.getChannelId() + "&AdsId=" + request.getParameter("adsId"));
		//HttpClientUtil httpClientUtil = new HttpClientUtil(user.getDestUrl()+"?ChannelId=&AdsId=");
		System.out.println("目标地址："+user.getDestUrl()+"?ChannelId=" + user.getChannelId() + "&AdsId=" + request.getParameter("adsId"));
		JSONObject obj = null;
		String responseData = null;
		int failedCount = 0,//发送失败计数
				transCount = 0,//发送成功计数
				number = 0;//发送数据总条数
		if (request.getParameter("number") != "" && request.getParameter("number") != null) {
			number = Integer.parseInt(request.getParameter("number"));
		}
		params = new TreeMap<>();//发送数据的查询条件
		params.put("pageSize", 0);//取消分页查询
		params.put("transportMark", "0");//未发送的数据
		// 查询条件，地区
		if (request.getParameter("region") != "" && request.getParameter("region") != null) {
			String region = request.getParameter("region");
			if (region.contains("-")) {
				params.put("exceptRegion", region.replace("-", ""));
			} else {
				params.put("region", region);
			}
		}
		// 查询条件，媒体号
		if (request.getParameter("mediaNumber") != "" && request.getParameter("mediaNumber") != null) {
			params.put("mediaNumber", request.getParameter("mediaNumber"));
		}
		List<Customer> customerList = this.customerService.getCustomers(params).getList();
		Iterator<Customer> iterator = customerList.iterator();
		while (iterator.hasNext()) {//修改格式并发送
			if (transCount >= number) {//达到成功发送条数，退出循环
				break;
			}
			if ((transCount + failedCount + 1)%300 == 0) {//每发送300条数据重连
				httpClientUtil.closeClient();
				httpClientUtil.openClient();
			}
			Customer customer = (Customer) iterator.next();
			obj = new JSONObject();
			obj.put("Name", customer.getName());
			obj.put("Phone", customer.getPhone());
			obj.put("RecentCredit", 2);
			obj.put("IsAcceptRecommend", 1);
			obj.put("IsAddYwx", 0);
			obj.put("Sex", customer.getGender()==1?"M":"F");
			BigDecimal da = customer.getDemandAccount();
			if (da != null && da.doubleValue() >= 10000 && da.doubleValue() <= 500000) {
				obj.put("LoanAmount", da.divide(new BigDecimal(10000)));
			}else {
				obj.put("LoanAmount", new BigDecimal(5));
			}
			obj.put("HouseProperty", customer.getHouseProperty() + 1);
			obj.put("CarProperty", customer.getCarProperty() + 1);
			obj.put("IsHasBx", customer.getInsurancePolicy() == 0 ? 0 : 1);
			obj.put("ShebaoOrGongjijin", 2);
			obj.put("Ip", IpUtil.getRandomIp());
			obj.put("ProdIdArrayString", "*");
			obj.put("Cert", MD5Util.getMD5(user.getChannelId()+"_"+request.getParameter("adsId")+"_"+customer.getPhone()).toUpperCase());
			System.out.println("发送数据："+obj);
			responseData = httpClientUtil.post("application/x-www-form-urlencoded", obj.toString());
			obj = JSONObject.fromObject(responseData);
			@SuppressWarnings("rawtypes")
			Map map = (Map) obj;
			System.out.println("返回结果："+map);
			if (!"0".equals(map.get("Ret").toString())) {
				failedCount ++;
				customer.setTransportMark(map.get("Msg").toString());
				this.customerService.updateCustomer(customer);
			}else {
				transCount++;
				customer.setTransportMark("1");
				this.customerService.updateCustomer(customer);
			}
		}
		httpClientUtil.closeClient();//关闭连接
		Statistics statistics = new Statistics(transCount, failedCount, request.getParameter("adsId"), Calendar.getInstance().getTime());
		this.customerService.addStatistics(statistics);
		if (failedCount > 0 || transCount == 0) {
			return "{\"result\":\"error\", \"trans\":"+transCount+",\"failed\":"+failedCount+",\"number\":"+customerList.size()+"}";
		}
		return "{\"result\":\"success\", \"trans\":"+transCount+",\"number\":"+customerList.size()+"}";
	}
	
	@RequestMapping("/transpartSY")
	@ResponseBody
	public String transportSelectedDataToSY(HttpServletRequest request) throws Exception{
		String username = (String)request.getSession().getAttribute("username");
		if (username == null) {//登录状态失效
			return "{\"result\":\"lack\"}";
		}
		Map<String, Object> params = new TreeMap<>();
		params.put("username", username);
		User user = this.userService.getUsers(params).get(0);
		if (user.getChannelId() == null || (user.getAdsId1() == null && user.getAdsId2() == null)) {//渠道信息不完整
			return "{\"result\":\"lack\"}";
		}
		String select = request.getParameter("select");
		if (select == null  || "".equals(select)) {
			return "{\"result\":\"error\"}";
		}
		System.out.println(select);
		String[] ids = select.split(",");
		
		HttpClientUtil httpClientUtil = new HttpClientUtil(user.getDestUrl()+"?ChannelId=" + user.getChannelId() + "&AdsId=" + request.getParameter("adsId"));
		System.out.println("目标地址："+user.getDestUrl()+"?ChannelId=" + user.getChannelId() + "&AdsId=" + request.getParameter("adsId"));
		JSONObject obj = null;
		String responseData = null;
		int failedCount = 0,//发送失败计数
		transCount = 0;//发送成功计数
		List<Customer> customerList = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			customerList.add(this.customerService.getCustomer(Integer.parseInt(ids[i])));
		}
		Iterator<Customer> iterator = customerList.iterator();
		while (iterator.hasNext()) {//修改格式并发送
			Customer customer = (Customer) iterator.next();
			if (!"0".equals(customer.getTransportMark())) {//过滤已发送数据
				continue;
			}
			if ((transCount + failedCount + 1)%300 == 0) {//每发送300条数据重连
				httpClientUtil.closeClient();
				httpClientUtil.openClient();
			}
			obj = new JSONObject();
			obj.put("Name", customer.getName());
			obj.put("Phone", customer.getPhone());
			obj.put("RecentCredit", 2);
			obj.put("IsAcceptRecommend", 1);
			obj.put("IsAddYwx", 0);
			obj.put("Sex", customer.getGender()==1?"M":"F");
			BigDecimal da = customer.getDemandAccount();
			if (da != null && da.doubleValue() >= 10000 && da.doubleValue() <= 500000) {
				obj.put("LoanAmount", da.divide(new BigDecimal(10000)));
			}else {
				obj.put("LoanAmount", new BigDecimal(5));
			}
			obj.put("HouseProperty", customer.getHouseProperty() + 1);
			obj.put("CarProperty", customer.getCarProperty() + 1);
			obj.put("IsHasBx", customer.getInsurancePolicy() == 0 ? 0 : 1);
			obj.put("ShebaoOrGongjijin", 2);
			obj.put("Ip", IpUtil.getRandomIp());
			obj.put("ProdIdArrayString", "*");
			obj.put("Cert", MD5Util.getMD5(user.getChannelId()+"_"+request.getParameter("adsId")+"_"+customer.getPhone()).toUpperCase());
			System.out.println("发送数据："+obj);
			responseData = httpClientUtil.post("application/x-www-form-urlencoded", obj.toString());
			obj = JSONObject.fromObject(responseData);
			@SuppressWarnings("rawtypes")
			Map map = (Map) obj;
			System.out.println("返回结果："+map);
			if (!"0".equals(map.get("Ret").toString())) {
				failedCount ++;
				customer.setTransportMark(map.get("Msg").toString());
				this.customerService.updateCustomer(customer);
			}else {
				transCount++;
				customer.setTransportMark("1");
				this.customerService.updateCustomer(customer);
			}
		}
		httpClientUtil.closeClient();//关闭连接
		Statistics statistics = new Statistics(transCount, failedCount, request.getParameter("adsId"), Calendar.getInstance().getTime());
		this.customerService.addStatistics(statistics);
		if (failedCount > 0 || transCount == 0) {
			return "{\"result\":\"error\", \"trans\":"+transCount+",\"failed\":"+failedCount+"}";
		}
		return "{\"result\":\"success\", \"trans\":"+transCount+"}";
	}
	
	@RequestMapping("/getStatisticsSY")
	@ResponseBody
	public String getStatisticsSY(HttpServletRequest request) {
		String result1="",result2="";
		String username = (String)request.getSession().getAttribute("username");
		if (username == null) {//登录状态失效
			return "{\"result\":\"lack\"}";
		}
		Map<String, Object> params = new TreeMap<>();
		params.put("username", username);
		User user = this.userService.getUsers(params).get(0);
		if (user.getChannelId() == null || (user.getAdsId1() == null && user.getAdsId2() == null)) {//渠道信息不完整
			return "{\"result\":\"lack\"}";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null, endDate = null;
		try {
			if (request.getParameter("startDate")!="" && request.getParameter("startDate")!=null) {
				startDate = sdf.parse(request.getParameter("startDate"));
			}
			if (request.getParameter("endDate")!="" && request.getParameter("endDate")!=null) {
				endDate = sdf.parse(request.getParameter("endDate"));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (startDate != null && endDate != null) {
			Statistics statistics1 = this.customerService.selectBetween(startDate, endDate, user.getAdsId1()),
					statistics2 = this.customerService.selectBetween(startDate, endDate, user.getAdsId2());
			if (statistics1 != null) {
				result1 =  ",\"successCount1\":" + statistics1.getSuccessCount() + ",\"failCount1\":"
						+ statistics1.getFailCount() + ",\"adsId1\":" + user.getAdsId1();
			}
			if (statistics2 != null) {
				result2 = ",\"successCount2\":" + statistics2.getSuccessCount()
				+ ",\"failCount2\":" + statistics2.getFailCount()  + ",\"adsId2\":" + user.getAdsId2();
			}
		}
		if ("".equals(result1+result2)) {
			return  "{\"result\":\"error\"}";
		}
		return "{\"result\":\"success\""+result1+result2+"}";
	}

	@RequestMapping("/transportDR")
	@ResponseBody
	public String transportDataToDR(HttpServletRequest request) throws Exception {
		String username = (String) request.getSession().getAttribute("username");
		if (username == null) {// 登录状态失效
			return "{\"result\":\"lack\"}";
		}
		
		//HttpClientUtil httpClientUtil = new HttpClientUtil("http://103.242.169.60:19999/rzr/Transfer/Register");
		HttpClientUtil httpClientUtil = new HttpClientUtil("http://mirzr.rongzi.com/rzr/Transfer/Register");
		JSONObject obj = null;
		String responseData = null;
		int failedCount = 0, // 发送失败计数
				transCount = 0, // 发送成功计数
				number = 0;// 发送数据总条数
		if (request.getParameter("number") != "" && request.getParameter("number") != null) {
			number = Integer.parseInt(request.getParameter("number"));
		}
		
		Map<String, Object> params = new TreeMap<>();// 发送数据的查询条件
		params.put("pageSize", 0);// 取消分页查询
		params.put("transportMark", "0");// 未发送的数据
		// 查询条件，地区
		if (request.getParameter("region") != "" && request.getParameter("region") != null) {
			String region = request.getParameter("region");
			if (region.contains("-")) {
				params.put("exceptRegion", region.replace("-", ""));
			} else {
				params.put("region", region);
			}
		}
		//查询条件，创建时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (request.getParameter("startCDate")!="" && request.getParameter("startCDate")!=null) {
			params.put("startCDate", sdf.parse(request.getParameter("startCDate")));
		}
		if (request.getParameter("endCDate")!="" && request.getParameter("endCDate")!=null) {
			params.put("endCDate", sdf.parse(request.getParameter("endCDate")));
		}
		List<Customer> customerList = this.customerService.getCustomers(params).getList();
		System.out.println("****************************************************");
		System.out.println(customerList.size());
		Iterator<Customer> iterator = customerList.iterator();
		while (iterator.hasNext()) {// 修改格式并发送
			if (transCount >= number) {// 达到成功发送条数，退出循环
				break;
			}
			if ((transCount + failedCount + 1) % 300 == 0) {// 每发送300条数据重连
				httpClientUtil.closeClient();
				httpClientUtil.openClient();
			}
			
			Customer customer = (Customer) iterator.next();
			obj = new JSONObject();
			String phone = customer.getPhone(),
					region = customer.getRegion();
			if (region != null) {
				region = region.substring(region.indexOf("|") + 1);
				region = this.customerService.getInitial(region);
			}
			if(region == null){
				region = "CHENGDU";
			}
			int loanAmount = 0;
			if(customer.getDemandAccount() != null) {
				loanAmount = customer.getDemandAccount().intValue()/10000;
			}
			if (loanAmount <1) {
				loanAmount = 5;
			}
			String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()),
					signature = MD5Util.getMD5(region+phone +customer.getName()+customer.getGender()+loanAmount+289+ timestamp + "rongzi.com_8763");
			obj.put("CityName", region);
			obj.put("CellPhoneNumber", phone);
			obj.put("RealName", customer.getName());
			obj.put("Gender", customer.getGender());
			obj.put("LoanAmount", loanAmount);
			obj.put("UtmSource", 289);
			obj.put("TimeStamp", timestamp);
			obj.put("Signature", signature);
			
			System.out.println("发送注册数据：" + obj);
			responseData = httpClientUtil.post("application/json", obj.toString());
			obj = JSONObject.fromObject(responseData);
			@SuppressWarnings("rawtypes")
			Map map = (Map) obj;
			System.out.println("注册结果：" + map);
			if ("0".equals(map.get("Code").toString())) {//发送成功
				if (Boolean.FALSE.equals(map.get("IsRegistered"))) {//注册成功
					transCount++;
					customer.setTransportMark("1");
				} else {
					failedCount++;
					customer.setTransportMark("手机号已注册");
				}
				this.customerService.updateCustomer(customer);
			} else {
				System.out.println("注册请求失败，错误代码：" + map.get("Code"));
				return "注册请求失败，错误代码：" + map.get("Code");
			}
		}
		httpClientUtil.closeClient();// 关闭连接
		
		Statistics statistics = new Statistics(transCount, failedCount, "289", Calendar.getInstance().getTime());
		this.customerService.addStatistics(statistics);
		if (failedCount > 0 || transCount == 0) {
			return "{\"result\":\"error\", \"trans\":" + transCount + ",\"failed\":" + failedCount + ",\"number\":"
					+ customerList.size() + "}";
		}
		return "{\"result\":\"success\", \"trans\":" + transCount + ",\"number\":" + customerList.size() + "}";
	}

	@RequestMapping("/transpartDR")
	@ResponseBody
	public String transportSelectedDataToDR(HttpServletRequest request) throws Exception {
		String username = (String) request.getSession().getAttribute("username");
		if (username == null) {// 登录状态失效
			return "{\"result\":\"lack\"}";
		}
		
		String select = request.getParameter("select");
		if (select == null || "".equals(select)) {
			return "{\"result\":\"error\"}";
		}
		System.out.println(select);
		String[] ids = select.split(",");

		//HttpClientUtil httpClientUtil = new HttpClientUtil("http://103.242.169.60:19999/rzr/Transfer/Register");
		HttpClientUtil httpClientUtil = new HttpClientUtil("http://mirzr.rongzi.com/rzr/Transfer/Register");
		JSONObject obj = null;
		String responseData = null;
		int failedCount = 0, // 发送失败计数
				transCount = 0;// 发送成功计数
		List<Customer> customerList = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			customerList.add(this.customerService.getCustomer(Integer.parseInt(ids[i])));
		}
		
		Iterator<Customer> iterator = customerList.iterator();
		while (iterator.hasNext()) {// 修改格式并发送
			Customer customer = (Customer) iterator.next();
			if (!"0".equals(customer.getTransportMark())) {// 过滤已发送数据
				continue;
			}
			if ((transCount + failedCount + 1) % 300 == 0) {// 每发送300条数据重连
				httpClientUtil.closeClient();
				httpClientUtil.openClient();
			}

			obj = new JSONObject();
			String phone = customer.getPhone(),
					region = customer.getRegion();
			if (region != null) {
				region = region.substring(region.indexOf("|") + 1);
				region = this.customerService.getInitial(region);
			}
			if(region == null){
				region = "CHENGDU";
			}
			int loanAmount = 0;
			if(customer.getDemandAccount() != null) {
				loanAmount = customer.getDemandAccount().intValue()/10000;
			}
			if (loanAmount <1) {
				loanAmount = 5;
			}
			String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()),
					signature = MD5Util.getMD5(region+phone +customer.getName()+customer.getGender()+loanAmount+289+ timestamp + "rongzi.com_8763");
			obj.put("CityName", region);
			obj.put("CellPhoneNumber", phone);
			obj.put("RealName", customer.getName());
			obj.put("Gender", customer.getGender());
			obj.put("LoanAmount", loanAmount);
			obj.put("UtmSource", 289);
			obj.put("TimeStamp", timestamp);
			obj.put("Signature", signature);
			
			System.out.println("发送注册数据：" + obj);
			responseData = httpClientUtil.post("application/json", obj.toString());
			obj = JSONObject.fromObject(responseData);
			@SuppressWarnings("rawtypes")
			Map map = (Map) obj;
			System.out.println("注册结果：" + map);
			if ("0".equals(map.get("Code").toString())) {//发送成功
				if (Boolean.FALSE.equals(map.get("IsRegistered"))) {//注册成功
					transCount++;
					customer.setTransportMark("1");
				} else {
					failedCount++;
					customer.setTransportMark("手机号已注册");
				}
				this.customerService.updateCustomer(customer);
			} else {
				System.out.println("注册请求失败，错误代码：" + map.get("Code"));
				return "注册请求失败，错误代码：" + map.get("Code");
			}
		}
		httpClientUtil.closeClient();// 关闭连接
		
		Statistics statistics = new Statistics(transCount, failedCount, "289", Calendar.getInstance().getTime());
		this.customerService.addStatistics(statistics);
		if (failedCount > 0 || transCount == 0) {
			return "{\"result\":\"error\", \"trans\":" + transCount + ",\"failed\":" + failedCount + "}";
		}
		return "{\"result\":\"success\", \"trans\":" + transCount + "}";
	}
	
	@RequestMapping("/getStatisticsDR")
	@ResponseBody
	public String getStatisticsDR(HttpServletRequest request) {
		String result="";
		String username = (String)request.getSession().getAttribute("username");
		if (username == null) {//登录状态失效
			return "{\"result\":\"lack\"}";
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null, endDate = null;
		try {
			if (request.getParameter("startDate")!="" && request.getParameter("startDate")!=null) {
				startDate = sdf.parse(request.getParameter("startDate"));
			}
			if (request.getParameter("endDate")!="" && request.getParameter("endDate")!=null) {
				endDate = sdf.parse(request.getParameter("endDate"));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (startDate != null && endDate != null) {
			Statistics statistics = this.customerService.selectBetween(startDate, endDate, "289");
			if (statistics != null) {
				result =  ",\"successCount\":" + statistics.getSuccessCount() + ",\"failCount\":"
						+ statistics.getFailCount() + ",\"adsId\":289";
			}
		}
		if ("".equals(result)) {
			return  "{\"result\":\"error\"}";
		}
		return "{\"result\":\"success\""+result+"}";
	}
	
	@RequestMapping("/insurance")
	@ResponseBody
	public String getFreeInsurance(HttpServletRequest request) throws Exception {
		Map<String, Object> data = WebUtils.getParametersStartingWith(request, null);
		String result = "{\"code\":0,\"msg\":\"数据为空\",\"data\":null}";
		if (data == null) {
			return result;
		}
		
		HttpClientUtil httpClientUtil = new HttpClientUtil("http://119.18.231.129:15080/openapi/sms/send.json");
		String responseData = null;
			

		JSONObject obj = new JSONObject();
		String openChannel = "KUAIMENG_TEST",
				openKey = "afSSAdFALAQciNWw";
		long timeStamp = UnixTimeStampUtil.getNowTimeStamp();
		obj.put("openChannel", openChannel);
		obj.put("phone", data.get("phone").toString());
		obj.put("openEncypt", MD5Util.getMD5(openChannel+":"+ openKey+":"+ timeStamp));
		obj.put("version", "1.0.0");
		obj.put("platform", 3);
		obj.put("timestamp", timeStamp);
		obj.put("deviceId", SessionIdUtil.generateSessionId());
		obj.put("deviceType", "iphone8");
		obj.put("netType", 1);
		obj.put("language", "CH-CN");
		obj.put("channel", "szkm");
		obj.put("screenSize", 13);
			
		System.out.println("发送注册数据：" + obj);
		responseData = httpClientUtil.post("application/json", obj.toString());
		obj = JSONObject.fromObject(responseData);
		@SuppressWarnings("rawtypes")
		Map map = (Map) obj;
		System.out.println("注册结果：" + map);
		httpClientUtil.closeClient();// 关闭连接
		
		return "{\"result\":\"success\", \"trans\":" + 1 + "}";
	}
}
