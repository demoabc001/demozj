package cn.com.demo.dao;

import java.util.List;

import cn.com.demo.entity.DmMobileInfo;

public interface IDmMobileInfoDAO {
	// 获取总记录数
	public int getRecordCount();
	// 都包含begin和end
	public List<DmMobileInfo> findByRange(int begin,int end);
	/**
	 * 查询所有的手机列表
	 * */
    public List<DmMobileInfo> findAll();
    /**
     * 根据手机的id，修改手机对象中其他的所有属性
     * */
    public void update(DmMobileInfo mobile);
    /**
     * 根据id查找对应的手机对象
     * */
    public DmMobileInfo findById(int id);
}
