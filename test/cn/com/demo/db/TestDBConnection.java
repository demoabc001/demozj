package cn.com.demo.db;

import java.sql.Connection;

import junit.framework.Assert;

import org.junit.Test;

public class TestDBConnection {
	@Test
	public void testGetConnection(){
		// 期望不为空
		// 实际
		Connection conn = DBConnection.getInstance().getConnection();
		Assert.assertNotNull(conn);
	}
	@Test
    public void testGetInstance(){
    	// 准备期望值
    	// 获取实际值
    	// 判断期望和实际是否一样，得出测试的结果
    	// (断言期望和实际一致)
    	
    	Object obj = DBConnection.getInstance();
    	Assert.assertTrue(obj != null && obj instanceof DBConnection);
    }
}
