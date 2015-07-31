package com.shz.foundation.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 为模拟图片上传，包装File生成 @MultipartFile
 * @author pc
 *
 */
public class JpegMultipartFile implements MultipartFile {
	public JpegMultipartFile(File imgFile) {
		this.imgFile=imgFile;
	}
	public JpegMultipartFile(String filePath) {
		this.imgFile=new File(filePath);
	}
	private File imgFile;
	@Override
	public String getName() {
		return FilenameUtils.getBaseName(getOriginalFilename());
	}

	@Override
	public String getOriginalFilename() {
		return imgFile.getName();
	}

	@Override
	public String getContentType() {
		return "image/jpeg";
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public long getSize() {
		return imgFile.length();
	}

	@Override
	public byte[] getBytes() throws IOException {
		return FileUtils.readFileToByteArray(imgFile);
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return FileUtils.openInputStream(imgFile);
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		
	}

}
