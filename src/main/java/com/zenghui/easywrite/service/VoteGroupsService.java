package com.zenghui.easywrite.service;

import com.zenghui.easywrite.entity.miniprogram.VoteGroups;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 投票对象实体(VoteGroups)表服务接口
 *
 * @author zenghui
 * @since 2021-02-09 20:53:00
 */
public interface VoteGroupsService {
    VoteGroups queryById(String id);

    Page<VoteGroups> queryAllByLimit(int offset, int limit);

    VoteGroups insert(VoteGroups voteGroups);

    VoteGroups update(VoteGroups voteGroups);

    boolean deleteById(String id);

    List<VoteGroups> getall();
}
