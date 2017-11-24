package cn.com.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.com.demo.dao.IDmMobileInfoDAO;
import cn.com.demo.db.DBConnection;
import cn.com.demo.entity.DmMobileInfo;

public class DmMobileInfoDAOImpl implements IDmMobileInfoDAO {

	@Override
	public List<DmMobileInfo> findAll() {
		List<DmMobileInfo> mobileList = null;

		DBConnection dbConn = DBConnection.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbConn.getConnection();
			String sql = "select * from dm_mobile_info order by mb_id";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				mobileList = new ArrayList<DmMobileInfo>();

				DmMobileInfo info = null;
				while (rs.next()) {
					info = new DmMobileInfo();
					info.setMbColor(rs.getString("mb_color"));
					info.setMbId(rs.getInt("mb_id"));
					info.setMbMemory(rs.getInt("mb_memory"));
					info.setMbName(rs.getString("mb_name"));
					info.setMbPrice(rs.getDouble("mb_price"));

					mobileList.add(info);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			dbConn.close(conn, pstmt, rs);
		}

		return mobileList;
	}

	@Override
	public void update(DmMobileInfo mobile) {
		DBConnection dbConn = DBConnection.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = dbConn.getConnection();
			String sql = "update dm_mobile_info set "+
			              " mb_name=?,mb_memory=?,"+
					      "mb_color=?,mb_price=? "+
			              " where mb_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mobile.getMbName());
			pstmt.setInt(2, mobile.getMbMemory());
			pstmt.setString(3, mobile.getMbColor());
			pstmt.setDouble(4, mobile.getMbPrice());
			pstmt.setInt(5, mobile.getMbId());
			
			int count = pstmt.executeUpdate();
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
	}

	@Override
	public DmMobileInfo findById(int id) {
		DmMobileInfo info = null;

		DBConnection dbConn = DBConnection.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbConn.getConnection();
			String sql = "select * from dm_mobile_info where mb_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				info = new DmMobileInfo();
				info.setMbColor(rs.getString("mb_color"));
				info.setMbId(rs.getInt("mb_id"));
				info.setMbMemory(rs.getInt("mb_memory"));
				info.setMbName(rs.getString("mb_name"));
				info.setMbPrice(rs.getDouble("mb_price"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			dbConn.close(conn, pstmt, rs);
		}

		return info;
	}

	@Override
	public List<DmMobileInfo> findByRange(int begin, int end) {
		List<DmMobileInfo> mobileList = null;

		DBConnection dbConn = DBConnection.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbConn.getConnection();
			String sql = "select * from (select rownum num_, info.* from dm_mobile_info info order by mb_id) where num_<=? and num_>=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, begin);
			
			rs = pstmt.executeQuery();
			if (rs != null) {
				mobileList = new ArrayList<DmMobileInfo>();

				DmMobileInfo info = null;
				while (rs.next()) {
					info = new DmMobileInfo();
					info.setMbColor(rs.getString("mb_color"));
					info.setMbId(rs.getInt("mb_id"));
					info.setMbMemory(rs.getInt("mb_memory"));
					info.setMbName(rs.getString("mb_name"));
					info.setMbPrice(rs.getDouble("mb_price"));

					mobileList.add(info);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			dbConn.close(conn, pstmt, rs);
		}

		return mobileList;
	}

	public int getRecordCount(){
		int count = 0;
		DBConnection dbConn = DBConnection.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbConn.getConnection();
			String sql = "select count(*) from dm_mobile_info ";
			// select mb_id from dm_mobile_info
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return count;
	}
}
