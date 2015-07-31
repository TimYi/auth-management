package com.shz.foundation.media;

import javax.persistence.Entity;
import javax.persistence.Transient;

import com.shz.foundation.persistence.UUIDBaseModel;

@Entity
public class MediaContent extends UUIDBaseModel {

	private String remark;
	/**文件服务器名称*/
	private String serverName;
	/**文件服务器地址*/
	private String serverAddress;
	/**原始文件名称*/
	private String originFileName;
	/**媒体类型*/
	private String contentType;
	/**文件大小*/
	private Long fileSize;
	/**文件存储地址相对服务器访问地址的路径，包括文件名称*/
	private String path;
	/**文件名称*/
	private String fileName;
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * @return the serverName
	 * 服务器名称，用于检索
	 */
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	
	/**
	 * @return the serverAddress
	 * 文件服务器地址，结尾不带/，是服务器ip和port以及应用名称的结合。
	 */
	public String getServerAddress() {
		return serverAddress;
	}
	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}	
	public String getOriginFileName() {
		return this.originFileName;
	}
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	/**
	 * @return the savePath
	 * 相对于文件访问服务器跟路径的相对地址，开头带/，表示相对跟路径
	 */
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Transient
	public String getUrl() {
		return MediaServiceConfig.getMediaUrl(path);
	}
}
