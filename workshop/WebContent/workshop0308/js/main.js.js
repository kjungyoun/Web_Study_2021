window.onload = function() {

	// 로그인 구현
	var menu = document.getElementsByClassName("header_nav_menuitem");
	var profile = document.getElementsByClassName("profile_img");
	var login = menu[0];
	login.addEventListener('click', function(e) {
		var id = prompt("아이디 입력");
		var psw = prompt('비밀번호 입력');
		if (id == 'ssafy' && psw == '1234') {
			alert('로그인 성공!!');
			for (var i = 0; i < menu.length; i++) {
				if (i < 2) {
					menu[i].style.display = 'none'
				} else {
					menu[i].style.display = 'inline'
				}
			}
			profile[0].style.display = 'none';
			profile[1].style.display = 'block';
		} else {
			alert("아이디와 비밀번호를 다시 확인하세요");
		}
	})

	// 전국 매장 펼치기
	var store = document.getElementsByClassName('store_display');
	var li = document.getElementsByClassName('store_item_sub');
	store[0].addEventListener('click', function(e) {
		store[0].style.display = 'none';
		store[1].style.display = 'block';
		for (var i = 0; i < li.length; i++) {
			li[i].style.display = "block";
		}
	});
	
	// 전국 매장 접기
	store[1].addEventListener('click', function(e) {
		store[0].style.display = 'block';
		store[1].style.display = 'none';
		for (var i = 0; i < li.length; i++) {
			li[i].style.display = "none";
		}
	});
	
	// 각 도시 매장 펼치기 & 접기
	// 서울
	var city = document.getElementsByClassName('store_area');
	city[0].addEventListener('click', function(e) {
		if(li[0].style.display == 'block') li[0].style.display = 'none';
		else li[0].style.display = 'block';
	})
	// 대전
	var city = document.getElementsByClassName('store_area');
	city[1].addEventListener('click', function(e) {
		if(li[1].style.display == 'block') li[1].style.display = 'none';
		else li[1].style.display = 'block';
	})
	// 구미
	var city = document.getElementsByClassName('store_area');
	city[2].addEventListener('click', function(e) {
		if(li[2].style.display == 'block') li[2].style.display = 'none';
		else li[2].style.display = 'block';
	})
	// 광주
	var city = document.getElementsByClassName('store_area');
	city[3].addEventListener('click', function(e) {
		if(li[3].style.display == 'block') li[3].style.display = 'none';
		else li[3].style.display = 'block';
	})
	
	// 관리자 페이지 구현
	var link = document.getElementsByClassName('nav_link')[4];
	link.addEventListener('click', function(e) {
		window.open('makepoll.html', 'poll', 'width=600, height=500');
	})
	
	// main 투표 구현
	var item = localStorage.getItem('poll');
	var vote = document.querySelector('.vote');
	var voteIn = document.querySelector('.vote-in');
	
	if(item){
		// JSON 객체를 javaScript에서 사용할 수 있는 객체로 변환
		var parsedIt = JSON.parse(item);
		var tag= `
		<div class="vote_question">${parsedIt.question}</div>
	          <div class="vote_answer">
	            <ul>
	            `
				for (var i = 0; i < parsedIt.answer.length; i++) {
					tag +=
						`
						<li>
	                <input type="radio" name="vote_answer" id="" />${parsedIt.answer[i]}
	              </li>
						`
				}
				var startArr = parsedIt.start.split("-");
				var endArr = parsedIt.end.split("-");
				
	           tag +=  `
	            </ul>
	          </div>
	          <div class="vote_button">
	            <button class="button btn_primary">투표하기</button>
	            <button class="button">결과보기</button>
	          </div>
	          <div class="vote_date">투표기간 : ${startArr[0].substring(2, 4)}.${startArr[1]}.${startArr[2]} ~ ${endArr[0].substring(2, 4)}.${endArr[1]}.${endArr[2]}</div>
		`
	   voteIn.innerHTML = tag;
		
		
	}else{
		var title = document.querySelector('.vote_title');
		voteIn.style.display = "none";
		var poll = '진행중인 투표가 없습니다.';
		title.innerHTML = poll;
	}
	
	
}