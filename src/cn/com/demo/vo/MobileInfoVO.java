package cn.com.demo.vo;

import cn.com.demo.entity.DmMobileInfo;

public class MobileInfoVO implements Comparable {
	private DmMobileInfo mobile;
	private int dotTimes;

	// 自己比对方大，返回>0 相等0 小于<0
	@Override
	public int compareTo(Object o) {
		int result = 0;

		if (o == null || !(o instanceof MobileInfoVO)) {
			throw new RuntimeException("不可比较");
		} else {
			MobileInfoVO vo = (MobileInfoVO) o;
			// 比较点击次数
			result = dotTimes - vo.getDotTimes();
			// 如果没有比较出大小
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
