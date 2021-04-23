<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<html dir="ltr" lang="zxx">
  <head>
    <meta charset="utf-8">
    <title>Happy House | 메인화면</title>
    <meta name="description" content="The Project a Bootstrap-based, Responsive HTML5 Template">
    <meta name="author" content="author">
    <!-- Mobile Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Favicon -->
    <link rel="shortcut icon" href="images/favicon.ico">
    <!-- Web Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,500,500i,700,700i" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=PT+Serif" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="http://happyhouse.jaen.kr/bootstrap/css/bootstrap.css" rel="stylesheet">
    <!-- Font Awesome CSS -->
    <link href="http://happyhouse.jaen.kr/fonts/font-awesome/css/font-awesome.css" rel="stylesheet">
    <!-- Plugins -->
  	<!-- Plugins -->
    <link href="http://happyhouse.jaen.kr/plugins/magnific-popup/magnific-popup.css" rel="stylesheet">
    <link href="http://happyhouse.jaen.kr/css/animations.css" rel="stylesheet">
    <link href="http://happyhouse.jaen.kr/plugins/slick/slick.css" rel="stylesheet">
    
    <!-- The Project's core CSS file -->
    <!-- Use css/rtl_style.css for RTL version -->
    <link href="http://happyhouse.jaen.kr/css/style.css" rel="stylesheet" >
    <!-- The Project's Typography CSS file, includes used fonts -->
    <!-- Used font for body: Roboto -->
    <!-- Used font for headings: Raleway -->
    <!-- Use css/rtl_typography-default.css for RTL version -->
    <link href="http://happyhouse.jaen.kr/css/typography-default.css" rel="stylesheet" >
    <!-- Color Scheme (In order to change the color scheme, replace the blue.css with the color scheme that you prefer) -->
    <link href="http://happyhouse.jaen.kr/css/skins/light_blue.css" rel="stylesheet">
	
	
    <!-- Custom css -->
    <link href="http://happyhouse.jaen.kr/css/custom.css" rel="stylesheet">
    <script type="text/javascript" src='http://happyhouse.jaen.kr/js/jquery-3.3.1.js'></script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCZfdQwv6kw3YqjpTPj7AdoOSaBWK-vgsk&callback=initMap" async defer></script>
	<script type="text/javascript">
								//https://console.developers.google.com    key받기 위한 site
								
								
							var locations =[];
							<c:choose>
								<c:when test='${!empty houses}'>
									<c:forEach items='${houses}' var="house">
									locations.push(['${house.aptName}',${house.lat},${house.lng}])
									</c:forEach>
								</c:when>
								<c:otherwise>
							locations = [
								  	['도봉구'	,	37.6658609	,	127.0317674	],
									 ['은평구'	,	37.6176125	,	126.9227004	],
									 ['동대문구'	,	37.5838012	,	127.0507003	],
									 ['동작구'	,	37.4965037	,	126.9443073	],
									 ['금천구'	,	37.4600969	,	126.9001546	],
									 ['구로구'	,	37.4954856	,	126.858121	],
									 ['종로구'	,	37.5990998	,	126.9861493	],
									 ['강북구'	,	37.6469954	,	127.0147158	],
									 ['중랑구'	,	37.5953795	,	127.0939669	],
									 ['강남구'	,	37.4959854	,	127.0664091	],
									 ['강서구'	,	37.5657617	,	126.8226561	],
									 ['중구'	,	37.5579452	,	126.9941904	],
									 ['강동구'	,	37.5492077	,	127.1464824	],
									 ['광진구'	,	37.5481445	,	127.0857528	],
									 ['마포구'	,	37.5622906	,	126.9087803	],
									 ['서초구'	,	37.4769528	,	127.0378103	],
									 ['성북구'	,	37.606991	,	127.0232185	],
									 ['노원구'	,	37.655264	,	127.0771201	],
									 ['송파구'	,	37.5048534	,	127.1144822	],
									 ['서대문구'	,	37.5820369	,	126.9356665	],
									 ['양천구'	,	37.5270616	,	126.8561534	],
									 ['영등포구'	,	37.520641	,	126.9139242	],
									 ['관악구'	,	37.4653993	,	126.9438071	],
									 ['성동구'	,	37.5506753	,	127.0409622	],
									 ['용산구'	,	37.5311008	,	126.9810742	]
							 ];
												
							</c:otherwise>
							</c:choose>
								var map = null;
								var latitude;
								var longitude;
								
								
