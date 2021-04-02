package com.ssafy.guestbook.model.service;

import com.ssafy.guestbook.model.MemberDto;
import com.ssafy.guestbook.model.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService {
	
	private static MemberService memberService;
	
	private MemberServiceImpl() {};
	
	public static MemberService getMemberService() {
		if(memberService == null)
			memberService = new MemberServiceImpl();
		return memberService;
	}

	@Override
	public void registerMember(MemberDto memberDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public MemberDto login(String userid, String userpwd) {
		// 매개변수 유효성 검사...
		
		return MemberDaoImpl.getMemberDao().login(userid, userpwd);
	}

	@Override
	public MemberDto getMember(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyMember(MemberDto memberDto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteMember(String userid) {
		// TODO Auto-generated method stub

	}

}
