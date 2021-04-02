package com.ssafy.guestbook.model.service;

import java.util.List;

import com.ssafy.guestbook.model.GuestBookDto;
import com.ssafy.guestbook.model.dao.GuestBookDaoImpl;


public class GuestBookServiceImpl implements GuestBookService {
	
	public static GuestBookService guestBookService;
	
	private GuestBookServiceImpl() {}
	
	public static GuestBookService getGuestBookService() {
		if(guestBookService == null)
			guestBookService = new GuestBookServiceImpl();
		return guestBookService;
	}

	
	@Override
	public void registerArticle(GuestBookDto guestBookDto)throws Exception {
		GuestBookDaoImpl.getGuestBookDao().registerArticle(guestBookDto);
	}

	@Override
	public List<GuestBookDto> listArticle(String key, String word)throws Exception {
		key = key == null? "" : key;
		word = word == null? "" : word;
		
		return GuestBookDaoImpl.getGuestBookDao().listArticle(key, word);
	}

	@Override
	public GuestBookDto getArticle(int articleNo) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyAricle(GuestBookDto guestBookDto)throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteArticle(int articleNo) throws Exception{
		// TODO Auto-generated method stub

	}

}