///////////////////////////////////////////////////event 설정 및 초기 설정 
								$(function(){
									  ///////////////////////////////시, 도 선택 이벤트 처리 
											$('#city').change(function(){
												var city = $(this).val();
												console.log('city 선택',city)
												$.ajax({
													 url: 'main.do?action=baseAddressGuSearch'
													,data:{
														local : city
													}
													,dataType:'json'
													,success : processGuResult
													,error   : function(err) {
														console.log(err);
													}
												})
											})
											
											///////////////////////////////구, 군 선택 이벤트 처리 
											$('#gu').change(function(){
												var gu = $(this).val();
												console.log('gu 선택',gu)
												$.ajax({
													 url: 'main.do?action=baseAddressDongSearch'
													,data:{
														local : gu
													}
													,dataType:'json'
													,success : processDongResult
													,error   : function(err) {
														console.log(err);
													}
												})
											})
											
											
											///////////////////////////////동 선택 이벤트 처리
											$('#dong').change(function(){
													$('#frm').submit();
											})
								})

								
								function processGuResult(jsondata, status, xhr){
									console.log('결과 도착')
									var list = jsondata;
									console.log(list)
									var contents = $('#gu');
									contents.empty();				//기존에 내용이 있으면 비우기 
									$('<option value="all">시/구/군</option>').appendTo(contents)
										$.each(list, function(index, item) {
											$('<option value="'+item.dongcode+'">'+item.gugun+'</option>').appendTo(contents)
										})
								}
								
								function processDongResult(jsondata, status, xhr){
									console.log('결과 도착')
									var list = jsondata;
									console.log(list)
									var contents = $('#dong');
									contents.empty();				//기존에 내용이 있으면 비우기
									 $('<option value="all">동</option>').appendTo(contents)
										$.each(list, function(index, item) {
											$('<option value="'+item.dong+'">'+item.dong+'</option>').appendTo(contents)
										})
								}
								
								
