package com.du.common.util;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author dxy
 * @date 2018/8/14 9:46
 */
public class ApiUtils {
	private ApiUtils() {
	}

	private static Logger logger = LoggerFactory.getLogger(ApiUtils.class);
	/**
	 * sign
	 */
	private static final String SIGN = "sign";

	/**
	 * 使用<code>secret</code>对paramValues按以下算法进行签名： <br/>
	 * uppercase(hex(sha1(secretkey1value1key2value2...secret))
	 *
	 * @param paramValues 参数列表
	 * @param secret      密钥
	 * @return String
	 */
	public static String sign(Map<String, String> paramValues, String secret) {
		return sign(paramValues, null, secret);
	}

	/**
	 * 对paramValues进行签名，其中ignoreParamNames这些参数不参与签名
	 *
	 * @param paramValues      参数列表
	 * @param ignoreParamNames 忽略参数列表
	 * @param secret           密钥
	 * @return String
	 */
	public static String sign(Map<String, String> paramValues, List<String> ignoreParamNames, String secret) {
		try {
			StringBuilder sb = new StringBuilder();
			List<String> paramNames = new ArrayList<>(paramValues.size());
			paramNames.addAll(paramValues.keySet());
			if (CollectionUtils.isEmpty(ignoreParamNames)) {
				for (String ignoreParamName : ignoreParamNames) {
					paramNames.remove(ignoreParamName);
				}
			}
			Collections.sort(paramNames);

			sb.append(secret);
			for (String paramName : paramNames) {
				sb.append(paramName).append(paramValues.get(paramName));
			}
			sb.append(secret);
			byte[] sha1Digest = getSHA1Digest(sb.toString());
			return byte2hex(sha1Digest);
		} catch (IOException e) {
			logger.error(SIGN, e);
			return null;
		}
	}

	/**
	 * 对字符串进行utf8编码
	 *
	 * @param value             原字符串
	 * @param sourceCharsetName 原编码
	 * @return String
	 */
	public static String utf8Encoding(String value, String sourceCharsetName) {
		try {
			return new String(value.getBytes(sourceCharsetName), StandardCharsets.UTF_8.name());
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * 对数据进行SHA-1签名
	 *
	 * @param data String
	 * @return byte[]
	 * @throws IOException
	 */
	private static byte[] getSHA1Digest(String data) throws IOException {
		byte[] bytes = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			bytes = md.digest(data.getBytes(StandardCharsets.UTF_8.name()));
		} catch (GeneralSecurityException gse) {
			throw new IOException(gse);
		}
		return bytes;
	}

	/**
	 * 二进制转十六进制字符串
	 *
	 * @param bytes byte[]
	 * @return String
	 */
	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toUpperCase());
		}
		return sign.toString();
	}

}
