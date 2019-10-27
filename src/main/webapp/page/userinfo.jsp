<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改资料</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="css/ionicons.min.css">
<!-- bootstrapValidator -->
<link rel="stylesheet" href="<%=basePath%>css/bootstrapValidator.min.css" type="text/css">
<!-- Theme style -->
<link rel="stylesheet" href="css/AdminLTE.min.css">
<!-- AdminLTE Skins. We have chosen the skin for this starter
        page. However, you can choose any other skin. Make sure you
        apply the skin class to the body tag so the changes take effect.
  -->
<link rel="stylesheet" href="css/skin.min.css">
</head>
<body class="hold-transition skin sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<%@include file="/page/header.jsp"%>
		<!-- Left side column. contains the logo and sidebar -->
		<%@include file="/page/sidebar.jsp"%>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h4>修改资料</h4>
			</section>

			<!-- Main content -->
			<section class="content">
			<form id="userForm" action="user/update" method="post" onsubmit="return checkpwd()">
				<div class="row">
					<div class="box">
						<div class="box-body">
							<div class="col-xs-6">
								<table class="table table-striped">
									<tbody>
										<tr>
											<td><input name="id" value="${user.id }" type="text" style="display:none;" />
											<span style="width: 200px;">邮箱</span>
											<input name="email" value="${user.email }" type="email" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											</td>
										</tr>
										<tr>
											<td><span style="width: 200px;">用户名</span>
											<input name="username" value="${user.username }" type="text" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											</td>
										</tr>
										<tr>
											<td><span style="width: 200px;">新密码</span>
											<input id="password" name="password" type="text" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											<span id='tips' style='color:red;font-size:12px'>不修改密码时密码两项不填。</span>
											</td>
										</tr>
										<tr>
											<td><span style="width: 200px;">确认密码</span>
											<input  id="confirm" name="confirm"  type="text" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											</td>
										</tr>
										<tr>
											<td><span style="width: 200px;">昵称</span>
											<input name="nickname" value="${user.nickname }" type="text" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											</td>
										</tr>
										<tr>
											<td><span style="width: 200px;">渠道ID</span>
											<input name="channelId" value="${user.channelId }" type="text" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											</td>
										</tr>
										<tr>
											<td><span style="width: 200px;">广告位ID1</span>
											<input name="adsId1" value="${user.adsId1 }" type="text" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											</td>
										</tr>
										<tr>
											<td><span style="width: 200px;">广告位ID2</span>
											<input name="adsId2" value="${user.adsId2 }" type="text" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											</td>
										</tr>
										<tr>
											<td><span style="width: 200px;">接口路径</span>
											<input name="destUrl" value="${user.destUrl }" type="text" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="col-xs-6">
								
							</div>
							<!-- /.col -->
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-xs-12">
					<button type="submit" class="width-35 btn btn-sm btn-primary">
							<i class="fa fa-save fa-fw"></i> 保存
					</button>
					<button type="reset" class="width-35 btn btn-sm btn-danger" onclick="location.href='<%=basePath%>customer/toImport'">
							<i class="fa fa-close fa-fw"></i> 取消
					</button>
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</form>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<footer class="main-footer">
			<!-- To the right -->
			<div class="pull-right hidden-xs">
				<strong>Copyright &copy; 2017 <a href="#">Company</a>.
				</strong> All rights reserved.
			</div>
		</footer>

		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED JS SCRIPTS -->

	<!-- jQuery 2.2.3 -->
	<script src="js/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="js/bootstrap.min.js"></script>
	<!-- bootstrapValidator -->
	<script type="text/javascript" src="<%=basePath%>js/bootstrapValidator.min.js"></script>
	<!-- upload script -->
	<script src="js/ajaxfileupload.js"></script>
	<!-- AdminLTE App -->
	<script src="js/app.min.js"></script>
	<!-- page script -->
	<script src="js/md5.min.js"></script>
	<script>
	function checkpwd(){
		$("#tips").remove();
		var password = $("#password").val(),
		confirm = $("#confirm").val();
		if (password!="") {
			if (password != confirm) {
				$("#confirm").after("<span id='tips' style='color:red;font-size:12px'>两次输入密码必须一致。</span>");
				return false;
			}
			$("#password").val(md5(password));
		}
		return true;
	}
	</script>
	<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>