///////////////////////////////////////////////////맵 설정

 								<c:choose>
										 	<c:when 	test='${!empty mainAttent}'>
														var lat = ${mainAttent.lat}
														var lng = ${mainAttent.lng}
														var zoom = 15
												</c:when>
												<c:otherwise>
												var lat = 37.606991
												var lng = 127.0232185
												var zoom = 11
												</c:otherwise>
								</c:choose>
								function initMap() {
									//getLocation();
									var opt = {
										// google map에 중앙으로 표시할 좌표 설정
										center : {lat:lat,lng: lng},
										zoom : zoom,  //0~ 21  큰 값일 수록  zooming
									};
									//google.maps.Map(map을 그릴 영역, 옵션정보 );
									map = new google.maps.Map(document.getElementById("map"), opt);
									
									var infowindow = new google.maps.InfoWindow();

					    var marker, i;
					    for (i = 0; i < locations.length; i++) {  
					      marker = new google.maps.Marker({
					        id:i,
					        title : locations[i][0],
					        label : locations[i][0],
					        position: new google.maps.LatLng(locations[i][1], locations[i][2]),
					        map: map
					      });

					      google.maps.event.addListener(marker, 'click', (function(marker, i) {
					        return function() {
					          infowindow.setContent(locations[i][0]);
					          infowindow.open(map, marker);
					        }
					      })(marker, i));
					      if(marker)
					      {
					        marker.addListener('click', function() {
					        	 console.log(this.title);
					          map.setZoom(15);
					          map.panTo(this.getPosition());
					        });
					        }
					    }
								}
								
								
								
								/// 현재 위치를 최초위치로.
								
								function showLocation(position) {
									latitude = position.coords.latitude;
									longitude = position.coords.longitude;
									//alert("Latitude : " + latitude + " Longitude: " + longitude);
									//현재 위치 정보를 center로 지정하기 위해 객체 생성
									var hear = new google.maps.LatLng(latitude, longitude);
									map.panTo(hear); //위치 정보를 통해 맵에 표시 
							
								}
							
								function errorHandler(err) {
									if (err.code == 1) {
										alert("Error: Access is denied!");
									} else if (err.code == 2) {
										alert("Error: Position is unavailable!");
									}else if(err.code == 3){
										alert("Erro : Time out");
									}
								}
								function getLocation() {
									if (navigator.geolocation) {
										var options = {
											timeout : 60000,
											enabledHighAccuracy :true
										};
										navigator.geolocation.getCurrentPosition(showLocation,
												errorHandler, options);
									} else {
										alert("Sorry, browser does not support geolocation!");
									}
								}
								</script>
  </head>

  <!-- body classes:  -->
  <!-- "boxed": boxed layout mode e.g. <body class="boxed"> -->
  <!-- "pattern-1 ... pattern-9": background patterns for boxed layout mode e.g. <body class="boxed pattern-1"> -->
  <!-- "transparent-header": makes the header transparent and pulls the banner to top -->
  <!-- "gradient-background-header": applies gradient background to header -->
  <!-- "page-loader-1 ... page-loader-6": add a page loader to the page (more info @components-page-loaders.html) -->
  <body class=" ">

    <!-- scrollToTop -->
    <!-- ================ -->
    <div class="scrollToTop circle"><i class="fa fa-angle-up"></i></div>

    <!-- page wrapper start -->
    <!-- ================ -->
    <div class="page-wrapper">
      <!-- header-container end -->
      	<jsp:include page="menu.jsp"/>
      <!-- header-second end -->  
      
      
      <!-- banner start -->
      <!-- ================ -->
	  			<c:import url="banner.jsp"/>
      <!-- banner end -->
      
      
      
	  <div class="dark-bg section">
     <div class="container-fluid">
          <!-- filters start -->
     <div class="sorting-filters text-center mb-20 d-flex justify-content-center">
     <form class="form-inline" id="frm"  action="main.do">
     	<input type="hidden" name="action" value="searchHouse"/>
			  <!-- <input type="hidden" id="code"  name="code" value="code"/> -->
              <div class="form-group md">
                <select class="form-control" name="city" id="city">
                	 <option value="all" >도/광역시</option>
                  <option value="서울특별시" >서울시</option>
                  <option value="경기도" >경기도</option>
                  <option value="인천광역시" >인천광역시</option>
                  <option value="부산광역시" >부산광역시</option>
                  <option value="대전광역시" >대전광역시</option>
                  <option value="대구광역시" >대구광역시</option>
                  <option value="울산광역시" >울산광역시</option>
                  <option value="세종특별자치시" >세종시</option>
                  <option value="광주광역시" >광주광역시</option>
                  <option value="강원도" >강원도</option>
                  <option value="충청북도" >충청북도</option>
                  <option value="경상북도" >경상북도</option>
                  <option value="경상남도" >경상남도</option>
                  <option value="전라북도" >전라북도</option>
                  <option value="전라남도" >전라남도</option>
                  <option value="제주특별자치도" >제주도</option>
                </select>
              </div>
              <div class="form-group md-1">
                <select class="form-control" name="dongcode" id="gu">
                  <option value="all" >시/구/군</option>
                  <option value="도봉구">도봉구</option>
										        <option value="은평구">은평구</option>
										        <option value="동대문구">동대문구</option>
										        <option value="동작구">동작구</option>
										        <option value="금천구">금천구</option>
										        <option value="구로구">구로구</option>
										        <option value="종로구">종로구</option>
										        <option value="강북구">강북구</option>
										        <option value="중랑구">중랑구</option>
										        <option value="강남구">강남구</option>
										        <option value="강서구">강서구</option>
										        <option value="중구">중구</option>
										        <option value="강동구">강동구</option>
										        <option value="광진구">광진구</option>
										        <option value="마포구">마포구</option>
										        <option value="서초구">서초구</option>
										        <option value="성북구">성북구</option>
										        <option value="노원구">노원구</option>
										        <option value="송파구">송파구</option>
										        <option value="서대문">서대문</option>
										        <option value="양천구">양천구</option>
										        <option value="영등포구">영등포구</option>
										        <option value="관악구">관악구</option>
										        <option value="성동구">성동구</option>
										        <option value="용산구">용산구</option>
                </select>
              </div>
              <div class="form-group md-1">
                <select class="form-control" name="dong" id="dong">
                  <option value="all" >동</option>
                </select>
              </div>
            </form>
          </div>
          <!-- filters end -->
        </div>
      </div>
      <!-- main-container start -->
      <!-- ================ -->
      <section class="main-container">
        <div class="container">
          <div class="row">
            <!-- main start -->
            <!-- ================ -->
            <div class="main col-12">
              <!-- =======상품 정보  시작   반복 시작  ========= -->
              <div class="row grid-space-10">
              					<div  class='col-12 justify-content-center' id="map" style="width: 800px; height: 400px"></div>
              </div>
              
              <!-- =======상품 정보  종료  반복 종료  ========= -->
              <div class="separator-2"></div>
              <!-- ======= 부가 화면 서비스   ========= -->
               <div class="row grid-space-10">
                <div class='col-md-4'>
              				<img src='images/sideAD.jpg'>
              		</div>
              		<div class="col-md-4">
                		<h3 class="title">지혜롭게 내집 마련하기</h3>
                  <div class="separator-2"></div>
                  	<ul class="nav flex-column list-style-icons">
                     <li class="nav-item"><a class="nav-link " href="news.do?arti_id=6"><i class=" fa fa-won"></i> 가용자금 확인 및 대출 계획</a></li>
                      <li class="nav-item"><a class="nav-link " href="news.do?arti_id=7"><i class="fa fa-home"></i> 집 종류 및 지역 선택</a></li>
                      <li class="nav-item"><a class="nav-link " href="news.do?arti_id=8"><i class="fa fa-line-chart"></i>정보 수집 & 시세파악</a></li>
                      <li class="nav-item"><a class="nav-link " href="news.do?arti_id=9"><i class="fa fa-search-plus"></i> 부동산 방문 & 집구경</a></li>
                      <li class="nav-item"><a class="nav-link " href="news.do?arti_id=10"><i class="fa fa-pencil"></i> 계약 및 잔금 치르기</a></li>
                      <li class="nav-item"><a class="nav-link " href="news.do?arti_id=11"><i class="fa fa-file-text-o"></i> 소유권 이전등기</a></li>
                      <li class="nav-item"><a class="nav-link " href="news.do?arti_id=12"><i class="fa fa-gear"></i> 인테리어 공사</a></li>
                    </ul>
                  <div class="separator-2"></div>
              		</div>
                <div class="col-md-4">
                   	<h3 class="title">오늘의 뉴스</h3>
                   	<div class="separator-2"></div>
																					<h4><a href="news.do?arti_id=5" class="NPI=a:title,i:0004615690" title="서울 아파트, 매매·전세 다른 양상…상승둔화vs상승확대">서울 아파트, 매매·전세 다른 양상…상승..</a><span>아시아경제</span></h4>
																					<ul>
																						<li><a href="news.do?arti_id=1" class="small margin-clear" title="서울 입주 2년차 아파트 전셋값 1억400만원 올라">서울 입주 2년차 아파트 전셋값 1억400만원 올라</a> <span>한겨레</span></li>
																						<li><a href="news.do?arti_id=2" class="small margin-clear" title="12·16發 거래한파…매수자가 사라졌다">12·16發 거래한파…매수자가 사라졌다</a> <span>아시아경제</span></li>
																						<li><a href="news.do?arti_id=3" class="small margin-clear" title="재건축 대안 뜨는 수직증축 리모델링… 추진 속도는 &lsquo;글쎄요&lsquo;">재건축 대안 뜨는 수직증축 리모델링… 추진 속..</a> <span>동아일보</span></li>
																						<li><a href="news.do?arti_id=4" class="small margin-clear" title="고가기준 9억, 서울 아파트 중간값 됐다">고가기준 9억, 서울 아파트 중간값 됐다</a> <span>동아일보</span></li>
																					</ul>
																				
                </div>
               
              		
             </div>
              <!-- =======상품 정보  종료  반복 종료  ========= -->
              <!-- pagination start -->
              <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                   
                </ul>
              </nav>
