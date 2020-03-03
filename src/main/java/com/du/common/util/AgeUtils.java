package com.du.common.util;

import com.du.common.constant.BusinessConstant;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 年龄相关工具类
 *
 * @author dxy
 * @date 2019/8/12 18:32
 */
public class AgeUtils {
	private AgeUtils() {
	}

	/**
	 * 根据年龄获取年龄范围
	 *
	 * @param age 年龄
	 * @return String（年龄范围）
	 */
	public static String getAgeRange(Integer age) {
		if (age == null) {
			return "";
		}
		if (BusinessConstant.NINETEEN >= age) {
			// 19岁及以下
			return BusinessConstant.UNDER_NINETEEN_CH;
		} else if (BusinessConstant.TWENTY <= age && BusinessConstant.TWENTY_FIVE >= age) {
			// 20岁~25岁
			return BusinessConstant.TWENTY_AND_TWENTYFIVE_CH;
		} else if (BusinessConstant.TWENTY_SIX <= age && BusinessConstant.THIRTY_FIVE >= age) {
			// 26岁~35岁
			return BusinessConstant.TWENTYSIX_AND_THIRTYFIVE_CH;
		} else if (BusinessConstant.THIRTY_SIX <= age && BusinessConstant.FOURTY_FIVE >= age) {
			// 36岁~45岁
			return BusinessConstant.THIRTYSIX_AND_FOURTYFIVE_CH;
		} else if (BusinessConstant.FOURTY_SIX <= age && BusinessConstant.FIFTY_FIVE >= age) {
			// 46岁~55岁
			return BusinessConstant.FOURTYSIXE_AND_FIFTYFIVE_CH;
		} else {
			// 56岁及以上
			return BusinessConstant.ABOVE_FIFTYFIVE_CH;
		}
	}

	/**
	 * 计算年龄范围
	 *
	 * @param ageList 年龄列表
	 * @return List<String>
	 */
	public static List<String> calculateAgeRange(List<Integer> ageList) {
		List<String> ageRangeList = Lists.newArrayList();
		// 如果传入列表为空，返回""
		if (CollectionUtils.isEmpty(ageList)) {
			return ageRangeList;
		}
		// 19岁及以下人数
		int underNineteenCount = 0;
		// 20岁~25岁
		int twentyAndTwentyFiveCount = 0;
		// 26岁~35岁
		int twentySixAndThirtyFiveCount = 0;
		// 36岁~45岁
		int thirtySixAndFourtyFiveCount = 0;
		// 46岁~55岁
		int fourtySixeAndFiftyFiveCount = 0;
		// 56岁及以上
		int aboveFiftyFiveCount = 0;
		for (Integer age : ageList) {
			if (BusinessConstant.NINETEEN >= age) {
				// 19岁及以下
				underNineteenCount++;
			} else if (BusinessConstant.TWENTY <= age && BusinessConstant.TWENTY_FIVE >= age) {
				// 20岁~25岁
				twentyAndTwentyFiveCount++;
			} else if (BusinessConstant.TWENTY_SIX <= age && BusinessConstant.THIRTY_FIVE >= age) {
				// 26岁~35岁
				twentySixAndThirtyFiveCount++;
			} else if (BusinessConstant.THIRTY_SIX <= age && BusinessConstant.FOURTY_FIVE >= age) {
				// 36岁~45岁
				thirtySixAndFourtyFiveCount++;
			} else if (BusinessConstant.FOURTY_SIX <= age && BusinessConstant.FIFTY_FIVE >= age) {
				// 46岁~55岁
				fourtySixeAndFiftyFiveCount++;
			} else {
				// 56岁及以上
				aboveFiftyFiveCount++;
			}
		}
		// 年段数量统计
		Map<String, Integer> ageCountMap = Maps.newHashMap();
		ageCountMap.put(BusinessConstant.UNDER_NINETEEN_CH, underNineteenCount);
		ageCountMap.put(BusinessConstant.TWENTY_AND_TWENTYFIVE_CH, twentyAndTwentyFiveCount);
		ageCountMap.put(BusinessConstant.TWENTYSIX_AND_THIRTYFIVE_CH, twentySixAndThirtyFiveCount);
		ageCountMap.put(BusinessConstant.THIRTYSIX_AND_FOURTYFIVE_CH, thirtySixAndFourtyFiveCount);
		ageCountMap.put(BusinessConstant.FOURTYSIXE_AND_FIFTYFIVE_CH, fourtySixeAndFiftyFiveCount);
		ageCountMap.put(BusinessConstant.ABOVE_FIFTYFIVE_CH, aboveFiftyFiveCount);
		// 选出人数最多的年段，如果有相等的情况，随机选择
		// value值
		Collection<Integer> values = ageCountMap.values();
		// 转化为数据
		Object[] objects = values.toArray();
		// 按升序排序
		Arrays.sort(objects);
		// 获取人数最多的值max
		Object max = objects[objects.length - 1];
		//通过value值查找key
		for (Map.Entry<String, Integer> entry : ageCountMap.entrySet()) {
			Integer value = entry.getValue();
			String key = entry.getKey();
			// 通过与max的值比较，获取人数最多的年龄段
			if (max.equals(value)) {
				ageRangeList.add(key);
			}
		}
		return ageRangeList;
	}

