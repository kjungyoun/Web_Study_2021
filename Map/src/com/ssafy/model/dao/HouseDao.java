package com.ssafy.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.model.dto.BaseAddress;
import com.ssafy.model.dto.HouseInfo;
import com.ssafy.model.dto.HousePageBean;

public interface HouseDao {
	public List<HouseInfo> searchHouseInfo(String dong) throws SQLException;
	public List<BaseAddress> searchGu(String city) throws SQLException;
	public List<BaseAddress> searchDong(String local) throws SQLException;
}
