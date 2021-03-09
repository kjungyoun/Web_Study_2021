window.onload = function(){
	// 답변 항목 추가
	var add = document.getElementById('btn-add');
	add.addEventListener('click', function(e) {
		
		var list = document.getElementById("poll-answer-list");
		var item = document.createElement('div');
		item.setAttribute('class', 'poll-answer-item');
		
		var input = document.createElement('input');
		input.setAttribute('type', 'text');
		input.setAttribute('name', 'answer');
		
		var btn = document.createElement('button');
		btn.addEventListener('click', function(e) {
			// parentNode 가 아닌 parent 로 할 경우 추가된 답변 항목 전부 삭제됨
			// parentNode 는 자신의 상위 태그 단 하나만을 가리킴
			var parent = this.parentNode;
			list.removeChild(parent);
		})
		btn.appendChild(document.createTextNode('삭제'))
		
		item.appendChild(input);
		item.appendChild(btn);
		
		list.appendChild(item);
	})
	
	
	// 시작일자 기본값을 현재 날짜로 설정
	var start = document.getElementById('start-date');
	start.value = new Date().toISOString().substr(0,10);
	
	// 투표 생성
	var make = document.getElementById('btn-make');
	make.addEventListener('click', function(e) {
		var question = document.getElementById('question');
		if(!question.value){
			alert('질문 내용을 입력해주세요');
			question.focus();
			return;
		}
		var answer = document.getElementsByName('answer');
		
		for (var i = 0; i < answer.length; i++) {
			if(!answer[i].value){
				alert('답변 내용을 입력해주세요');
				answer[i].focus();
				return;
			}
		}
		// 배열에 각 객체의 데이터 저장
		var answerArr =[];
		for (var i = 0; i < answer.length; i++) {
			answerArr[i] = answer[i].value;
		}
		
		var end = document.getElementById('end-date');
		if(!end.value){
			alert('종료일을 입력해주세요');
			end.focus();
			return;
		}
		
		var item = {
			question: question.value,
			answer: answerArr,
			start: start.value,
			end: end.value
		}
		
		localStorage.setItem('poll',JSON.stringify(item));
		alert('투표를 생성합니다.');
		opener.location.reload();
		self.close();
	})
}