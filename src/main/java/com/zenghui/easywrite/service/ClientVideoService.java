package com.zenghui.easywrite.service;

import com.zenghui.easywrite.entity.client.ClientArticle;
import com.zenghui.easywrite.entity.client.ClientVideo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * 这张表是官网放视频的 视频数据表(ClientVideo)表服务接口
 *
 * @author zenghui
 * @since 2021-02-09 14:19:24
 */
public interface ClientVideoService {
    String add(ClientVideo clientvideo);
    void delete(String ids);
    void deleteOne(String id);
    Page<ClientVideo> staffFindAllByKeywords(String keywords, int page, int size, Sort.Direction direction);
    Page<ClientVideo> findAllByKeywords(String keywords,int page,int size);
    ClientVideo findOneById(String id);
    void update(ClientVideo video);

    Page<ClientVideo> staffFindAllByKeywordsAndStatus(String keywords, String status, int page, int size, Sort.Direction direction);

    Page<ClientVideo> findAllClass(String keywords, int page, int size, String active, Sort.Direction direction);
    Page<ClientVideo> findAllExist(String keywords, int page,int size, Sort.Direction direction);
}
