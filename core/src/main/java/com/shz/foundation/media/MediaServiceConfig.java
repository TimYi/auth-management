package com.shz.foundation.media;

import org.apache.commons.lang3.StringUtils;

/**
 * 现行服务器基础配置信息
 * 通过静态注入实现可配置Server信息
 * 因为不能在接口中使用静态注入，而添加了这个类
 * 是基础类型，其它类可以像依赖接口一样依赖此类
 * 具有一些工具类功能
 * @author pc
 *
 */
public class MediaServiceConfig {

	/**服务器名称*/
	public static String SERVER_NAME;
	/**服务器地址*/
	public static String SERVER_ADDRESS;
	/**服务器保存文件所在跟路径*/
	public static String BASE_PATH;
	
	public static void setServerName(String serverName) {
		SERVER_NAME = serverName;
	}
	public static void setServerAddress(String serverAddress) {
		SERVER_ADDRESS = serverAddress;
	}	
	public static void setBasePath(String basePath) {
		BASE_PATH = basePath;
	}
	
	/**
	 * 获取媒体类文件访问地址
	 * @param path 文件存储地址相对服务器访问地址的路径，包括文件名称
	 * @return 图片访问地址
	 */
	public static String getMediaUrl(String path) {
		String serverAddress=StringUtils.removeEnd(SERVER_ADDRESS, "/");
		path=StringUtils.removeStart(path, "/");
		return serverAddress+"/"+path;
	}
}
