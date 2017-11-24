package cn.com.test;

import ognl.Ognl;
import cn.com.demo.entity.DmMobileInfo;

public class TestMain {

	public static void main(String[] args) throws Exception{
		DmMobileInfo info = new DmMobileInfo();
		info.setMbColor("ºìÉ«");
		
		Object obj = Ognl.getValue("mbColor", info);
		System.out.println(obj);
	}

}
