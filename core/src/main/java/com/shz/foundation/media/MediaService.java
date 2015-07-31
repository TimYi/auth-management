package com.shz.foundation.media;

import java.io.IOException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * 对媒体内容进行增删改查操作，由于涉及到文件的保存和删除，不能直接使用Repository进行这些操作。
 * @author pc
 *
 */
@Transactional
public interface MediaService {
	MediaContent getMedia(String id);
	/**
	 * 保存上传的文件
	 * @param file
	 * @return 返对应的 @MediaContent
	 */
	MediaContent save(MultipartFile file) throws IOException;
	
	/**
	 * 更新已有MediaContent，如果已有 @MediaContent 为空，返回一个新的 @MediaContent
	 * @param content
	 * @param file
	 * @return
	 */
	MediaContent update(MediaContent media, MultipartFile file) throws IOException;
	/**
	 * 更新已有MediaContent，如果已有 @MediaContent 为空，返回一个新的 @MediaContent
	 * @param id
	 * @param file
	 * @return
	 * @throws IOException
	 */
	MediaContent update(String id, MultipartFile file) throws IOException;
	
	/**
	 * 删除对应文件，而MediaContent实体的删除交给Repository处理。
	 * @param content
	 * @throws IOException
	 */
	void delete(MediaContent media) throws IOException;
	/**
	 * 删除对应文件，而MediaContent实体的删除交给Repository处理。
	 * @param id
	 * @throws IOException
	 */
	void delete(String id) throws IOException;
}
