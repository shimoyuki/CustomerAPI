<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- Main Header -->
		<header class="main-header">
			<!-- Logo -->
			<a href="customer/toImport" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>首页</b></span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg"><b>首页</b></span>
			</a>

			<!-- Header Navbar -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button"> <span class="sr-only">Toggle navigation</span></a>
				<!-- Navbar Right Menu -->
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<!-- User Account Menu -->
						<li class="dropdown user user-menu">
							<!-- Menu Toggle Button --> 
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
							<!-- The user image in the navbar-->
							<img src="${user.profile}" class="user-image" alt="User Image"> 
							<!-- hidden-xs hides the username on small devices so only the image appears. -->
							<span class="hidden-xs">${user.nickname}</span>
							</a>
							<ul class="dropdown-menu">
								<!-- The user image in the menu -->
								<li class="user-header"><img src="${user.profile}" class="img-circle" alt="User Image">
								<p>${user.nickname}</p></li>
								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<a href="user/toUpdate" class="btn btn-default btn-flat">修改资料</a>
									</div>
									<div class="pull-right">
										<a href="user/logOut" class="btn btn-default btn-flat">退出登录</a>
									</div>
								</li>
							</ul>
						</li>
					</ul>
				</div>
				<div class="navbar-custom-menu">
					<!-- search form (Optional) -->
					<form action="#" method="get" class="navbar-form">
						<div class="input-group">
							<!-- <input type="text" name="q" class="form-control" placeholder="查询..."> <span class="input-group-btn">
								<button type="submit" name="search" id="search-btn" class="btn btn-flat">
									<i class="fa fa-search"></i>
								</button>
							</span> -->
						</div>
					</form>
					<!-- /.search form -->
				</div>
			</nav>
		</header>
		