package com.du.common.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;


/**
 * 文件工具类
 *
 * @author dxy
 * @date 2018/7/3 14:07
 */
public class FileUtils {
	private FileUtils(){}
	private static Logger logger = LoggerFactory.getLogger(FileUtils.class);
	/**
	 * readFile
	 */
	private static final String READ_FILE = "readFile";
	/**
	 * getFileMD5
	 */
	private static final String GET_FILE_MD5 = "getFileMD5";
	/**
	 * readMultipartFile
	 */
	private static final String READ_MULTIPART_FILE = "readMultipartFile";

	/**
	 * 读取文件
	 *
	 * @param path 路径
	 * @return
	 */
	public static String readFile(String path) {
		StringBuilder laststr = new StringBuilder();
		// 打开文件
		File file = new File(path);
		try (FileInputStream in = new FileInputStream(file);
		     BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8.name()))) {
			// 读取文件
			String tempString = "";
			while ((tempString = reader.readLine()) != null) {
				laststr.append(tempString);
			}
		} catch (Exception e) {
			logger.error(READ_FILE, e);
		}
		return laststr.toString();
	}

	/**
	 * MultipartFile转化为File
	 *
	 * @param multipartFile MultipartFile
	 * @return File
	 * @throws IOException
	 */
	public static File multipartFileConvertFile(MultipartFile multipartFile) throws IOException {
		if (multipartFile == null) {
			throw new NullPointerException();
		}
		File file = new File(multipartFile.getOriginalFilename());
		//自动关闭流
		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(multipartFile.getBytes());
		}
		return file;
	}

	/**
	 * MultipartFile转化为File
	 *
	 * @param multipartFile MultipartFile
	 * @return File
	 * @throws IOException
	 */
	public static File multipartFileConvertFile(MultipartFile multipartFile, String fileName) throws IOException {
		if (multipartFile == null) {
			throw new NullPointerException();
		}
		File file = null;
		if (StringUtils.isBlank(fileName)) {
			file = new File(fileName);
		} else {
			file = new File(multipartFile.getOriginalFilename());
		}
		//自动关闭流
		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(multipartFile.getBytes());
		}
		return file;
	}

	/**
	 * 获取文件MD5值
	 *
	 * @param filePath String
	 * @return String
	 */
	public static String getFileMD5(String filePath) {
		File file = new File(filePath);
		if (file.exists() && file.isFile()) {
			try (InputStream in = new FileInputStream(file)) {
				MessageDigest messageDigest = DigestUtils.getMd5Digest();
				byte[] cache = new byte[1024];
				int nRead = 0;
				while ((nRead = in.read(cache)) != -1) {
					messageDigest.update(cache, 0, nRead);
				}
				byte[] data = messageDigest.digest();
				return byteToHex(data);
			} catch (IOException e) {
				logger.error(GET_FILE_MD5, e);
			}
		}
		return "";
	}

	/**
	 * 获取文件MD5值
	 *
	 * @param file File
	 * @return String
	 */
	public static String getFileMD5(File file) {
		String md5 = "";
		if (file.exists() && file.isFile()) {
			try (InputStream in = new FileInputStream(file)) {
				MessageDigest md = DigestUtils.getMd5Digest();
				byte[] buffer = new byte[1024];
				int read = 0;
				while ((read = in.read(buffer)) != -1) {
					md.update(buffer, 0, read);
				}
				byte[] data = md.digest();
				md5 = byteToHex(data);
			} catch (IOException e) {
				logger.error(GET_FILE_MD5, e);
			}
		}
		return md5;
	}

	/**
	 * byte转化为16进制字符串
	 *
	 * @param bytes byte[]
	 * @return String
	 */
	protected static String byteToHex(byte[] bytes) {
		char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f'};
		StringBuilder sb = new StringBuilder(2 * bytes.length);
		for (byte b : bytes) {
			char c0 = hexDigits[b >>> 4 & 0xf];
			char c1 = hexDigits[b & 0xf];
			sb.append(c0);
			sb.append(c1);
		}
		return sb.toString();
	}

	/**
	 * 读取文件(导入JSON文件发布广告测试)
	 *
	 * @param multipartFile MultipartFile
	 * @return String
	 */
	public static String readMultipartFile(MultipartFile multipartFile) throws IOException {
		StringBuilder laststr = new StringBuilder();
		File file = new File(multipartFile.getOriginalFilename());
		//自动关闭流
		try (FileOutputStream fos = new FileOutputStream(file)) {
			fos.write(multipartFile.getBytes());
		}
		try (FileInputStream in = new FileInputStream(file);
		     BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8.name()))) {
			String tempString = "";
			while ((tempString = reader.readLine()) != null) {
				laststr.append(tempString);
			}
		} catch (IOException e) {
			logger.error(READ_MULTIPART_FILE, e);
			return null;
		} finally {
			// 删除文件
			if (file.exists()) {
				// 获取文件绝对路径
				String absolutePath = file.getAbsolutePath();
				Path path = Paths.get(absolutePath);
				Files.delete(path);
			}
		}
		return laststr.toString();
	}

	/**
	 * 从网路地址中获取文件
	 *
	 * @param downloadStie 下载地址
	 * @param dest         文件存储全路径
	 * @throws IOException
	 */
	public static void downloadFileFromURL(String downloadStie, String dest) throws IOException {
		if (StringUtils.isBlank(downloadStie) || StringUtils.isBlank(dest)) {
			throw new NullPointerException("");
		}
		File tempFile = new File(dest);
		// 如果文件父目录不存在，就创建文件父目录
		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdirs();
		}
		URL url = new URL(downloadStie);
		try (ReadableByteChannel rbc = Channels.newChannel(url.openStream());
		     FileOutputStream fos = new FileOutputStream(tempFile)) {
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		}
	}

}
