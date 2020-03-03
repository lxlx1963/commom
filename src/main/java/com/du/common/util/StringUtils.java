package com.du.common.util;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * 字符串工具类
 *
 * @author dxy
 * @date 2018/6/22 17:21
 */
public class StringUtils {
	private StringUtils(){}
	/**
	 * 列表转化为格式化字符串
	 *
	 * @param list   List<Object>
	 * @param format 间隔符
	 * @return String(格式化字符串)
	 */
	public static String listToFormatString(List<?> list, String format) {
		if (CollectionUtils.isEmpty(list) || org.apache.commons.lang3.StringUtils.isBlank(format)) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			if (i > 0) {
				sb.append(format);
			}
			sb.append(list.get(i));
		}
		return sb.toString();
	}
}
