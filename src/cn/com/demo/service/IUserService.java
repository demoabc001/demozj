package cn.com.demo.service;

import java.util.List;

import cn.com.demo.entity.DmUser;

public interface IUserService {
	/**
	 * 获取指定页码的手机集合
	 * */
	public List<DmUser> searchUsersByPage(int pageNo);
	/**
	 * 获取总页码数
	 * */
	public int getSumPageCount();
}
