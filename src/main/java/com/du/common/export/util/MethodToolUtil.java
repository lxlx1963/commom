package com.du.common.export.util;


import java.lang.reflect.Method;

/**
 * 方法工具类
 */
public class MethodToolUtil {
	private MethodToolUtil() {
	}

	/**
	 * set
	 */
	private static final String SET = "set";
	/**
	 * get
	 */
	private static final String GET = "get";
	/**
	 * is
	 */
	private static final String IS = "is";

	/**
	 * 执行方法
	 *
	 * @param object     任意对象
	 * @param methodName 方法名
	 * @return Object(任意对象)
	 * @throws Exception
	 */
	public static Object executeMethod(Object object, String methodName) throws Exception {
		Class clazz = object.getClass();
		Method method = clazz.getDeclaredMethod(methodName, new Class[0]);
		return method.invoke(object, new Object[0]);
	}

	/**
	 * 执行方法
	 *
	 * @param object     任意对象
	 * @param methodName 方法名
	 * @param param      参数
	 * @throws Exception
	 */
	public static void executeMethod(Object object, String methodName, Object param) throws Exception {
		Class clazz = object.getClass();
		Method method = clazz.getDeclaredMethod(methodName, new Class[]{param.getClass()});
		method.invoke(object, new Object[]{param});
	}

	/**
	 * 执行方法
	 *
	 * @param object     任意对象
	 * @param methodName 方法名
	 * @param param      是否带参数
	 * @throws Exception
	 */
	public static void excuteBoolMethod(Object object, String methodName, boolean param) throws Exception {
		Class c = object.getClass();
		Method m = c.getDeclaredMethod(methodName, new Class[]{Boolean.TYPE});
		m.invoke(object, new Object[]{Boolean.valueOf(param)});
	}

	/**
	 * 返回set方法名
	 *
	 * @param property 属性名
	 * @return String(方法名)
	 */
	public static String returnSetMethodName(String property) {
		return SET + Character.toUpperCase(property.charAt(0)) + property.substring(1);
	}

	/**
	 * 返回get方法名
	 *
	 * @param property 属性名
	 * @return String(方法名)
	 */
	public static String returnGetMethodName(String property) {
		return GET + Character.toUpperCase(property.charAt(0)) + property.substring(1);
	}

	/**
	 * 返回is方法名
	 *
	 * @param property 属性名
	 * @return String(方法名)
	 */
	public static String returnIsBooleanMethodName(String property) {
		return IS + Character.toUpperCase(property.charAt(0)) + property.substring(1);
	}
}