window.onload = function(){
	/* 답변 항목 추가 구현 */
	document.querySelector('#btn-add').addEventListener('click', function() {
		var listDiv = document.querySelector('#poll-answer-list');
		
		// <div></div> 를 만듬
		var divEl = document.createElement('div');
		// <div class ="poll-answer-item"></div> 로 생성
		divEl.setAttribute('class', 'poll-answer-item');
		
		var inputEl = document.createElement('input');
		inputEl.setAttribute('type', 'text');
		inputEl.setAttribute('name', 'answer');
		
		var buttonEl = document.createElement('button');
		buttonEl.setAttribute('class', 'button');
		
		buttonEl.addEventListener('click', function() {
			var parent = this.parentNode;
			listDiv.removeChild(parent);
		});
		buttonEl.appendChild(document.createTextNode('제거'));
		
		divEl.appendChild(inputEl);
		divEl.appendChild(buttonEl);
		
		listDiv.appendChild(divEl);
	});
	
	/* 투표 생성 구현 */
	document.querySelector('#btn-make').addEventListener('click',function(){
		var question = document.getElementById('question');
		if(!question.value){
			alert('질문 내용을 입력해주세요. 개빡치기전에..');
			question.focus();
			return;
		}
		var answer = document.getElementsByName('answer');
		for (var i = 0; i < answer.length; i++) {
			if(!answer[i].value){
				alert("답변 항목 입력하라고");
				answer[i].focus();
				return
			}
		}
		
		var answerArr = [];
		for (var i = 0; i < answer.length; i++) {
			answerArr.push(answer[i].value);
		}
		
		//localStorage에 저장
		localStorage.setItem('question',question.value);
		// JSON.stringify : JavaScript의 object를 JSON 형태의 문자열로 변경하는 기능
		localStorage.setItem('answer', JSON.stringify(answerArr));
		
		alert('투표를 생성합니다.');
		// 새 창을 연 page (부모 페이지)를 새로 load 
		opener.location.reload();
		// 새 창을 닫기
		self.close();
	});
}