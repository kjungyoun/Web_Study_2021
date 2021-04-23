package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.dto.BaseAddress;
import com.ssafy.model.dto.HouseInfo;
import com.ssafy.model.dto.HousePageBean;

public interface HouseService {
	List<HouseInfo> searchHouseInfo(String dong);
	public List<BaseAddress> searchGu(String city) ;
	public List<BaseAddress> searchDong(String local);
}