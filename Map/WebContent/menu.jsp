<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	
%>
<!-- header-container start -->
<div class="header-container">
	<!-- header-top start -->
	<!-- classes:  -->
	<!-- "dark": dark version of header top e.g. class="header-top dark" -->
	<!-- "colored": colored version of header top e.g. class="header-top colored" -->
	<!-- ================ -->
	<div class="header-top dark">
		<div class="container">
			<div class="row">
				<div class="col-3 col-sm-6 col-lg-9">
					<!-- header-top-first start -->
					<!-- ================ -->
					<div class="header-top-first clearfix"></div>
					<!-- header-top-first end -->
				</div>
				<div class="col-9 col-sm-6 col-lg-3">

					<!-- header-top-second start -->
					<!-- ================ -->
					<div id="header-top-second" class="clearfix">

						<!-- header top dropdowns start -->
						<!-- ================ -->
						<div class="header-top-dropdown text-right">
							<c:choose>
								<c:when test="${empty id }">
									<div class="btn-group">
										<a href="signupForm.do" class="btn btn-default btn-sm"><i
											class="fa fa-user pr-2"></i> Sign Up</a>
									</div>
									<div class="btn-group">
										<button id="header-top-drop-2" type="button"
											class="btn dropdown-toggle btn-default btn-sm dropdown-toggle--no-caret"
											data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false">
											<i class="fa fa-lock pr-2"></i> Login
										</button>
										<ul
											class="dropdown-menu dropdown-menu-right dropdown-animation"
											aria-labelledby="header-top-drop-2">
											<li>


												<form class="login-form margin-clear" action="login.do"
													method="post">
													<div class="form-group has-feedback">
														<label class="control-label">아 이 디</label> <input
															type="text" class="form-control" placeholder="" name="id">
														<i class="fa fa-user form-control-feedback"></i>
													</div>
													<div class="form-group has-feedback">
														<label class="control-label">비밀번호</label> <input
															type="password" class="form-control" placeholder=""
															name="pw"> <i
															class="fa fa-lock form-control-feedback"></i>
													</div>
													<button type="submit" class="btn btn-gray btn-sm">
														로 그 인</button>
													 
													<ul class='pagination'>
														<!--
														<li><a href="#" class="btn btn-default btn-sm">아이디
																찾기</a></li>
														-->		
														<li><a href="passwordSearchForm.do" class="btn btn-default btn-sm">비밀번호
																찾기</a></li>
													</ul>
													 
												</form>
											</li>
										</ul>
									</div>
								</c:when>
								<c:otherwise>
									<div class="btn-group">
										<a href="logout.do" class="btn btn-default btn-sm"><i
											class="fa fa-user pr-2"></i> Logout</a> <a href="memberView.do"
											class="btn btn-default btn-sm"><i class="fa fa-user pr-2"></i>
											회원정보</a>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						<!--  header top dropdowns end -->
					</div>
					<!-- header-top-second end -->
				</div>
			</div>
		</div>
	</div>
	<!-- header-top end -->

	<!-- header start -->
	<!-- classes:  -->
	<!-- "fixed": enables fixed navigation mode (sticky menu) e.g. class="header fixed clearfix" -->
	<!-- "fixed-desktop": enables fixed navigation only for desktop devices e.g. class="header fixed fixed-desktop clearfix" -->
	<!-- "fixed-all": enables fixed navigation only for all devices desktop and mobile e.g. class="header fixed fixed-desktop clearfix" -->
	<!-- "dark": dark version of header e.g. class="header dark clearfix" -->
	<!-- "centered": mandatory class for the centered logo layout -->
	<!-- ================ -->
	<header class="header fixed fixed-desktop clearfix">
		<div class="container">
			<div class="row">
				<div class="col-md-auto hidden-md-down">
					<!-- header-first start -->
					<!-- ================ -->
					<div class="header-first clearfix">

						<!-- logo -->
						<div id="logo" class="logo">
							<a href="index.do"><img id="logo_img" src="images/logo.jpg" width='150'
								alt="The SSAFY"></a>
						</div>
					</div>
				</div>
				<div class="col-lg-10 ml-auto">

					<!-- header-second start -->
					<!-- ================ -->
					<div class="header-second clearfix">

						<!-- main-navigation start -->
						<!-- classes: -->
						<!-- "onclick": Makes the dropdowns open on click, this the default bootstrap behavior e.g. class="main-navigation onclick" -->
						<!-- "animated": Enables animations on dropdowns opening e.g. class="main-navigation animated" -->
						<!-- ================ -->
						<div class="main-navigation main-navigation--mega-menu  animated">
							<nav class="navbar navbar-expand-lg navbar-light p-0">
								<div class="navbar-brand clearfix hidden-lg-up">

									<!-- logo -->
									<div id="logo-mobile" class="logo">
										<a href="index.do"><img id="logo-img-mobile"
											src="images/logo.jpg" width='150' alt="The SSAFY"></a>
											
									</div>
										
								</div>

								<!-- header dropdown buttons -->
								<div
									class="header-dropdown-buttons hidden-lg-up p-0 ml-auto mr-3">
									<div class="btn-group">
										<button type="button"
											class="btn dropdown-toggle dropdown-toggle--no-caret"
											id="header-drop-3" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false">
											<i class="fa fa-search"></i>
										</button>
										<ul
											class="dropdown-menu dropdown-menu-right dropdown-animation"
											aria-labelledby="header-drop-3">
											<li>
												<form role="search" class="search-box margin-clear">
													<div class="form-group has-feedback">
														<input type="text" class="form-control"
															placeholder="Search"> <i
															class="fa fa-search form-control-feedback"></i>
													</div>
												</form>
											</li>
										</ul>
									</div>
								</div>
								<!-- header dropdown buttons end -->

								<button class="navbar-toggler" type="button"
									data-toggle="collapse" data-target="#navbar-collapse-1"
									aria-controls="navbar-collapse-1" aria-expanded="false"
									aria-label="Toggle navigation">
									<span class="navbar-toggler-icon"></span>
								</button>

								<div class="collapse navbar-collapse" id="navbar-collapse-1">
											<ul class="navbar-nav ml-xl-auto">
												<li class="nav-item dropdown  mega-menu mega-menu--wide">
													<a href="vod.do" class="nav-link "
													id="first-dropdown" aria-haspopup="true"
													aria-expanded="false">강의VOD</a>
												</li>
												<li class="nav-item dropdown  mega-menu mega-menu--wide">
													<a href="noticeList.do" class="nav-link "
													id="first-dropdown" aria-haspopup="true"
													aria-expanded="false">공지사항</a>
												</li>
												<li
													class="nav-item dropdown mega-menu mega-menu--wide">
													<!-- <a href="foodBestList.do?id=n" class="nav-link dropdown-toggle" id="second-dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">영양 정보</a> -->
													<a href="news.do?arti_id=1" class="nav-link"
													id="second-dropdown" 
													aria-haspopup="true" aria-expanded="false">오늘의 뉴스</a>
												</li>
												<c:choose>
												<c:when test="${!empty id }">
												<li class="nav-item dropdown  mega-menu mega-menu--wide">
													<a href="qna.do" class="nav-link "
													id="first-dropdown" aria-haspopup="true"
													aria-expanded="false">자유글</a>
												</li>
												<li class="nav-item dropdown "><a
													href="mainSearchCommercial.do" class="nav-link "
													id="second-dropdown" 
													aria-haspopup="true" aria-expanded="false">주변탐방</a> <!-- <a href="foodBestList.do?id=m" class="nav-link dropdown-toggle" id="third-dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">재료 정보</a> -->
												</li>
												<li class="nav-item dropdown  mega-menu mega-menu--narrow">
													<a href="attentionLocationRegForm.do"
													class="nav-link " id="fourth-dropdown"
													aria-haspopup="true"
													aria-expanded="false">관심지역 설정</a>
												</li>
												<li class="nav-item dropdown  mega-menu mega-menu--narrow">
													<a href="mainAttentionLocationSearch.do"
													class="nav-link " id="fourth-dropdown"
													aria-haspopup="true"
													aria-expanded="false">관심지역 둘러보기</a>
												</li>
												</c:when>
											</c:choose>
											</ul>
									<!-- main-menu end -->
								</div>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- header end -->
</div>