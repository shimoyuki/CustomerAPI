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
<title>数据导入</title>
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
				<h4>数据导入</h4>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-4">
						<form id="uploadForm" role="form" action="customer/upload1" method="post" enctype="multipart/form-data">
							<div class="box-body">
									<input id="uploadFile" name="uploadFile" type="file" style="display:inline">
									<input id="upload" onclick="return confirm('上传文件需要一些时间，请耐心等候！');" type="button" value="上传">
									<p class="help-block">选择要上传的Excel文件(不含地区信息)</p>
							</div>
						</form>
					</div>
					<div class="col-xs-4">
						<form id="uploadForm2" role="form" action="customer/upload2" method="post" enctype="multipart/form-data">
							<div class="box-body">
									<input id="uploadFile2" name="uploadFile2" type="file" style="display:inline">
									<input id="upload2" onclick="return confirm('上传文件需要一些时间，请耐心等候！');" type="button" value="上传">
									<p class="help-block">选择要上传的Excel文件(含地区信息)</p>
							</div>
						</form>
					</div>
					<div class="col-xs-4">
					<div class="box-body">
				<a href="customer/toAdd" class="btn btn-info btn-sm"><i class="fa fa-pencil"></i>手动添加数据</a>
				</div>
				</div>
				</div>

				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<h3 class="box-title">已导入用户信息表</h3>
							</div>
							<!-- /.box-header -->
							
							<div class="box-body">
							<button id="deleteSelect" type="button" class="width-35 pull-left btn btn-sm btn-danger"><i class="fa fa-trash-o"></i> 删除选择的数据</button>
							<button id="updateSelect" type="button" class="width-35 pull-left btn btn-sm btn-info"><i class="fa fa-pencil"></i> 将选择数据重置为未发送</button>
							<button id="delete" type="button" class="width-35 pull-right btn btn-sm btn-danger"><i class="fa fa-trash-o"></i> 删除符合条件的数据</button>
								<select class="form-control"
									style="width: auto; height: 30px; float: right" id="mediaNumber"
									name="mediaNumber">
									<option value="">全部</option>
									<c:forEach items="${mnList}" var="item">
										<option value="${item}"> ${item}</option>
									</c:forEach>
								</select> <span style="width: auto; float: right; padding-top: 5px;margin-left: 10px;">媒体号</span>
								<input type="text" class="form-control"
									style="width: auto; height: 30px; float: right" id="region" placeholder="地区/-地区（选择/排除指定地区）"
									name="region"/> <span style="width: auto; float: right; padding-top: 5px;margin-left: 10px;">地区</span>
								<input type="text" id="endCDate" name="endCDate"  value="${fcdList[1]}"
									style="width: 100px; height: 30px; float: right"
									class="form-control" placeholder="终止日期">
								<input type="text" id="startCDate" name="startCDate" value="${fcdList[0]}"
									style="width: 100px; height: 30px; float: right;"
									class="form-control" placeholder="起始日期">
								<span style="width: auto; float: right; padding-top: 5px;">创建时间</span>
								<select class="form-control" style="width: auto; height: 30px; float: right" id="recordDate" name="recordDate">
									<option value="">全部</option>
									<c:forEach items="${frdList}" var="item">
										<option value="${item}"> ${item}</option>
									</c:forEach>
								</select> <span style="width: auto; float: right;padding-top:5px">录入时间</span>
							</div>
							<!-- /.box-body -->
							
							<div class="box-body">
								<table id="customerTable"
									class="table table-bordered table-striped">
									<thead>
										<tr>
											<th style="text-align: center;"><input type="checkbox" id="selectAll"></th>
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
											<th style="text-align: center;">创建时间</th>
											<th style="text-align: center;">发送状态</th>
											<th style="text-align: center;">操作</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th style="text-align: center;"><input type="checkbox" id="selectAll"></th>
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
											<th style="text-align: center;">创建时间</th>
											<th style="text-align: center;">发送状态</th>
											<th style="text-align: center;">操作</th>
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
	<!-- AdminLTE App -->
	<script src="js/app.min.js"></script>
	<!-- page script -->
	<script src="js/data_table.js"></script>
	<script>
	    function del(id){
		    $.ajax({
					data : {
					id: id
					},
					type : "get",
					dataType : 'json',
					url : "<%=basePath%>customer/delete",
					error : function(data) {
						alert("删除出错");
					},
					success : function(data) {
						if (data.result == "success") {
							$("#customerTable").dataTable().fnDraw(false);
						} else {
							alert("删除出错");
						}
					}
		    });
	    }
	    function ajaxFileUpload(url, file) {
			$.ajaxFileUpload({
				url : '<%=basePath%>'+url, //用于文件上传的服务器端请求地址
				secureuri : false, //一般设置为false  
				fileElementId : file, //文件上传空间的id属性  <input type="file" id="file" name="file" />  
				dataType : 'json', //返回值类型 一般设置为json  
				type : "post",
				error : function(data) {
					alert("上传文件出错");
				},
				success : function(data) {
					if (data.result == "success") {
						alert("上传数据成功");
						$("#customerTable").dataTable().fnDraw(false);
					} else if(data.result == "exist"){
						alert("上传数据成功,其中部分数据已存在");
						$("#customerTable").dataTable().fnDraw(false);
					}else {
						alert("上传数据失败，服务端出错或数据均已存在");
					}
				}
			});
			return false;
		}
	
		  $(function() {
			$("#upload").click(function() {
				if ($("#uploadFile").val().length > 0) {
					ajaxFileUpload('customer/upload1', 'uploadFile');
				} else {
					$.messager.alert("请选择文件！");
				}
			});
			$("#upload2").click(function() {
				if ($("#uploadFile2").val().length > 0) {
					ajaxFileUpload('customer/upload2', 'uploadFile2');
				} else {
					$.messager.alert("请选择文件！");
				}
			});
			$('#customerTable').dataTable(
					{
						ajax : {
							url : 'customer/getPage',
							type : 'POST',
							"data": function ( d ) {
							      return $.extend( {}, d, {
							        recordDate: $('#recordDate').val(),
							        startCDate: $('#startCDate').val(),
							    	endCDate: $('#endCDate').val(),
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
							data : 'createDate',
							sClass:"text-center"
						}, {
							data : 'transportMark',
							sClass:"text-center"
						}, {
							data : 'id',
							sClass:"text-center",
							render: function(data,type,row,meta){
								return data = '<a href="customer/toUpdate?id=' + data + '" class="btn btn-info btn-sm"><i class="fa fa-pencil"></i>编辑</a>'
				                  + '<a href="javascript:if(confirm(\'确认删除？\'))del(\''+data+'\')" class="btn btn-danger btn-sm" data-id=' + data + '><i class="fa fa-trash-o"></i>删除</a>';
				            }
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
			$("#recordDate").bind("change", function () { //按钮 点击事件触发table重新请求服务器
				  $("#customerTable").dataTable().fnDraw(false);
			});
			$("#startCDate").bind("change", function () { //按钮 点击事件触发table重新请求服务器
				  $("#customerTable").dataTable().fnDraw(false);
			});
			$("#endCDate").bind("change", function () { //按钮 点击事件触发table重新请求服务器
				  $("#customerTable").dataTable().fnDraw(false);
			});
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
			$("#delete").click(function(){
				var date =$("#recordDate").val(),
				startCDate = $("#startCDate").val(),
				endCDate = $("#endCDate").val(),
				region = $("#region").val(),
				media = $("#mediaNumber").val(),
				info = "";
				if (date == "" && startCDate == "" && endCDate == "" && region == "" && media == "") {
					info = "全部";
				}else {
					if (startCDate != "" && endCDate == "") {
						info += startCDate;
						info += "至";
						info += endCDate
						info += "内创建的";
					}
					if (date != "") {
						info += date;
						info += "录入"
					}
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
				}
				if(confirm('确认删除'+info+'的数据？')){
						$.ajax({
							url : 'customer/deleteBy',
							type : "POST",
							dataType : "json",
							data :{
								recordDate : $("#recordDate").val(),
								startCDate: $('#startCDate').val(),
								endCDate: $('#endCDate').val(),
								region: $('#region').val(),
							    mediaNumber: $('#mediaNumber').val()
							},
							error : function(data) {
								alert("网络出错");
							},
							success : function(data) {
								if (data.result == "success") {
									$("#customerTable").dataTable().fnDraw(false);
								} else {
									alert("删除出错");
								}
							}
						});
				}
			});
			$("#deleteSelect").click(function(){
				var select = "";
				$("input:checkbox[name='select']").each(function(i) {
					if ($(this).prop("checked")) {
						if (select == "") {
							select = $(this).val();
						} else {
							select += ("," + $(this).val());
						}
					}
				});
				if(select != "" && confirm('确认删除id为'+select+'的数据？')){
						$.ajax({
							url : 'customer/deleteSelect',
							type : "POST",
							dataType : "json",
							data :{
								select: select
							},
							error : function(data) {
								alert("网络出错");
							},
							success : function(data) {
								if (data.result == "success") {
									$("#customerTable").dataTable().fnDraw(false);
								} else {
									alert("删除出错");
								}
							}
						});
				}
			});
			$("#updateSelect").click(function(){
				var select = "";
				$("input:checkbox[name='select']").each(function(i) {
					if ($(this).prop("checked")) {
						if (select == "") {
							select = $(this).val();
						} else {
							select += ("," + $(this).val());
						}
					}
				});
				if(select != "" && confirm('确认重置id为'+select+'的数据为未发送状态？')){
						$.ajax({
							url : 'customer/updateSelect',
							type : "POST",
							dataType : "json",
							data :{
								select: select
							},
							error : function(data) {
								alert("网络出错");
							},
							success : function(data) {
								if (data.result == "success") {
									$("#customerTable").dataTable().fnDraw(false);
								} else {
									alert("修改出错");
								}
							}
						});
				}
			});
		}); 
	</script>
	<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->
</body>
</html>