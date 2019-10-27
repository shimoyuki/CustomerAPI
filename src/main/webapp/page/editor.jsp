<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>数据编辑</title>
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
				<h4>数据编辑</h4>
			</section>

			<!-- Main content -->
			<section class="content">
			<form id="customerForm" action="customer/update" method="post">
				<div class="row">
					<div class="box">
						<div class="box-body">
							<div class="col-xs-6">
								<table class="table table-striped">
									<tbody>
										<tr>
											<td><input name="id" value="${customer.id }" type="text" style="display:none;" />
											<span style="width: 200px;">姓名</span>
											<input name="name" value="${customer.name }" type="text" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											</td>
										</tr>
										<tr>
											<td><span style="width: 200px;">联系方式</span>
											<input name="phone" value="${customer.phone }" type="text" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											</td>
										</tr>
										<tr>
											<td><span style="width: 200px;">地区</span>
											<input name="region" value="${customer.region }" type="text" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											</td>
										</tr>
										<tr>
											<td><span style="width: 200px;">性别</span>
											 <select id="gender" name="gender" class="form-control" style="display: inline; width: 200px; border-radius: 5px;">
													<option value="">未知</option>
													<option value="1">男</option>
													<option value="2">女</option>
											</select></td>
										</tr>
										<tr>
											<td><span style="width: 200px;">出生日期</span>
											<input name="birthday" value="${customer.birthday }" type="text" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											</td>
										</tr>
										<tr>
											<td><span style="width: 200px;">身份证号</span>
											<input name="idCardNo" value="${customer.idCardNo }" type="text" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											</td>
										</tr>
										<tr>
											<td><span style="width: 200px;">贷款金额(元)</span>
											<input name="demandAccount" value="${customer.demandAccount }" type="text" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											</td>
										</tr>
										<tr>
											<td><span style="width: 200px;">月收入范围</span>
											<select id="monthlyIncome" name="monthlyIncome" class="form-control" style="display: inline; width: 200px; border-radius: 5px;">
													<option value="">未知</option>
													<option value="0">无</option>
													<option value="1">5000元以下</option>
													<option value="2">5000-10000元</option>
													<option value="3">10000元以上</option>
											</select></td>
										</tr>
										<tr>
											<td><span style="width: 200px;">职业类型</span>
											<select id="careerType" name="careerType" class="form-control" style="display: inline; width: 200px; border-radius: 5px;">
													<option value="">未知</option>
													<option value="0">无</option>
													<option value="1">公务员</option>
													<option value="2">公司职员</option>
													<option value="3">企业主</option>
													<option value="4">个体户</option>
													<option value="5">自由职业</option>
													<option value="6">其它</option>
											</select></td>
										</tr>
										<tr>
											<td><span style="width: 200px;">是否有公积金</span>
											<select id="isHasFund" name="isHasFund" class="form-control" style="display: inline; width: 200px; border-radius: 5px;">
													<option value="">未知</option>
													<option value="1">有</option>
													<option value="0">无</option>
											</select></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="col-xs-6">
								<table class="table table-striped">
									<tbody>
										<tr>
											<td><span style="width: 200px;">是否有社保</span>
											<select id="isHasSecurity" name="isHasSecurity" class="form-control" style="display: inline; width: 200px; border-radius: 5px;">
													<option value="">未知</option>
													<option value="1">有</option>
													<option value="0">无</option>
											</select></td>
										</tr>
										<tr>
											<td><span style="width: 200px;">工资发放形式</span>
											<select id="payrollForm" name="payrollForm" class="form-control" style="display: inline; width: 200px; border-radius: 5px;">
													<option value="">未知</option>
													<option value="0">无</option>
													<option value="1">银行代发</option>
													<option value="2">转账工资</option>
													<option value="3">现金发放</option>
											</select></td>
										</tr>
										<tr>
											<td><span style="width: 200px;">是否有过信用卡</span>
											<select id="isHadCredit" name="isHadCredit" class="form-control" style="display: inline; width: 200px; border-radius: 5px;">
													<option value="">未知</option>
													<option value="1">有</option>
													<option value="0">无</option>
											</select></td>
										</tr>
										<tr>
											<td><span style="width: 200px;">是否有逾期</span>
											<select id="isHadOverdue" name="isHadOverdue" class="form-control" style="display: inline; width: 200px; border-radius: 5px;">
													<option value="">未知</option>
													<option value="1">有</option>
													<option value="0">无</option>
											</select></td>
										</tr>
										<tr>
											<td><span style="width: 200px;">当地居住时间</span>
											<select id="residenceTime" name="residenceTime" class="form-control" style="display: inline; width: 200px; border-radius: 5px;">
													<option value="">未知</option>
													<option value="1">半年以上</option>
													<option value="0">半年以下</option>
											</select></td>
										</tr>
										<tr>
											<td><span style="width: 200px;">名下房产</span>
											<select id="houseProperty" name="houseProperty" class="form-control" style="display: inline; width: 200px; border-radius: 5px;">
													<option value="">未知</option>
													<option value="0">无房产</option>
													<option value="1">有房产还贷中</option>
													<option value="2">有房产无房贷</option>
											</select></td>
										</tr>
										<tr>
											<td><span style="width: 200px;">名下私家车</span>
											<select id="carProperty" name="carProperty" class="form-control" style="display: inline; width: 200px; border-radius: 5px;">
													<option value="">未知</option>
													<option value="0">无车</option>
													<option value="1">有车还贷中</option>
													<option value="2">有车无车贷</option>
											</select></td>
										</tr>
										<tr>
											<td><span style="width: 200px;">名下寿险保单</span>
											<select id="insurancePolicy" name="insurancePolicy" class="form-control" style="display: inline; width: 200px; border-radius: 5px;">
													<option value="">未知</option>
													<option value="0">无寿险</option>
													<option value="1">年保费2400以下</option>
													<option value="2">年保费2400以上</option>
											</select></td>
										</tr>
										<tr>
											<td><span style="width: 200px;">媒体号</span>
											<input name="mediaNumber" value="${customer.mediaNumber }" type="text" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											</td>
										</tr>
										<c:if test="${operation eq 'customer/update'}">
										<tr>
											<td><span style="width: 200px;">创建时间</span>
											<input readonly="readonly" value="<fmt:formatDate value="${customer.createDate }" pattern="yyyy-MM-dd" />" type="text" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											</td>
										</tr>
										<tr>
											<td><span style="width: 200px;">录入时间</span>
											<input readonly="readonly" value="<fmt:formatDate value="${customer.recordDate }" pattern="yyyy-MM-dd" />" type="text" class="form-control" style="display: inline; width: 200px; border-radius: 5px;" />
											</td>
										</tr>
										</c:if>
									</tbody>
								</table>
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
	<script>
	 $(function() {
		 $("#gender").val("${customer.gender}");
		 $("#monthlyIncome").val("${customer.monthlyIncome}");
		 $("#careerType").val("${customer.careerType}");
		 $("#isHasFund").val("${customer.isHasFund}");
		 $("#isHasSecurity").val("${customer.isHasSecurity}");
		 $("#payrollForm").val("${customer.payrollForm}");
		 $("#isHadCredit").val("${customer.isHadCredit}");
		 $("#isHadOverdue").val("${customer.isHadOverdue}");
		 $("#residenceTime").val("${customer.residenceTime}");
		 $("#houseProperty").val("${customer.houseProperty}");
		 $("#carProperty").val("${customer.carProperty}");
		 $("#insurancePolicy").val("${customer.insurancePolicy}");
		 $("#customerForm").attr("action","${operation}");
	 });
	</script>
	<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>