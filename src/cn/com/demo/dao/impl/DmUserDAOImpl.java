package cn.com.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.com.demo.dao.IDmUserDAO;
import cn.com.demo.db.DBConnection;
import cn.com.demo.entity.DmMobileInfo;
import cn.com.demo.entity.DmUser;

public class DmUserDAOImpl implements IDmUserDAO {

	@Override
	public List<DmUser> findUsersByRange(int begin,int end) {
		List<DmUser> mobileList = null;

		DBConnection dbConn = DBConnection.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbConn.getConnection();
			String sql = "select * from (select rownum num_, info.* from dm_user info order by ur_id) where num_<=? and num_>=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, begin);
			
			rs = pstmt.executeQuery();
			if (rs != null) {
				mobileList = new ArrayList<DmUser>();

				DmUser info = null;
				while (rs.next()) {
					info = new DmUser();
					info.setUrAge(rs.getInt("ur_age"));
					info.setUrId(rs.getInt("ur_id"));
					info.setUrName(rs.getString("ur_name"));
					info.setUrPassword(rs.getString("ur_password"));
					info.setUrSex(rs.getString("ur_sex"));
					info.setUrUserName(rs.getString("ur_user_name"));

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
	public int findSumRecordCount() {
		int count = 0;
		DBConnection dbConn = DBConnection.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbConn.getConnection();
			String sql = "select count(*) from dm_user ";
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
