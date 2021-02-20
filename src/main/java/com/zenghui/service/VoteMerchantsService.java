package com.zenghui.service;

import com.zenghui.entity.VoteMerchants;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 小程序活动赞助商(VoteMerchants)表服务接口
 *
 * @author zenghui
 * @since 2021-02-09 21:10:40
 */
public interface VoteMerchantsService {
    VoteMerchants queryById(String id);

    Page<VoteMerchants> queryAllByLimit(int offset, int limit);

    VoteMerchants insert(VoteMerchants voteMerchants);

    VoteMerchants update(VoteMerchants voteMerchants);

    boolean deleteById(String id);

    List<VoteMerchants> getall();
}
