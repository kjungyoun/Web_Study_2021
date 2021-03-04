window.onload = function(){
	var question = localStorage.getItem('question');
	var pollBtnDiv = document.getElementsByClassName('content-left-poll-btn')[0];
	var pollDiv = document.querySelector('.content-left-poll');
	
	if(question){
		var answer = localStorage.getItem('answer');
		
		// JSON 형태의 문자열을 JavaScript 타입의 배열로 변환
		var answerArr = JSON.parse(answer);
		var poll = `
			<div class="vote-title">[ 당신의 선택 ]</div>
      <div class="vote-question">${question}</div>
      <div class="vote-answer">
        <ul>`
			for (var i = 0; i < answerArr.length; i++) {
				poll += `<li>
				<label><input type="radio" name="vote-answer" value="${answerArr[i]}" />${answerArr[i]}</label>
				</li>
				`
			}
          
        poll += `</ul>
      </div>
      <div class="vote-button">
        <button class="button btn-primary" onclick="javascript:poll();">투표하기</button>
        <button class="button">결과보기</button>
      </div>
      <div class="vote-date">투표기간 : 20.09.01 ~ 20.09.30</div>
		`
		pollDiv.innerHTML = poll;
        pollBtnDiv.style.display = 'none';
        pollDiv.style.display = 'block';
		
	}else{
		var poll = '현재 진행중인 투표가 없습니다.';
		pollDiv.innerHTML = poll;
		
		pollBtnDiv.style.display = '';
		pollDiv.style.display = '';
	}
	
	document.getElementById('btn-makepoll').onclick = function(){
		window.open('makepoll.html','mp','width=400,height=300');
	}
}