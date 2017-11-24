package cn.com.demo.dao.impl;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cn.com.demo.dao.IDmMobileInfoDAO;
import cn.com.demo.entity.DmMobileInfo;

public class TestDmMobileInfoDAOImpl {
	private IDmMobileInfoDAO mbDAO = null;
	@Before
	public void init(){
		mbDAO = new DmMobileInfoDAOImpl();
	}
	@After
	public void destory(){
		mbDAO = null;
	}
	@Test
	public void testUpdate(){
		int id=3;
		double newPrice = 500.99;
		DmMobileInfo info = mbDAO.findById(id);
		info.setMbPrice(newPrice);
		mbDAO.update(info);
		
		info = mbDAO.findById(id);
		
		Assert.assertEquals(newPrice, info.getMbPrice());
	}
	@Test
    public void testFindById(){
    	// 准备好测试数据
    	// 准备好期望结果
    	// 获取实际结果
    	// 断言
    	
    	
    	// 准备好测试数据
    	int id=3;
    	// 准备好期望结果
    	String name = "小米入门";
    	// 获取实际结果
    	String actName = mbDAO.findById(id).getMbName();
    	// 断言
    	Assert.assertEquals(name, actName);
    }
}
