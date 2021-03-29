//고전 이벤트 모델방식
function sum(){
	var xhr=new XMLHttpRequest();
	//코드추가
	
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			//코드추가
		}
	}
	xhr.open("GET", url, true);
	xhr.send(null);
}












