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
    	// ׼���ò�������
    	// ׼�����������
    	// ��ȡʵ�ʽ��
    	// ����
    	
    	
    	// ׼���ò�������
    	int id=3;
    	// ׼�����������
    	String name = "С������";
    	// ��ȡʵ�ʽ��
    	String actName = mbDAO.findById(id).getMbName();
    	// ����
    	Assert.assertEquals(name, actName);
    }
}
