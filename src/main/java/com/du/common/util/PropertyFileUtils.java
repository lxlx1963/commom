package com.du.common.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * 配置文件读取
 */
public class PropertyFileUtils {
	private PropertyFileUtils() {
	}

	/**
	 * 通过配置Key获取key对应的值
	 *
	 * @param propertyFileName 配置文件名称（不带后缀）
	 * @param key              键
	 * @return key对应Value
	 * @throws UnsupportedEncodingException
	 */
	public static String getPropertyValueByKey(String propertyFileName, String key) throws UnsupportedEncodingException {
		String result = "";
		result = java.util.ResourceBundle.getBundle(propertyFileName).getString(key);
		return new String(result.getBytes(StandardCharsets.ISO_8859_1.name()), "GBK");
	}

}
