package com.ssafy.model.dto;

public class HouseException extends RuntimeException {
	public HouseException() {
		super("수행 중 오류 발생");
	}
	public HouseException(String msg) {
		super(msg);
	}
}
