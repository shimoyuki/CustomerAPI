<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	if(session.getAttribute("username") != null){
		response.sendRedirect(basePath+"customer/toImport");
	}
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<!-- basic styles -->
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/font-awesome.min.css" />
<!-- ace styles -->
<link rel="stylesheet" href="css/ace.min.css" />
</head>
<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<span class="black">客户数据管理系统</span>
								</h1>
								<h4 class="blue">&copy; Company Name</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header black lighter bigger" align="center">
												<i class="fa fa-id-card fa-lg"></i> 用户登录
											</h4>

											<div class="space-6"></div>

											<form action="user/login">
												<fieldset>
                                                <label class="block clearfix">
                                                <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-user fa-fw"></i></span>
                                                <input id="username" name="username" class="form-control" type="text" placeholder="用户名">
                                                </div>
                                                </label>
                                                <label id="loginTip" class="block clearfix">
                                                <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                                                <input id="password" name="password" class="form-control" type="password" placeholder="密码">
                                                </div>
                                                </label>

												<div class="space"></div>

													<div class="clearfix">
														<label class="inline">
															<input id="savepwd" name="savepwd" type="checkbox" class="ace" />
															<span class="lbl"> 保存密码</span>
														</label>

														<button id="login" type="button" class="width-35 pull-right btn btn-sm btn-primary">
														<i class="fa fa-user-o fa-fw"></i> 登录
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>
										</div><!-- /widget-main -->

										<div class="toolbar clearfix">
											<div>
                                                <!-- <a class="btn btn-sm btn-primary" href="#" onClick="show_box('forgot-box'); return false;"><i class="fa fa-unlock"></i> 忘记密码?</a> -->
											</div>

											<div>
												<a class="btn btn-sm btn-primary" href="#" onClick="show_box('signup-box'); return false;"><i class="fa fa-user-circle-o"></i> 注册</a>
											</div>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /login-box -->

								<div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header black lighter bigger" align="center">
												<i class="fa fa-id-card-o fa-lg"></i> 重置密码
											</h4>

											<div class="space-6"></div>
											<p>
												请输入验证邮箱
											</p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<div class="input-group">
                                                        <span class="input-group-addon"><i class="fa fa-envelope fa-fw"></i></span>
                                                        <input class="form-control" type="email" placeholder="邮箱">
                                                        </div>
													</label>

													<div class="clearfix">
														<button type="submit" class="width-35 pull-right btn btn-sm btn-danger">
															<i class="fa fa-envelope-o fa-fw"></i> 发送
														</button>
													</div>
												</fieldset>
											</form>
										</div><!-- /widget-main -->

										<div class="toolbar center">
                                            <div>
												<a class="btn btn-sm btn-danger" href="#" onClick="show_box('login-box'); return false;"><i class="fa fa-user-circle"></i> 返回登录</a>
											</div>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /forgot-box -->

								<div id="signup-box" class="signup-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header black lighter bigger" align="center">
												<i class="fa fa-id-card-o fa-lg"></i> 注册用户
											</h4>

											<div class="space-6"></div>

											<form action="user/login">
												<fieldset>
												<label class="block clearfix">
                                                <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-envelope fa-fw"></i></span>
                                                <input id="signCode" name="signCode" class="form-control" type="text" placeholder="注册码">
                                                </div>
                                                </label>
                                                
                                                <label class="block clearfix">
                                                <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-user fa-fw"></i></span>
                                                <input id="sUsername" name="username" class="form-control" type="text" placeholder="用户名">
                                                </div>
                                                </label>
                                                
                                                <label class="block clearfix">
                                                <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                                                <input id="sPassword" name="password" class="form-control" type="password" placeholder="密码">
                                                </div>
                                                </label>
                                                
                                                <label id="signUpTip" class="block clearfix">
                                                <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
                                                <input  id="sConfirm" name="confirm" class="form-control" type="password" placeholder="确认密码">
                                                </div>
                                                </label>

													<div class="space-24"></div>

													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="fa fa-trash-o fa-fw"></i>清空
														</button>

														<button id="signUp" type="button" class="width-65 pull-right btn btn-sm btn-success">
															<i class="fa fa-user-o fa-fw"></i> 注册
														</button>
													</div>
												</fieldset>
											</form>
										</div>

										<div class="toolbar center">
											<div>
												<a class="btn btn-sm btn-success" href="#" onClick="show_box('login-box'); return false;"><i class="fa fa-user-circle"></i> 返回登录</a>
											</div>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /signup-box -->
							</div><!-- /position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->

		<!-- basic scripts -->
		<script src="js/jquery-2.2.3.min.js"></script>
		<script type="text/javascript">
			window.jQuery || document.write("<script src='js/jquery-2.2.3.min.js'>"+"<"+"/script>");
		</script>
		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<!-- inline scripts related to this page -->
		<script src="js/md5.min.js"></script>
		<script type="text/javascript">
			function show_box(id) {
			 jQuery('.widget-box.visible').removeClass('visible');
			 jQuery('#'+id).addClass('visible');
			}
			
			$(function() {
				$("#login").click(function() {
					$.ajax({
						data : {
						username:  $("#username").val(),
						password: md5($("#password").val()),
						savepwd: $("#savepwd").prop("checked")
						},
						type : "post",
						dataType : 'json',
						url : "<%=basePath%>user/login",
						error : function(data) {
							alert("网络出错");
						},
						success : function(data) {
							$("#tips").remove();
							if (data.result == "success") {
								window.location.href="customer/toImport";
							} else {
								$("#loginTip").append("<span id='tips' style='color:red;font-size:12px'>登录失败，请检查用户名密码是否正确。</span>");
							}
						}
			    });
				});
				
				$("#signUp").click(function() {
					$("#tips").remove();
					var username = $("#sUsername").val(),
					password = $("#sPassword").val(),
					confirm = $("#sConfirm").val();
					if (username == "" || password == "" || confirm == "") {
						$("#signUpTip").append("<span id='tips' style='color:red;font-size:12px'>账号密码不能为空。</span>");
						return;
					}
					if (password != confirm) {
						$("#signUpTip").append("<span id='tips' style='color:red;font-size:12px'>两次输入密码必须一致。</span>");
						return;
					}
					$.ajax({
						data : {
						username: username ,
						password: md5(password),
						signCode: $("#signCode").val()
						},
						type : "post",
						dataType : 'json',
						url : "<%=basePath%>user/signUp",
						error : function(data) {
							alert("网络出错");
						},
						success : function(data) {
							if (data.result == "success") {
								window.location.href="customer/toImport";
							} else {
								$("#signUpTip").append("<span id='tips' style='color:red;font-size:12px'>注册失败，注册码错误或账号已存在。</span>");
							}
						}
			    });
				});
			});
		</script>
	</body>
</html>
