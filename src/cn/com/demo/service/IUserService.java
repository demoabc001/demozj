package cn.com.demo.service;

import java.util.List;

import cn.com.demo.entity.DmUser;

public interface IUserService {
	/**
	 * ��ȡָ��ҳ����ֻ�����
	 * */
	public List<DmUser> searchUsersByPage(int pageNo);
	/**
	 * ��ȡ��ҳ����
	 * */
	public int getSumPageCount();
}
