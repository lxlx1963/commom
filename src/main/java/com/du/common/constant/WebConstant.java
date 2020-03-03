package com.du.common.constant;

/**
 * Web常量类
 */
public final class WebConstant {
	/**
	 * code
	 */
	public static final String CODE = "code";
	/**
	 * data
	 */
	public static final String DATA = "data";
	/**
	 * msg
	 */
	public static final String MSG = "msg";
	/**
	 * 成功
	 */
	public static final int CODE_SUCCESS = 0;
	/**
	 * 业务逻辑错误
	 */
	public static final int CODE_FAIL = 1;
	/**
	 * 系统错误
	 */
	public static final int CODE_SYSTEM_FAIL = -1;
	/**
	 * 成功
	 */
	public static final String MSG_SUCCESS = "操作成功";
	/**
	 * 操作失败
	 */
	public static final String MSG_ERROR = "操作失败";
	/**
	 * 系统错误
	 */
	public static final String MSG_SYSTEM_ERROR = "系统错误";
	/**
	 * session键user
	 */
	public static final String SESSION_KEY_USER = "login_user";

	private WebConstant() {
	}

}
