package com.du.common.util;

import com.du.common.annotation.Children;
import com.du.common.annotation.Id;
import com.du.common.annotation.ParentId;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取树形公共工具类（列表中的类，必须有@Children、@Id、@ParentId注解注释对应的字段）
 *
 * @author dxy
 * @date 2018/7/9 10:06
 */
public class TreeUtils {
	private TreeUtils() {
	}

	private static Logger logger = LoggerFactory.getLogger(TreeUtils.class);
	/**
	 * set
	 */
	private static final String SET = "set";
	/**
	 * get
	 */
	private static final String GET = "get";
	/**
	 * 参数为空
	 */
	private static final String PARAMETER_IS_NULL = "参数为空";
	/**
	 * 列表中的对象不能为空
	 */
	private static final String LIST_OF_ELEMENT_IS_NULL = "列表中的对象不能为空";
	/**
	 * 请在主键上使用@ParentId注解
	 */
	private static final String PARENT_ID_ANNOTATIION = "请在主键上使用@ParentId注解";
	/**
	 * 请在主键上使用@Children注解
	 */
	private static final String CHILDREN_ANNOTATIION = "请在主键上使用@Children注解";
	/**
	 * 请在主键上使用@Id注解
	 */
	private static final String ID_ANNOTATIION = "请在主键上使用@Id注解";
	/**
	 * setter
	 */
	private static final String SETTER = "setter";
	/**
	 * getter
	 */
	private static final String GETTER = "getter";

	/**
	 * 返回树形列表
	 *
	 * @param allList List<?>
	 * @return List
	 */
	public static List getTreeList(List<?> allList) {
		if (CollectionUtils.isEmpty(allList)) {
			throw new NullPointerException(PARAMETER_IS_NULL);
		}
		Object obj = allList.get(0);
		if (obj == null) {
			throw new NullPointerException(LIST_OF_ELEMENT_IS_NULL);
		}
		//获取父ID和子节点字段名称
		String parentFieldName = getFieldNameWithParent(obj);
		String childrenFieldName = getFieldNameWithChildren(obj);
		String idFieldName = getFieldNameWithId(obj);
		if (StringUtils.isBlank(parentFieldName)) {
			throw new IllegalArgumentException(PARENT_ID_ANNOTATIION);
		}
		if (StringUtils.isBlank(childrenFieldName)) {
			throw new IllegalArgumentException(CHILDREN_ANNOTATIION);
		}
		if (StringUtils.isBlank(idFieldName)) {
			throw new IllegalArgumentException(ID_ANNOTATIION);
		}
		//根节点
		List rootList = new ArrayList<>();
		for (int i = 0; i < allList.size(); i++) {
			Object object = allList.get(i);
			if (object == null) {
				continue;
			}
			//父节点为null的，为根节点。
			Object value = getter(object, parentFieldName);
			if (value == null) {
				rootList.add(object);
			}
		}
		//为根节点设置所有子孙节点
		for (Object root : rootList) {
			if (root == null) {
				continue;
			}
			List childList = getChild(getter(root, idFieldName), allList);
			setter(root, childrenFieldName, childList, List.class);
		}
		return rootList;
	}

	/**
	 * 获取所有子孙节点
	 *
	 * @param id   id
	 * @param list 所有节点列表
	 * @return List 每个根节点下，所有子节点列表
	 */
	private static List getChild(Object id, List<?> list) {
		if (id == null || CollectionUtils.isEmpty(list)) {
			throw new NullPointerException(PARAMETER_IS_NULL);
		}
		Object first = list.get(0);
		//获取父ID和子节点字段名称
		String parentFieldName = getFieldNameWithParent(first);
		String childrenFieldName = getFieldNameWithChildren(first);
		String idFieldName = getFieldNameWithId(first);
		//子菜单
		List childList = new ArrayList<>();

		/**
		 * 遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
		 * 相等说明：为该根节点的子节点。
		 */
		for (int i = 0; i < list.size(); i++) {
			Object tree = list.get(i);
			if (tree == null) {
				continue;
			}
			Object parentId = getter(tree, parentFieldName);
			if (parentId != null && String.valueOf(id).equals(String.valueOf(parentId))) {
				childList.add(tree);
			}
		}
		//递归查找子节点
		for (Object obj : childList) {
			Object getter = getter(obj, idFieldName);
			setter(obj, childrenFieldName, getChild(getter, list), List.class);
		}
		//如果节点下没有子节点，返回一个空List（递归退出）
		if (CollectionUtils.isEmpty(childList)) {
			return new ArrayList<>();
		}
		return childList;
	}

