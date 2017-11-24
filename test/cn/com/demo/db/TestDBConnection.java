package cn.com.demo.db;

import java.sql.Connection;

import junit.framework.Assert;

import org.junit.Test;

public class TestDBConnection {
	@Test
	public void testGetConnection(){
		// ������Ϊ��
		// ʵ��
		Connection conn = DBConnection.getInstance().getConnection();
		Assert.assertNotNull(conn);
	}
	@Test
    public void testGetInstance(){
    	// ׼������ֵ
    	// ��ȡʵ��ֵ
    	// �ж�������ʵ���Ƿ�һ�����ó����ԵĽ��
    	// (����������ʵ��һ��)
    	
    	Object obj = DBConnection.getInstance();
    	Assert.assertTrue(obj != null && obj instanceof DBConnection);
    }
}
