package com.capi.controller;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capi.model.User;
import com.capi.service.UserService;
import com.capi.utils.CookieUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username"),
				password = request.getParameter("password"),
				savepwd = request.getParameter("savepwd");
		//System.out.println(username+"****************"+password);
		if (this.userService.checkUser(username, password)) {
			int  loginMaxAge = 30*24*60*60;   //定义账户密码的生命周期，这里是一个月。单位为秒
			//System.out.println(savepwd);
			if ("true".equals(savepwd)) {
				CookieUtil.addCookie(response, "loginName", username, loginMaxAge);//将用户名加入到cookie中
				CookieUtil.addCookie(response, "loginPwd", password, loginMaxAge);//将密码加入到cookie中
			}
			request.getSession().setAttribute("username", username);
			return "{\"result\":\"success\"}";
		}
		return "{\"result\":\"error\"}";
	}
	
	@RequestMapping("/signUp")
	@ResponseBody
	public String signUp(HttpServletRequest request) {
		String username = request.getParameter("username"),
				password = request.getParameter("password"),
				signCode = request.getParameter("signCode");
		if (!"Q3VzdG9tZXI=".equals(signCode)) {
			return "{\"result\":\"error\"}";
		}
		//System.out.println(username+"****************"+password);
		
		Map<String, Object> params = new TreeMap<>();
		params.put("username", username);
		if (!this.userService.getUsers(params).isEmpty()) {
			return "{\"result\":\"error\"}";
		}
		User user = new User(username, password);
		user.setProfile("images/profile-default.jpg");
		user.setDestUrl("http://d.95jr.com/api/mutiloan/newproduct.aspx");
		user.setChannelId("110315");
		user.setAdsId1("2102414");
		user.setAdsId2("2102533");
		if (this.userService.addUser(user) > 0) {
			request.getSession().setAttribute("username", username);
			return "{\"result\":\"success\"}";
		}
		return "{\"result\":\"error\"}";
	}
	
	@RequestMapping("/logOut")
	public String logOut(HttpServletRequest request, HttpServletResponse response) {
		CookieUtil.addCookie(response, "loginName", null, 0); // 清除Cookie
		CookieUtil.addCookie(response, "loginPwd", null, 0); // 清除Cookie
		request.getSession().removeAttribute("username");
		return "redirect:/";
	}
	
	@RequestMapping("/toUpdate")
	public String toUpdate(HttpServletRequest request, Model model) {
		String username = (String)request.getSession().getAttribute("username");
		Map<String, Object> params = new TreeMap<>();
		params.put("username", username);
		User user = this.userService.getUsers(params).get(0);
		model.addAttribute("user", user);
		return "userinfo";
	}
	@RequestMapping("update")
	public String update(User user) {
		if ("".equals(user.getPassword())) {
			user.setPassword(null);
		}
		if (this.userService.updateUser(user) < 1) {
			System.out.println("修改失败！");
		}
		return "redirect:toUpdate";
	}
}
