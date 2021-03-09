$(function(){
	// 추가 버튼 구현
	$('#btn-add').click(function() {
		
		// 아래처럼 list와 item을 분리하여 append 하지 않으면 제거 버튼을 누를시 전부 사라지는 현상 발생
		var div = $('<div class="poll-answer-item">')
		.append('<input type="text" name="answer"/>')
		.append('<button class="remove-ans">제거</button>')
		
		$('#poll-answer-list').append(div)
	})
	
	$(document).on('click','.remove-ans', function() {
		$(this).parent('.poll-answer-item').remove()
	})
	
	// 투표 생성 버튼 구현
	var sD = new Date().toISOString().substr(0, 10)
	$('#start-date').val(sD)
	
	$('#btn-make').click(function() {
		if(!$('#question').val()){
			alert('질문 내용을 입력하시오.')
			$('#question').focus()
			return;
		}
		
		$('input[name=answer]').each(function(idx, item) {
			if(!$(item).val()){
				alert('질문을 입력하시오')
				$(item).focus();
				return;
			}
		})
		
		if(!$('#end-date').val()){
			alert('종료일을 입력하시오.')
			$('#end-date').focus();
		}
		
		var answerArr = [];
		$('input[name=answer]').each(function(idx, item) {
			answerArr.push($(item).val());
		})
		
		var object = {
			question : $('#question').val(),
			answer : answerArr,
			start : $('#start-date').val(),
			end : $('#end-date').val()
		}
		
		localStorage.setItem('poll', JSON.stringify(object));
		alert('투표를 생성합니다.')
		opener.location.reload()
		self.close();
		
		
	})
})