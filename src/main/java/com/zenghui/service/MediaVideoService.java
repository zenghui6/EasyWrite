package com.zenghui.service;

import com.zenghui.entity.MediaVideo;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 这个表是分发表，用来记录视频分发(MediaVideo)表服务接口
 *
 * @author zenghui
 * @since 2021-02-09 20:27:11
 */
public interface MediaVideoService {
    MediaVideo queryById(String id);

    Page<MediaVideo> queryAllByLimit(int offset, int limit);

    MediaVideo insert(MediaVideo mediaVideo);

    MediaVideo update(MediaVideo mediaVideo);

    boolean deleteById(String id);

    List<MediaVideo> getall();
}
