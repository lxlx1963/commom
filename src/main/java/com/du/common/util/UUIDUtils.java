package com.du.common.util;

import java.util.UUID;

public class UUIDUtils {
	private UUIDUtils() {
	}
	// 2018/4/10 是否考虑并发

	/**
	 * 获取UUID字符串
	 *
	 * @return 替换了“-”的字符串
	 */
	public static String getUUIDString() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}

}
