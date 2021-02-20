package com.zenghui.easywrite.service;

import com.zenghui.easywrite.entity.miniprogram.VoteSwipers;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 小程序轮播图(VoteSwipers)表服务接口
 *
 * @author zenghui
 * @since 2021-02-09 21:55:39
 */
public interface VoteSwipersService {
    VoteSwipers queryById(String id);

    Page<VoteSwipers> queryAllByLimit(int offset, int limit);

    VoteSwipers insert(VoteSwipers voteSwipers);

    VoteSwipers update(VoteSwipers voteSwipers);

    boolean deleteById(String id);

    List<VoteSwipers> getall();
}
