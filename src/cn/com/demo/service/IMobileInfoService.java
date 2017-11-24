package cn.com.demo.service;

import java.util.List;
import java.util.Map;

import cn.com.demo.entity.DmMobileInfo;
import cn.com.demo.vo.MobileInfoVO;

public interface IMobileInfoService {
	/**
	 * ��ȡָ��ҳ����ֻ�����
	 * */
	public List<DmMobileInfo> searchMobilesByPage(int pageNo);
	/**
	 * ��ȡ��ҳ����
	 * */
	public int getSumPageCount();
	
    public DmMobileInfo searchMobile(int id);
    public List<DmMobileInfo> searchAllMobiles();
    /**
     * �����ֻ������е�id�������ֻ��������и�ֵ������
     * */
    public void updateMobile(DmMobileInfo info);
    /**
     * ��װMboileInfo��������
     * */
    public List<MobileInfoVO> sort(List<DmMobileInfo> mobileList,Map<String,String> dotMap);
}
