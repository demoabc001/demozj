package cn.com.demo.service.impl;

import java.util.List;

import cn.com.demo.dao.IDmUserDAO;
import cn.com.demo.dao.impl.DmUserDAOImpl;
import cn.com.demo.entity.DmUser;
import cn.com.demo.service.IUserService;
import cn.com.demo.util.Constants;

public class UserServiceImpl implements IUserService {

	@Override
	public List<DmUser> searchUsersByPage(int pageNo) {
		List<DmUser> list = null;

		// ��֤pageNo�ĺϷ���
		if (pageNo <= 0) {
			pageNo = 1;
		}
		int sumPageCount = getSumPageCount();
		if (pageNo > sumPageCount) {
			pageNo = sumPageCount;
		}
		// ���㿪ʼ�ͽ���
		int begin = (pageNo - 1) * Constants.PAGE_RECORD_COUNT + 1;
		int end = pageNo * Constants.PAGE_RECORD_COUNT;
		// ����dao
		IDmUserDAO dao = new DmUserDAOImpl();
		list = dao.findUsersByRange(begin, end);

		return list;
	}

	@Override
	public int getSumPageCount() {
		// ��ȡ�ܼ�¼��
		IDmUserDAO dao = new DmUserDAOImpl();
		int recordCount = dao.findSumRecordCount();
		// ����ÿҳ��ʾ������������ҳ����
		int pageCount = recordCount / Constants.PAGE_RECORD_COUNT;
		if (recordCount % Constants.PAGE_RECORD_COUNT != 0) {
			pageCount++;
		}
		return pageCount;
	}

}
