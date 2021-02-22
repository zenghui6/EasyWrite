package com.zenghui.easywrite.service;

import com.zenghui.easywrite.entity.client.ClientVideo;
import com.zenghui.easywrite.entity.client.ClientVideo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * 这张表是官网放视频的 视频数据表(ClientVideo)表服务接口
 *
 * @author zenghui
 * @since 2021-02-09 14:19:24
 */
public interface ClientVideoService {
    void add(ClientVideo clientvideo);
    void delete(String ids);
    void deleteOne(String id);
    Page<ClientVideo> staffFindAllByKeywords(String keywords, int page, int size, Sort.Direction direction);
    Page<ClientVideo> findAllByKeywords(String keywords,int page,int size);
    ClientVideo findOneById(String id);
    void update(ClientVideo swiper);
    Page<ClientVideo> findAllClass(String keywords, int page,int size, String active, Sort.Direction direction);
    Page<ClientVideo> findAllExist(String keywords, int page,int size, Sort.Direction direction);
}
