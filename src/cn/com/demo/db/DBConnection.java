package cn.com.demo.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
	private static DBConnection DB_CONN;
	private String userName;
	private String password;
	private String url;
	
    private DBConnection(){
    	try {
			this.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static DBConnection getInstance(){
    	if(DB_CONN == null){
    		DB_CONN = new DBConnection();
    	}
    	return DB_CONN;
    }
    // ∂¡≈‰÷√Œƒº˛
    private void init() throws Exception{
    	Properties prop = new Properties();
    	InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("config/db.properties");
    	prop.load(in);
    	
    	this.userName = prop.getProperty("userName");
    	this.password = prop.getProperty("password");
    	this.url = prop.getProperty("url");
    	
    	String driverName = prop.getProperty("driverName");
    	Class.forName(driverName);
    }
    
    public Connection getConnection(){
    	Connection conn = null;
    	
    	try {
			conn = DriverManager.getConnection(this.url,this.userName,this.password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return conn;
    }
    
    public void close(Connection conn,Statement stmt, ResultSet rs){
    	try{
    		if(rs != null) rs.close();
    		if(stmt != null) stmt.close();
    		if(conn != null) conn.close();
    	}catch(Exception e){}
    }
}