	/**
	 * 获取字段名称（注解@ParentId）
	 *
	 * @param t T
	 * @return String(字段名称)
	 */
	private static String getFieldNameWithParent(Object t) {
		if (t == null) {
			throw new NullPointerException(PARAMETER_IS_NULL);
		}
		String fieldName = "";
		Field[] fields = t.getClass().getDeclaredFields();
		if (fields == null || fields.length == 0) {
			return fieldName;
		}
		// 标签，用于判断调出双层循环
		boolean flag = false;
		//返回属性字段注解为@ParentId的属性名称
		for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();
			if (annotations == null || annotations.length == 0) {
				continue;
			}
			for (Annotation annotation : annotations) {
				if (annotation instanceof ParentId) {
					fieldName = field.getName();
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}
		}
		return fieldName;
	}

	/**
	 * 获取字段名称（注解@ChildrenId）
	 *
	 * @param t T
	 * @return String(字段名称)
	 */
	private static String getFieldNameWithChildren(Object t) {
		if (t == null) {
			throw new NullPointerException(PARAMETER_IS_NULL);
		}
		String fieldName = "";
		Field[] fields = t.getClass().getDeclaredFields();
		if (fields == null || fields.length == 0) {
			return fieldName;
		}
		// 标签，用于判断调出双层循环
		boolean flag = false;
		//返回属性字段注解为@Children的属性名称
		for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();
			if (annotations == null || annotations.length == 0) {
				continue;
			}
			for (Annotation annotation : annotations) {
				if (annotation instanceof Children) {
					fieldName = field.getName();
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}
		}
		return fieldName;
	}

	/**
	 * 获取字段名称（注解@Id）
	 *
	 * @param t T
	 * @return String(字段名称)
	 */
	private static String getFieldNameWithId(Object t) {
		if (t == null) {
			throw new NullPointerException(PARAMETER_IS_NULL);
		}
		String fieldName = "";
		Field[] fields = t.getClass().getDeclaredFields();
		if (fields == null || fields.length == 0) {
			return fieldName;
		}
		// 标签，用于判断调出双层循环
		boolean flag = false;
		//返回属性字段注解为@Id的属性名称
		for (Field field : fields) {
			Annotation[] annotations = field.getAnnotations();
			if (annotations == null || annotations.length == 0) {
				continue;
			}
			for (Annotation annotation : annotations) {
				if (annotation instanceof Id) {
					fieldName = field.getName();
					flag = true;
					break;
				}
			}
			if (flag) {
				break;
			}
		}
		return fieldName;
	}

	/**
	 * 调用set方法
	 *
	 * @param obj       Object
	 * @param attribute String
	 * @param value     Object
	 * @param type      Class<?>
	 */
	private static void setter(Object obj, String attribute, Object value, Class<?> type) {
		try {
			Method met = obj.getClass().
					getMethod(SET + firstWordToUpper(attribute), type);
			met.invoke(obj, value);
		} catch (Exception e) {
			logger.error(SETTER, e);
		}
	}

	/**
	 * 调用get方法
	 *
	 * @param obj       Object
	 * @param attribute String
	 * @return Object
	 */
	private static Object getter(Object obj, String attribute) {
		try {
			Method met = obj.getClass().getMethod(GET + firstWordToUpper(attribute));
			return met.invoke(obj);
		} catch (Exception e) {
			logger.error(GETTER, e);
		}
		return null;
	}

	/**
	 * 将字段的首字母大写
	 *
	 * @param str String
	 * @return String
	 */
	private static String firstWordToUpper(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

}
