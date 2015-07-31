package com.shz.foundation.media;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import com.shz.foundation.utils.Identities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件保存在服务器本地磁盘时，可以使用此媒体仓库。
 * @author pc
 *
 */
@Transactional
public class LocalMediaService implements MediaService {
	
	@Autowired
	private MediaContentRepository repository;
	/**文件相对存储目录*/
	private String relativePath="media";
	
	@Override
	public MediaContent getMedia(String id) {
		return repository.findOne(id);
	}
	
	@Override
	public MediaContent save(MultipartFile file) throws IOException {
		MediaContent media=null;
		return update(media, file);
	}

	@Override
	public MediaContent update(MediaContent media, MultipartFile file) throws IOException {
		if(file==null) throw new IOException("文件不能为空");
		if(media==null) {
			media=new MediaContent();
		} else {
			//清理旧文件
			deleteFile(media);
		}
		media.setServerAddress(getServerAddress());
		media.setFileSize(file.getSize());
		media.setServerName(getServerName());
		media.setContentType(file.getContentType());
		String originFileName=file.getOriginalFilename();
		media.setOriginFileName(originFileName);
		
		//生成文件名称
		String extension=FilenameUtils.getExtension(originFileName);
		String fileName=Identities.uuid2();
		fileName=fileName+"."+extension;
		media.setFileName(fileName);
		
		//相对根路径的path
		String path=concatRelativeFilePath(fileName);		
		media.setPath(path);
		
		//将文件保存到本地目录
		String realFilePath=concatRealFilePathString(path);
		File realFile=new File(realFilePath);
		FileUtils.writeByteArrayToFile(realFile, file.getBytes());
		media=repository.save(media);
		return media;
	}
	
	@Override
	public MediaContent update(String id, MultipartFile file)
			throws IOException {
		MediaContent media=repository.findOne(id);
		if(media==null) return null;
		return update(media, file);
	}
	
	@Override
	public void delete(MediaContent media) throws IOException {
		if(media==null)return;
		deleteFile(media);
		repository.delete(media);
	}
	@Override
	public void delete(String id) throws IOException {
		MediaContent media=repository.findOne(id);
		delete(media);
	}
	
	protected void deleteFile(MediaContent media) {
		String path=getBasePath()+media.getPath();
		path=FilenameUtils.normalize(path, true);
		File file=new File(path);
		file.delete();
	}
	
	/**
	 * 根据文件名，生成相对存储路径
	 * @param fileName 文件全名，包括扩展名
	 * @return
	 */
	protected String concatRelativeFilePath(String fileName) {
		if(StringUtils.isBlank(getRelativePath())) {
			return fileName;
		}
		String relativePath=StringUtils.removeEnd(getRelativePath(), "/");
		String path=relativePath+"/"+fileName;
		return path;
	}
	
	/**
	 * 根据文件相对路径，生成绝对存储路径
	 * @param path 文件相对存储路径，包括文件名称
	 * @return 文件绝对存储路径
	 */
	protected String concatRealFilePathString(String path) {
		String basePath=StringUtils.removeEnd(getBasePath(), "/");
		path=StringUtils.removeStart(path, "/");
		String realFilePath=basePath+"/"+path;
		realFilePath=FilenameUtils.normalize(realFilePath, true);
		return realFilePath;
	}


	/**
	 * @return the serverName
	 * 服务器名称，用于数据检索和数据迁移
	 */
	public String getServerName() {
		return MediaServiceConfig.SERVER_NAME;
	}	
	public String getServerAddress() {
		return MediaServiceConfig.SERVER_ADDRESS;
	}	
	/**
	 * @return 服务器文件存储基地址
	 */
	public String getBasePath() {
		return MediaServiceConfig.BASE_PATH;
	}
	/**
	 * @return the relativePath
	 * 相对于文件服务器应用跟路径的相对路径
	 */
	public String getRelativePath() {
		return relativePath;
	}
	/**
	 * @param relativePath the relativePath to set
	 */
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
}
