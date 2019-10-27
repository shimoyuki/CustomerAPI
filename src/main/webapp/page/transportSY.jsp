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
<title>数据发送（速易）</title>
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="css/ionicons.min.css">
<!-- DataTables -->
<link rel="stylesheet" href="css/dataTables.bootstrap.css">
<!-- DCalendar -->
<link rel="stylesheet" href="css/dcalendar.picker.css"/>
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
				<h4>数据发送（速易）</h4>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div id="statistics" >
						    <span style="width: auto; float: left; padding-top: 5px;">发送统计</span>
						    <input type="text" id="startDate" name="startDate" style="width: auto; height: 30px; float: left"
								class="form-control" placeholder="起始日期(yyyy-MM-dd)">
						    <input type="text" id="endDate" name="endDate" style="width: auto; height: 30px; float: left"
								 class="form-control" placeholder="终止日期(yyyy-MM-dd)"> 
								<button type="button" name="statisticsCount" id="statisticsCount" style="width: auto; height: 30px;margin-bottom: 20px;" class="btn btn-flat">
									<i class="fa fa-search"></i>
								</button>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<h3 class="box-title">用户信息表</h3>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<button id="transpartSY" type="button"
									class="width-35 pull-left btn btn-sm btn-info">
									<i class="fa fa-envelope-o fa-fw"></i> 发送选择的数据
								</button>
								<button id="transportSY" type="button"
									class="width-35 pull-right btn btn-sm btn-info">
									<i class="fa fa-envelope-o fa-fw"></i> 发送符合条件的数据
								</button>
								<input type="text" class="form-control"
									placeholder="将要发送数据的条数，默认为0，不发送"
									style="width: auto; height: 30px; float: right" id="number"
									name="number" /> <span
									style="width: auto; float: right; padding-top: 5px; margin-left: 10px;">发送条数</span>
								<select class="form-control"
									style="width: auto; height: 30px; float: right"
									id="mediaNumber" name="mediaNumber">
									<option value="">全部</option>
									<c:forEach items="${mnList}" var="item">
										<option value="${item}"> ${item}</option>
									</c:forEach>
								</select> <span
									style="width: auto; float: right; padding-top: 5px; margin-left: 10px;">媒体号</span>
								<input type="text" class="form-control"
									placeholder="地区/-地区（选择/排除指定地区）" value="-深圳"
									style="width: auto; height: 30px; float: right" id="region"
									name="region" /> <span
									style="width: auto; float: right; padding-top: 5px; margin-left: 10px;">地区</span>
								<select class="form-control"
									style="width: auto; height: 30px; float: right" id="adsId"
									name="adsId">
									<option value="${user.adsId1 }">${user.adsId1 }</option>
									<option value="${user.adsId2 }">${user.adsId2 }</option>
								</select> <span
									style="width: auto; float: right; padding-top: 5px; margin-left: 10px;">广告位</span>
							</div>
							<!-- /.box-body -->

							<div class="box-body">
								<table id="customerTable"
									class="table table-bordered table-striped">
									<thead>
										<tr>
											<th style="text-align: center;"><input type="checkbox"
												id="selectAll"></th>
											<th style="text-align: center;">姓名</th>
											<th style="text-align: center;">联系方式</th>
											<th style="text-align: center;">地区</th>
											<th style="text-align: center;">性别</th>
											<th style="text-align: center;">出生日期</th>
											<th style="text-align: center;">月收入范围</th>
											<th style="text-align: center;">职业类型</th>
											<th style="text-align: center;">是否有公积金</th>
											<th style="text-align: center;">是否有社保</th>
											<th style="text-align: center;">工资发放形式</th>
											<th style="text-align: center;">是否有过信用卡</th>
											<th style="text-align: center;">是否有逾期</th>
											<th style="text-align: center;">当地居住时间</th>
											<th style="text-align: center;">名下房产</th>
											<th style="text-align: center;">名下私家车</th>
											<th style="text-align: center;">名下寿险保单</th>
											<th style="text-align: center;">贷款金额(元)</th>
											<th style="text-align: center;">媒体号</th>
											<th style="text-align: center;">发送状态</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th style="text-align: center;"></th>
											<th style="text-align: center;">姓名</th>
											<th style="text-align: center;">联系方式</th>
											<th style="text-align: center;">地区</th>
											<th style="text-align: center;">性别</th>
											<th style="text-align: center;">出生日期</th>
											<th style="text-align: center;">月收入范围</th>
											<th style="text-align: center;">职业类型</th>
											<th style="text-align: center;">是否有公积金</th>
											<th style="text-align: center;">是否有社保</th>
											<th style="text-align: center;">工资发放形式</th>
											<th style="text-align: center;">是否有过信用卡</th>
											<th style="text-align: center;">是否有逾期</th>
											<th style="text-align: center;">当地居住时间</th>
											<th style="text-align: center;">名下房产</th>
											<th style="text-align: center;">名下私家车</th>
											<th style="text-align: center;">名下寿险保单</th>
											<th style="text-align: center;">贷款金额(元)</th>
											<th style="text-align: center;">媒体号</th>
											<th style="text-align: center;">发送状态</th>
										</tr>
									</tfoot>
								</table>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
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
	<!-- DataTables -->
	<script src="js/jquery.dataTables.min.js"></script>
	<script src="js/dataTables.bootstrap.min.js"></script>
	<!-- upload script -->
	<script src="js/ajaxfileupload.js"></script>
	<!-- DCalendar -->
	<script type="text/javascript" src="js/dcalendar.picker.js"></script>  
	<!-- AdminLTE App -->
	<script src="js/app.min.js"></script>
	<!-- page script -->
	<script src="js/data_table.js"></script>
	<script>
		  $(function() {
			  $('#startDate').dcalendarpicker({format: 'yyyy-mm-dd'});//初始化日期选择器
			  $('#endDate').dcalendarpicker({format: 'yyyy-mm-dd'});
			  $('#customerTable').dataTable(
					{
						ajax : {
							url : 'customer/getPage',
							type : 'POST',
							"data": function ( d ) {
							      return $.extend( {}, d, {
							    	  region: $('#region').val(),
							    	  mediaNumber: $('#mediaNumber').val()
							      } );
							    }
						//dataSrc : 'list',//这个参数是自己封装的json里面的key
						},
						serverSide : true,//开启服务器模式:启用服务器分页
						lengthMenu : [ [ 20, 100, 1000, -1 ],
								[ 20, 100, 1000, "All" ] ],
						//lengthChange : false,//是否允许用户改变表格每页显示的记录数
						paging : true,//是否分页
						pagingType : "full_numbers",//除首页、上一页、下一页、末页四个按钮还有页数按钮
						processing : true,//是否显示处理状态
						scrollX: false,//允许水平滚动
						scrollY: '1600px',
						scrollCollapse: true, 
						searching : true,//是否开始本地搜索
						ordering : true,
						info : true,
						//stateSave : false,//刷新时是否保存状态
						autoWidth : true,//自动计算宽度
						//deferRender : true,//延迟渲染
						columns : [{
							data : 'id',
							sClass:"text-center",
							orderable: false,
							render: function(data,type,row,meta){
				                return data = '<input type="checkbox" id="select'+data+'" name="select" value="'+data+'">';
				            }
						}, {
							data : 'name',
							sClass:"text-center"
						}, {
							data : 'phone',
							sClass:"text-center"
						}, {
							data : 'region',
							sClass:"text-center"
						}, {
							data : 'gender',
							sClass:"text-center"
						}, {
							data : 'birthday',
							sClass:"text-center"
						}, {
							data : 'monthlyIncome',
							sClass:"text-center"
						}, {
							data : 'careerType',
							sClass:"text-center"
						}, {
							data : 'isHasFund',
							sClass:"text-center"
						}, {
							data : 'isHasSecurity',
							sClass:"text-center"
						}, {
							data : 'payrollForm',
							sClass:"text-center"
						}, {
							data : 'isHadCredit',
							sClass:"text-center"
						}, {
							data : 'isHadOverdue',
							sClass:"text-center"
						}, {
							data : 'residenceTime',
							sClass:"text-center"
						}, {
							data : 'houseProperty',
							sClass:"text-center"
						}, {
							data : 'carProperty',
							sClass:"text-center"
						}, {
							data : 'insurancePolicy',
							sClass:"text-center"
						}, {
							data : 'demandAccount',
							sClass:"text-center"
						}, {
							data : 'mediaNumber',
							sClass:"text-center"
						}, {
							data : 'transportMark',
							sClass:"text-center"
						}],
						language : {
							"sProcessing" : "处理中...",
							"sLengthMenu" : "每页 _MENU_ 项",
							"sZeroRecords" : "没有匹配结果",
							"sInfo" : "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
							"sInfoEmpty" : "当前显示第 0 至 0 项，共 0 项",
							"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
							"sInfoPostFix" : "",
							"sSearch" : "搜索:(发送状态,仅供查看)",
							"sUrl" : "",
							"sEmptyTable" : "表中数据为空",
							"sLoadingRecords" : "载入中...",
							"sInfoThousands" : ",",
							"oPaginate" : {
								"sFirst" : "首页",
								"sPrevious" : "上页",
								"sNext" : "下页",
								"sLast" : "末页",
								"sJump" : "跳转"
							},
							info : '第 _PAGE_ 页 / 总 _PAGES_ 页',
							infoEmpty : '没有数据',
							infoFiltered : '(过滤总件数 _MAX_ 条)'
						}
					});
			$("#adsId").change(function(){
				if ($("#adsId").val() == "2102533") {
					$("#region").val("深圳");
					$("#region").attr("readonly",true);
				}else{
					$("#region").val("-深圳");
					$("#region").attr("readonly",false);	
				}
				$("#customerTable").dataTable().fnDraw(false);
			}) ;
			$("#region").bind("change", function () { //按钮 点击事件触发table重新请求服务器
				  $("#customerTable").dataTable().fnDraw(false);
			});
			$("#mediaNumber").bind("change", function () { //按钮 点击事件触发table重新请求服务器
				  $("#customerTable").dataTable().fnDraw(false);
			});
			$("#selectAll").click(function() {
				if ($("#selectAll").prop("checked")) {
					$("input:checkbox[name='select']").prop("checked", true);// 全选
				} else {

					$("input:checkbox[name='select']").prop("checked", false);// 取消全选
				}
			});
			$("#transportSY").click(function(){
				$("#tips").remove();
				var ads =$("#adsId").val(),
				region = $("#region").val(),
				media = $("#mediaNumber").val(),
				number = $("#number").val(),
				info = "";
				if (region != "") {
					if (region.indexOf("-")>-1) {
						info += "除";
						info += region.substring(1);
						info += "以外地区";
					}else {
						info += region;
					}
				}
				if (media != "") {
					info += "媒体号为";
					info += media;
				}
				if (info != "") {
					info += "的";
				}
				if(confirm('确认发送'+info+number+'条数据到广告位'+ads+'？')){
				$.ajax({
					url : 'customer/transportSY',
					type : "POST",
					dataType : "json",
					data :{
						adsId : $("#adsId").val(),
						region: $('#region').val(),
						mediaNumber: $('#mediaNumber').val(),
				    	number: $('#number').val()
					},
					success : function(data) {
						if (data.result == "success") {
							$(".box-header").append("<span id='tips' style='color:red;font-size:18px'>传输成功，符合条件的未发送数据"+data.number+"条，共成功发送数据"+data.trans+"条。</span>");
						} else if(data.result == "error") {
							$(".box-header").append("<span id='tips' style='color:red;font-size:18px'>传输完毕，符合条件的未发送数据"+data.number+"条，共成功发送数据"+data.trans+"条，未发送成功数据"+data.failed+"条。</span>");
						} else{
							$(".box-header").append("<span id='tips' style='color:red;font-size:18px'>缺少渠道和广告位ID信息，请到修改资料完善。</span>");
						}
						$("#customerTable").dataTable().fnDraw(false);
					},
					error : function(res) {
						alert(res.responseText);
						$(".box-header").append("<span id='tips' style='color:red;font-size:18px'>网络错误，请联系技术人员。</span>");
					}
				});
			}
			});
			$("#transpartSY").click(function(){
				$("#tips").remove();
				var ads =$("#adsId").val(),
				select = "";
				$("input:checkbox[name='select']").each(function(i) {
					if ($(this).prop("checked")) {
						if (select == "") {
							select = $(this).val();
						} else {
							select += ("," + $(this).val());
						}
					}
				});
				if(select != "" && confirm('确认发送id为'+select+'的数据到广告位'+ads+'？')){
				$.ajax({
					url : 'customer/transpartSY',
					type : "POST",
					dataType : "json",
					data :{
						adsId : $("#adsId").val(),
						select : select
					},
					success : function(data) {
						if (data.result == "success") {
							$(".box-header").append("<span id='tips' style='color:red;font-size:18px'>传输成功，共成功发送数据"+data.trans+"条。</span>");
						} else if(data.result == "error") {
							$(".box-header").append("<span id='tips' style='color:red;font-size:18px'>传输完毕，共成功发送数据"+data.trans+"条，未发送成功数据"+data.failed+"条。</span>");
						} else{
							$(".box-header").append("<span id='tips' style='color:red;font-size:18px'>缺少渠道和广告位ID信息，请到修改资料完善。</span>");
						}
						$("#customerTable").dataTable().fnDraw(false);
					},
					error : function(res) {
						alert(res.responseText);
						$(".box-header").append("<span id='tips' style='color:red;font-size:18px'>网络错误，请联系技术人员。</span>");
					}
				});
			}
			});
			$("#statisticsCount").click(function(){
				$("#statisticsInfo").remove();
				$.ajax({
					url : 'customer/getStatisticsSY',
					type : "POST",
					dataType : "json",
					data :{
						startDate : $("#startDate").val(),
						endDate : $("#endDate").val()
					},
					success : function(data) {
						console.log(data);
						if (data.result == "success") {
							$("#statistics").append("<span id='statisticsInfo' style='font-size:18px'></span>");
							if(typeof(data.adsId1)!="undefined"){
								$("#statisticsInfo").append("<p>时段内广告位"+data.adsId1+"发送数据成功"+data.successCount1+"条，发送数据失败"+data.failCount1+"条</p>");
							}
							if(typeof(data.adsId2)!="undefined"){
								$("#statisticsInfo").append("<p>时段内广告位"+data.adsId2+"发送数据成功"+data.successCount2+"条，发送数据失败"+data.failCount2+"条</p>");
							}
						} else if(data.result == "error") {
							$("#statistics").append("<span id='statisticsInfo' style='color:red;font-size:18px'>无统计信息，起止时间有误或该时段无数据。</span>");
						}else {
							$("#statistics").append("<span id='statisticsInfo' style='color:red;font-size:18px'>登录状态失效或者缺少渠道和广告位ID信息，请到修改资料完善。</span>");
						}
					},
					error : function(res) {
						alert(res.responseText);
						$("#statistics").append("<span id='statisticsInfo' style='color:red;font-size:18px'>网络错误，请联系技术人员。</span>");
					}
				});
			});
		});
	</script>
	<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>