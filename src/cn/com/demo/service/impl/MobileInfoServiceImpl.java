package cn.com.demo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cn.com.demo.dao.IDmMobileInfoDAO;
import cn.com.demo.dao.impl.DmMobileInfoDAOImpl;
import cn.com.demo.entity.DmMobileInfo;
import cn.com.demo.service.IMobileInfoService;
import cn.com.demo.util.Constants;
import cn.com.demo.vo.MobileInfoVO;

public class MobileInfoServiceImpl implements IMobileInfoService {

	@Override
	public List<DmMobileInfo> searchMobilesByPage(int pageNo) {
		List<DmMobileInfo> list = null;
		
		// ��֤pageNo�ĺϷ���
		if(pageNo<=0){
			pageNo = 1;
		}
		int sumPageCount = getSumPageCount();
		if(pageNo>sumPageCount){
			pageNo = sumPageCount;
		}
		// ���㿪ʼ�ͽ���
		int begin = (pageNo-1)*Constants.PAGE_RECORD_COUNT+1;
		int end = pageNo * Constants.PAGE_RECORD_COUNT;
		// ����dao
		IDmMobileInfoDAO dao = new DmMobileInfoDAOImpl();
		list = dao.findByRange(begin, end);
		
		return list;
	}

	@Override
	public int getSumPageCount() {
		// ��ȡ�ܼ�¼��
		IDmMobileInfoDAO dao = new DmMobileInfoDAOImpl();
		int recordCount = dao.getRecordCount();
		// ����ÿҳ��ʾ������������ҳ����
		int pageCount = recordCount/Constants.PAGE_RECORD_COUNT;
		if(recordCount%Constants.PAGE_RECORD_COUNT != 0){
			pageCount++;
		}
		return pageCount;
	}

	@Override
	public DmMobileInfo searchMobile(int id) {
		// ����mobileDAO����
		IDmMobileInfoDAO mobileDAO = new DmMobileInfoDAOImpl();
		// ����mobileDAO�����findById��������ȡmobileInfo����
		DmMobileInfo mobile = mobileDAO.findById(id);
		return mobile;
	}

	@Override
	public List<DmMobileInfo> searchAllMobiles() {
		// ����mobileDAO����
		IDmMobileInfoDAO mobileDAO = new DmMobileInfoDAOImpl();
		// ����mobileDAO�����findAll��������ȡmobileInfo����
		List<DmMobileInfo> mobileList = mobileDAO.findAll();
		return mobileList;
	}

	@Override
	public void updateMobile(DmMobileInfo info) {
		// �ж�info�Ƿ�Ϊnull
		if (info == null) {
			throw new RuntimeException("Ҫ���µ�MobileInfo����Ϊ��");
		}
		// �ж�info���Ƿ���id
		if (info.getMbId() <= 0) {
			throw new RuntimeException("��������Ҫ����MobileInfo�����id����");
		}
		// ����MobileDAO����
		IDmMobileInfoDAO mobileDAO = new DmMobileInfoDAOImpl();
		// ����id�ҵ��ɶ���
		DmMobileInfo dbMobile = mobileDAO.findById(info.getMbId());
		if (dbMobile == null) {
			throw new RuntimeException("idΪ" + info.getMbId()
					+ "��MobileInfo���󲻴���");
		}
		// ���¶������и�ֵ�����ԣ����õ��ɶ�����
		if (info.getMbColor() != null) {
			dbMobile.setMbColor(info.getMbColor());
		}
		if (info.getMbMemory() != 0) {
			dbMobile.setMbMemory(info.getMbMemory());
		}
		if (info.getMbName() != null) {
			dbMobile.setMbName(info.getMbName());
		}
		if (info.getMbPrice() != 0) {
			dbMobile.setMbPrice(info.getMbPrice());
		}
		// ���¶���
		mobileDAO.update(dbMobile);
	}

	@Override
	public List<MobileInfoVO> sort(List<DmMobileInfo> mobileList,
			Map<String, String> dotMap) {
		List<MobileInfoVO> voList = null;

		// ѭ��
		// ��ȡÿ��Mobile
		// ����vo
		// ����mobile
		// ��map��ȡdot�����õ�vo
		// vo��ӵ�voList

		// ����voList

		if (mobileList != null) {
			voList = new ArrayList<MobileInfoVO>();
			// ѭ��
			MobileInfoVO vo = null;
			for (DmMobileInfo mb : mobileList) {

				// ����vo
				vo = new MobileInfoVO();
				// ��ȡÿ��Mobile
				// ����mobile
				vo.setMobile(mb);
				if (dotMap != null) {
					// ��map��ȡdot�����õ�vo
					String dotStr = dotMap.get(String.valueOf(mb.getMbId()));
					if (dotStr != null) {
						try {
							vo.setDotTimes(Integer.parseInt(dotStr));
						} catch (Exception e) {
						}
					}
				}
				// vo��ӵ�voList
				voList.add(vo);
			}

			// ����voList ����
			Collections.sort(voList);
			// ����
			Collections.reverse(voList);
		}

		return voList;
	}
}
