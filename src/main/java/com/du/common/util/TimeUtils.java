package com.du.common.util;

import com.du.common.constant.BusinessConstant;

/**
 * 时间工具类
 *
 * @author dxy
 * @date 2019/10/8 16:11
 */
public class TimeUtils {

	private TimeUtils() {

	}

	/**
	 * 获取秒段
	 *
	 * @param milliSecond 毫秒值
	 * @return String
	 */
	public static String getSecondRange(long milliSecond) {
		if (milliSecond <= BusinessConstant.FIVE_THOUSAND) {
			return BusinessConstant.LESS_THAN_FIVE_SECOND;
		} else if (milliSecond > BusinessConstant.FIVE_THOUSAND && milliSecond <= BusinessConstant.EIGHT_THOUSAND) {
			return BusinessConstant.FIVE_AND_EIGHT_SECOND;
		} else if (milliSecond > BusinessConstant.EIGHT_THOUSAND && milliSecond <= BusinessConstant.TWELVE_THOUSAND) {
			return BusinessConstant.EIGHT_AND_TWELVE_SECOND;
		} else {
			return BusinessConstant.MORE_THAN_TWELVE_SECOND;
		}
	}
}
