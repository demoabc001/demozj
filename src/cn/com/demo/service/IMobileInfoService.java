package cn.com.demo.service;

import java.util.List;
import java.util.Map;

import cn.com.demo.entity.DmMobileInfo;
import cn.com.demo.vo.MobileInfoVO;

public interface IMobileInfoService {
	/**
	 * 获取指定页码的手机集合
	 * */
	public List<DmMobileInfo> searchMobilesByPage(int pageNo);
	/**
	 * 获取总页码数
	 * */
	public int getSumPageCount();
	
    public DmMobileInfo searchMobile(int id);
    public List<DmMobileInfo> searchAllMobiles();
    /**
     * 根据手机对象中的id，更新手机对象中有赋值的属性
     * */
    public void updateMobile(DmMobileInfo info);
    /**
     * 封装MboileInfo并且排序
     * */
    public List<MobileInfoVO> sort(List<DmMobileInfo> mobileList,Map<String,String> dotMap);
}