<%--               <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Previous" onclick="pagelist(${pageBean.pageNo-1})">
                      <i aria-hidden="true" class="fa fa-angle-left"></i>
                      <span class="sr-only">Previous</span>
                    </a>
                  </li>
                  <li class="page-item active"><a class="page-link" href="#" onclick="pagelist(${pageBean.pageNo+0})">${pageBean.pageNo+0}</a></li>
                  <li class="page-item"><a class="page-link" href="#" onclick="pagelist(${pageBean.pageNo+1})">${pageBean.pageNo+1}</a></li>
                  <li class="page-item"><a class="page-link" href="#" onclick="pagelist(${pageBean.pageNo+2})">${pageBean.pageNo+2}</a></li>
                  <li class="page-item"><a class="page-link" href="#" onclick="pagelist(${pageBean.pageNo+3})">${pageBean.pageNo+3}</a></li>
                  <li class="page-item"><a class="page-link" href="#" onclick="pagelist(${pageBean.pageNo+4})">${pageBean.pageNo+4}</a></li>
                  <li class="page-item">
                    <a class="page-link" href="#" aria-label="Next" onclick="pagelist(${pageBean.pageNo+1})">
                      <i aria-hidden="true" class="fa fa-angle-right"></i>
                      <span class="sr-only">Next</span>
                    </a>
                  </li>
                </ul>
              </nav> --%>
              <!-- pagination end -->

            </div>
            <!-- main end -->

          </div>
        </div>
      </section>
      <!-- main-container end -->
      
      <!-- main-container end -->
      <c:import url="footer.jsp"/>
      <!-- footer end -->
    </div>
    <!-- page-wrapper end -->

    <!-- JavaScript files placed at the end of the document so the pages load faster -->
    <!-- ================================================== -->
    <!-- Jquery and Bootstap core js files -->
    <script src="http://happyhouse.jaen.kr/plugins/jquery.min.js"></script>
    <script src="http://happyhouse.jaen.kr/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Isotope javascript -->
    <script src="http://happyhouse.jaen.kr/plugins/isotope/imagesloaded.pkgd.min.js"></script>
    <script src="http://happyhouse.jaen.kr/plugins/isotope/isotope.pkgd.min.js"></script>
    
    <!-- Magnific Popup javascript -->
    <script src="http://happyhouse.jaen.kr/plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
    <!-- Appear javascript -->
    <script src="http://happyhouse.jaen.kr/plugins/waypoints/jquery.waypoints.min.js"></script>
    <script src="http://happyhouse.jaen.kr/plugins/waypoints/sticky.min.js"></script>
    <!-- Count To javascript -->
    <script src="http://happyhouse.jaen.kr/plugins/countTo/jquery.countTo.js"></script>
    <!-- Slick carousel javascript -->
    <script src="http://happyhouse.jaen.kr/plugins/slick/slick.min.js"></script>
    <!-- Initialization of Plugins -->
    <script src="http://happyhouse.jaen.kr/js/template.js"></script>
    <!-- Custom Scripts -->
    <script src="http://happyhouse.jaen.kr/js/custom.js"></script>

  </body>
</html>