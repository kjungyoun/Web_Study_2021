package com.ssafy.guestbook.model.service;

import java.util.List;

import com.ssafy.guestbook.model.GuestBookDto;

public interface GuestBookService {

//	글작성
	void registerArticle(GuestBookDto guestBookDto)throws Exception;
	
//	글목록 -> 검색을 위함
	List<GuestBookDto> listArticle(String key, String word)throws Exception;
	
//	글수정을 위한 글얻기
	GuestBookDto getArticle(int articleNo)throws Exception;
	
//	글수정
	void modifyAricle(GuestBookDto guestBookDto)throws Exception;
	
//	글삭제
	void deleteArticle(int articleNo)throws Exception;
}
