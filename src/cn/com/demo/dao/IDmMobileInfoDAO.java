package cn.com.demo.dao;

import java.util.List;

import cn.com.demo.entity.DmMobileInfo;

public interface IDmMobileInfoDAO {
	// ��ȡ�ܼ�¼��
	public int getRecordCount();
	// ������begin��end
	public List<DmMobileInfo> findByRange(int begin,int end);
	/**
	 * ��ѯ���е��ֻ��б�
	 * */
    public List<DmMobileInfo> findAll();
    /**
     * �����ֻ���id���޸��ֻ���������������������
     * */
    public void update(DmMobileInfo mobile);
    /**
     * ����id���Ҷ�Ӧ���ֻ�����
     * */
    public DmMobileInfo findById(int id);
}
