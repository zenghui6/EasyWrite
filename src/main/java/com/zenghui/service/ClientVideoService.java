package com.zenghui.service;

import com.zenghui.entity.ClientVideo;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 这张表是官网放视频的 视频数据表(ClientVideo)表服务接口
 *
 * @author zenghui
 * @since 2021-02-09 14:19:24
 */
public interface ClientVideoService {
    ClientVideo queryById(String id);

    Page<ClientVideo> queryAllByLimit(int offset, int limit);

    ClientVideo insert(ClientVideo clientVideo);

    ClientVideo update(ClientVideo clientVideo);

    boolean deleteById(String id);

    List<ClientVideo> getall();
}
