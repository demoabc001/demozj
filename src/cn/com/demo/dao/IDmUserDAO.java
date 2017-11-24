package cn.com.demo.dao;

import java.util.List;

import cn.com.demo.entity.DmUser;

public interface IDmUserDAO {
	public List<DmUser> findUsersByRange(int begin,int end);
    public int findSumRecordCount();
}
