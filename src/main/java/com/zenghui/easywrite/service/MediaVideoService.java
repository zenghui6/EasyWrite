package com.zenghui.easywrite.service;

import com.zenghui.easywrite.entity.media.MediaVideo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * 这个表是分发表，用来记录视频分发(MediaVideo)表服务接口
 *
 * @author zenghui
 * @since 2021-02-09 20:27:11
 */
public interface MediaVideoService {
    void add(MediaVideo mediaVideo);
    Page<MediaVideo> staffFindAllByKeywords(String keywords, int page, int size, Sort.Direction direction);
    Page<MediaVideo> findAllClass(String keywords, int page, int size, String active, Sort.Direction direction);
    Page<MediaVideo> findAllExist(String keywords, int page, int size, Sort.Direction direction);
    MediaVideo findOneById(String id);
    void update(MediaVideo mediaVideo);
    void deleteOne(String id);
}
