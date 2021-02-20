package com.zenghui.service;

import com.zenghui.entity.ClientArticle;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * (ClientArticle)表服务接口
 *
 * @author zenghui
 * @since 2021-02-09 11:07:58
 */
public interface ClientArticleService {
    ClientArticle queryById(String id);

    Page<ClientArticle> queryAllByLimit(int offset, int limit);

    ClientArticle insert(ClientArticle clientArticle);

    ClientArticle update(ClientArticle clientArticle);

    boolean deleteById(String id);

    List<ClientArticle> getall();
}
