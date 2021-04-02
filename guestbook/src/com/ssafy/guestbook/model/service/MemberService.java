package com.ssafy.guestbook.model.service;

import com.ssafy.guestbook.model.MemberDto;

public interface MemberService {

//	회원가입
	void registerMember(MemberDto memberDto);
	
//	로그인
	MemberDto login(String userid, String userpwd);
	
//	회원정보 수정을 위한 회원의 모든 정보 얻기
	MemberDto getMember(String userid);
	
//	회원정보 수정 -> 로그인 되었을 때만
	void modifyMember(MemberDto memberDto);
	
//	회원탈퇴 -> 로그인 되었을 때만
	void deleteMember(String userid);
	
}
