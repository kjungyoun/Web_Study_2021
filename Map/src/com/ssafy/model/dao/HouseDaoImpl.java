package com.ssafy.model.dao;

import java.awt.image.DataBufferInt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.model.dto.BaseAddress;
import com.ssafy.model.dto.HouseInfo;
import com.ssafy.model.dto.HousePageBean;
import com.ssafy.util.DBUtil;

public class HouseDaoImpl implements HouseDao {

	@Override
	public List<HouseInfo> searchHouseInfo(String dong)  throws SQLException{
		Connection con = null;
		PreparedStatement stmt= null;
		ResultSet	rs = null;
		List<HouseInfo> list = new ArrayList<HouseInfo>(20);
		try {
			con = DBUtil.getConnection();
			String sql = "select no, dong, AptName, code, lat, lng from houseinfo where dong= ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, dong);
			rs = stmt.executeQuery();
			while(rs.next()) {
				HouseInfo info = new HouseInfo();
				info.setNo(rs.getInt("no"));
				info.setDong(rs.getString("dong"));
				info.setAptName(rs.getString("AptName"));
				info.setCode(rs.getInt("code"));
				info.setLat(rs.getString("lat"));
				info.setLng(rs.getString("lng"));
				list.add(info);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
		return list;
	}

	@Override
	public List<BaseAddress> searchGu(String city) throws SQLException{
		Connection con = null;
		PreparedStatement stmt= null;
		ResultSet	rs = null;
		List<BaseAddress> list = new ArrayList<BaseAddress>(20);
		try {
			con = DBUtil.getConnection();
			String sql = "select distinct  gugun, dongcode  from baseaddress where city=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, city);
			rs = stmt.executeQuery();
			while(rs.next()) {
				BaseAddress ba = new BaseAddress();
				ba.setGugun(rs.getString("gugun"));
				ba.setDongcode(rs.getString("dongcode"));
				list.add(ba);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
		return list;
	}

	@Override
	public List<BaseAddress> searchDong(String local)  throws SQLException{
		Connection con = null;
		PreparedStatement stmt= null;
		ResultSet	rs = null;
		List<BaseAddress> list = new ArrayList<BaseAddress>(20);
		try {
			con = DBUtil.getConnection();
			String sql = "select   dong  from baseaddress where dongcode=?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, local);
			rs = stmt.executeQuery();
			while(rs.next()) {
				BaseAddress ba = new BaseAddress();
				ba.setDong(rs.getString("dong"));
				ba.setDongcode(local);
				list.add(ba);
			}
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(con);
		}
		return list;
	}

	

}