	/**
	 * 获取年龄段前后的年龄段列表
	 *
	 * 1.年龄段19岁及以下时 匹配后面年龄段20-25
	 * 2.年龄段56岁及以上时，匹配前面年龄段45-55
	 * @param ageRange 年龄段
	 * @return List<String>
	 */
	public static List<String> getAgeRangeList(String ageRange) {
		List<String> ageRangeList = Lists.newArrayList();
		if (StringUtils.isBlank(ageRange)) {
			return ageRangeList;
		}
		// 年龄段19岁及以下时 匹配后面年龄段20-25岁
		if (BusinessConstant.UNDER_NINETEEN_CH.equals(ageRange)) {
			ageRangeList.add(BusinessConstant.TWENTY_AND_TWENTYFIVE_CH);
			return ageRangeList;
		}
		// 年龄段56岁及以上时，匹配前面年龄段46-55岁
		if (BusinessConstant.ABOVE_FIFTYFIVE_CH.equals(ageRange)) {
			ageRangeList.add(BusinessConstant.FOURTYSIXE_AND_FIFTYFIVE_CH);
			return ageRangeList;
		}
		// 除年龄段“19岁及以下”、“56岁及以上”以外，其他年段段的格式都为xx-xx岁
		// 将“岁”从字符串中去掉
		String str = ageRange.replace(BusinessConstant.AGE_CH, "");
		// 已“~”分割字符串
		String[] ageArray = str.split("~");
		// 如果数组为空
		if (ArrayUtils.isEmpty(ageArray)) {
			return ageRangeList;
		}
		// 将字符串类型数组转化为数字数组
		Integer[] array = (Integer[]) ConvertUtils.convert(ageArray, Integer.class);
		// 按升序排序
		Arrays.sort(array);
		// 数组中只有两个元素
		for (int i = 0; i < array.length; i++) {
			int age = 0;
			// 数组中最小值为前年龄的最大年龄，前年龄段的最大年龄 = age - 1
			if (i == 0){
				age = array[i] - 1;
			}
			// 数组中最大值为后年龄的最小年龄，后年龄段的最大年龄 = age + 1
			if (i == 1) {
				age = array[i] + 1;
			}
			// 根据年龄获取年龄段
			String range = getAgeRange(age);
			ageRangeList.add(range);
		}
		return ageRangeList;
	}

	/**
	 * 获取其他年龄段列表
	 *
	 * @param ageRangeList 年龄段列表
	 * @return List<String>
	 */
	public static List<String> getOtherAgeRangeList(List<String> ageRangeList) {
		if (CollectionUtils.isEmpty(ageRangeList)) {
			return Lists.newArrayList();
		}
		return BusinessConstant.AGE_RANGE_LIST
				.stream()
				.filter(ageRange -> !ageRangeList.contains(ageRange))
				.collect(Collectors.toList());
	}
}
