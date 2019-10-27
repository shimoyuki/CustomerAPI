<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">

			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">

				<!-- Sidebar user panel (optional) -->
				<div class="user-panel">
					<div class="pull-left image">
						<img src="${user.profile}" class="img-circle" alt="User Image">
					</div>
					<div class="pull-left info">
						<p>${user.nickname}</p>
						<!-- Status -->
						<i class="fa fa-circle text-success"></i> <c:if test="${sessionScope.username != null }">Online</c:if>
					</div>
				</div>

				<!-- Sidebar Menu -->
				<ul class="sidebar-menu">
					<!-- Optionally, you can add icons to the links -->
					<li class="treeview">
					    <a href="#"><i class="fa fa-link"></i><span>数据管理</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
						<ul class="treeview-menu">
							<li class="active"><a href="customer/toImport">数据导入</a></li>
							<li><a href="customer/toTransportSY">数据发送（速易）</a></li>
							<li><a href="customer/toTransportDR">数据发送（东融）</a></li>
							<li><a href="customer/toTransportPA">数据发送（平安）</a></li>
						</ul>
					</li>
					<li>
					<a href="user/toUpdate"><i class="fa fa-link"></i><span>修改资料</span> <span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span></a>
					</li>
				</ul>
				<!-- /.sidebar-menu -->
			</section>
			<!-- /.sidebar -->
		</aside>
