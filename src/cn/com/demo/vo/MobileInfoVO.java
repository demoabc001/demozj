package cn.com.demo.vo;

import cn.com.demo.entity.DmMobileInfo;

public class MobileInfoVO implements Comparable {
	private DmMobileInfo mobile;
	private int dotTimes;

	// �Լ��ȶԷ��󣬷���>0 ���0 С��<0
	@Override
	public int compareTo(Object o) {
		int result = 0;

		if (o == null || !(o instanceof MobileInfoVO)) {
			throw new RuntimeException("���ɱȽ�");
		} else {
			MobileInfoVO vo = (MobileInfoVO) o;
			// �Ƚϵ������
			result = dotTimes - vo.getDotTimes();
			// ���û�бȽϳ���С
			if (result == 0) {
				result = this.mobile.getMbId() - vo.getMobile().getMbId();
			}
		}

		return result;
	}

	public DmMobileInfo getMobile() {
		return mobile;
	}

	public void setMobile(DmMobileInfo mobile) {
		this.mobile = mobile;
	}

	public int getDotTimes() {
		return dotTimes;
	}

	public void setDotTimes(int dotTimes) {
		this.dotTimes = dotTimes;
	}

}
