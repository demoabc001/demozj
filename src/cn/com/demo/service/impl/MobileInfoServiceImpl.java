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
		
		// 验证pageNo的合法性
		if(pageNo<=0){
			pageNo = 1;
		}
		int sumPageCount = getSumPageCount();
		if(pageNo>sumPageCount){
			pageNo = sumPageCount;
		}
		// 计算开始和结束
		int begin = (pageNo-1)*Constants.PAGE_RECORD_COUNT+1;
		int end = pageNo * Constants.PAGE_RECORD_COUNT;
		// 调用dao
		IDmMobileInfoDAO dao = new DmMobileInfoDAOImpl();
		list = dao.findByRange(begin, end);
		
		return list;
	}

	@Override
	public int getSumPageCount() {
		// 获取总记录数
		IDmMobileInfoDAO dao = new DmMobileInfoDAOImpl();
		int recordCount = dao.getRecordCount();
		// 根据每页显示条数，计算总页码数
		int pageCount = recordCount/Constants.PAGE_RECORD_COUNT;
		if(recordCount%Constants.PAGE_RECORD_COUNT != 0){
			pageCount++;
		}
		return pageCount;
	}

	@Override
	public DmMobileInfo searchMobile(int id) {
		// 创建mobileDAO对象
		IDmMobileInfoDAO mobileDAO = new DmMobileInfoDAOImpl();
		// 调用mobileDAO对象的findById方法，获取mobileInfo对象
		DmMobileInfo mobile = mobileDAO.findById(id);
		return mobile;
	}

	@Override
	public List<DmMobileInfo> searchAllMobiles() {
		// 创建mobileDAO对象
		IDmMobileInfoDAO mobileDAO = new DmMobileInfoDAOImpl();
		// 调用mobileDAO对象的findAll方法，获取mobileInfo对象
		List<DmMobileInfo> mobileList = mobileDAO.findAll();
		return mobileList;
	}

	@Override
	public void updateMobile(DmMobileInfo info) {
		// 判断info是否为null
		if (info == null) {
			throw new RuntimeException("要更新的MobileInfo不能为空");
		}
		// 判断info中是否有id
		if (info.getMbId() <= 0) {
			throw new RuntimeException("必须设置要更新MobileInfo对象的id属性");
		}
		// 创建MobileDAO对象
		IDmMobileInfoDAO mobileDAO = new DmMobileInfoDAOImpl();
		// 根据id找到旧对象
		DmMobileInfo dbMobile = mobileDAO.findById(info.getMbId());
		if (dbMobile == null) {
			throw new RuntimeException("id为" + info.getMbId()
					+ "的MobileInfo对象不存在");
		}
		// 将新对象中有赋值的属性，设置到旧对象中
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
		// 更新对象
		mobileDAO.update(dbMobile);
	}

	@Override
	public List<MobileInfoVO> sort(List<DmMobileInfo> mobileList,
			Map<String, String> dotMap) {
		List<MobileInfoVO> voList = null;

		// 循环
		// 获取每个Mobile
		// 创建vo
		// 设置mobile
		// 从map获取dot，设置到vo
		// vo添加到voList

		// 排序voList

		if (mobileList != null) {
			voList = new ArrayList<MobileInfoVO>();
			// 循环
			MobileInfoVO vo = null;
			for (DmMobileInfo mb : mobileList) {

				// 创建vo
				vo = new MobileInfoVO();
				// 获取每个Mobile
				// 设置mobile
				vo.setMobile(mb);
				if (dotMap != null) {
					// 从map获取dot，设置到vo
					String dotStr = dotMap.get(String.valueOf(mb.getMbId()));
					if (dotStr != null) {
						try {
							vo.setDotTimes(Integer.parseInt(dotStr));
						} catch (Exception e) {
						}
					}
				}
				// vo添加到voList
				voList.add(vo);
			}

			// 排序voList 升序
			Collections.sort(voList);
			// 倒排
			Collections.reverse(voList);
		}

		return voList;
	}
}
