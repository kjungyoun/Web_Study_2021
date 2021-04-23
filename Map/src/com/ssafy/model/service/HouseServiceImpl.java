package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.dao.HouseDao;
import com.ssafy.model.dao.HouseDaoImpl;
import com.ssafy.model.dto.BaseAddress;
import com.ssafy.model.dto.HouseException;
import com.ssafy.model.dto.HouseInfo;
import com.ssafy.model.dto.HousePageBean;

public class HouseServiceImpl implements HouseService  {
	private HouseDao  dao = new HouseDaoImpl();
	@Override
	public List<HouseInfo> searchHouseInfo(String dong) {
		try {
			return dao.searchHouseInfo(dong);
		} catch (Exception e) {
			throw new HouseException("주택 정보를 조회하는 중 오류 발생");
		}
	}
	public List<BaseAddress> searchGu(String city) {
		try {
			return dao.searchGu(city);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HouseException("도시 내에 있는 구 정보를 조회하는 중 오류 발생");
		}
	}
	public List<BaseAddress> searchDong(String local){
		try {
			return dao.searchDong(local);
		} catch (Exception e) {
			throw new HouseException("고 내에 있는 동 정보를 조회하는 중 오류 발생");
		}
	}
}
