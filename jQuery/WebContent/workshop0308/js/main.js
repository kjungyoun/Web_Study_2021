$(function(){
	// 로그인 구현
	$('.nav_link:first').click(function() {
		var id = prompt("아이디 입력");
		var psw = prompt('비밀번호 입력');
		if (id == 'ssafy' && psw == '1234') {
			alert('로그인 성공!!');
			$('.nav_link').each(function(index, item) {
				if(index<2){
					$(item).css('display', 'none');
				}else{
					$(item).css('display','inline');
				}
			})
			$('.profile_img:first').css('display','none');
			$('.profile_img:last').css('display', 'block');
		}else{
			alert('아이디와 비밀번호를 확인하세요!');
		}
	})
	
	// 전국 매장 펼치기 & 접기 with Toggle
	var count = 0;
	$('.store_display').click(function() {
		$('.store_item_sub').toggle('slow', function() {
		})
		count++;
		if(count%2 == 0)$('.store_display').html('전국매장펼치기')
		else $('.store_display').html('전국매장접기')
	})
	
	// 도시별 매장 펼치기 & 접기 with Toggle
	$('.store_area').each(function(idx, item) {
		$(item).click(function() {
			// next는 현재 자신 객체의 형제 객체를 의미
			$(item).next('.store_item_sub').toggle('slow', function() {
				
			})
		})
	})
	
	// 관리자 페이지 구현
	$('.nav_link:last').click(function() {
		window.open('makepoll.html','poll','width= 600, height= 500')
	})
	
	// main poll 구현
	var question = localStorage.getItem('poll')
	
	if(question){
		var parsed = JSON.parse(question);
		var tag= `
			<div class="vote_question">${parsed.question}</div>
		          <div class="vote_answer">
		            <ul>
		            `
					$.each(parsed.answer, function(idx, item) {
						tag +=
							`
							<li>
		                <input type="radio" name="vote_answer" id="" />${item}
		              </li>
							`
					})
					
					var startArr = parsed.start.split("-");
					var endArr = parsed.end.split("-");
					
		           tag +=  `
		            </ul>
		          </div>
		          <div class="vote_button">
		            <button class="button btn_primary">투표하기</button>
		            <button class="button">결과보기</button>
		          </div>
		          <div class="vote_date">투표기간 : ${startArr[0].substring(2, 4)}.${startArr[1]}.${startArr[2]} ~ ${endArr[0].substring(2, 4)}.${endArr[1]}.${endArr[2]}</div>
			`
		$('.vote-in').append(tag)
		        	   
	}else{
		$('.vote_title').html('등록된 투표가 없습니다.')
	}
})