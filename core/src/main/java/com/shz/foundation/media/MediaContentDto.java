package com.shz.foundation.media;

import com.shz.foundation.persistence.Identified;

public class MediaContentDto implements Identified<String> {
	private String id;
	private String url;
	private Long fileSize;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
}
