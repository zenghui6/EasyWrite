package com.zenghui.easywrite.service;

import com.zenghui.easywrite.entity.miniprogram.VoteActivities;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * (VoteActivities)表服务接口
 *
 * @author zenghui
 * @since 2021-02-09 20:33:50
 */
public interface VoteActivitiesService {
    VoteActivities queryById(String id);

    Page<VoteActivities> queryAllByLimit(int offset, int limit);

    VoteActivities insert(VoteActivities voteActivities);

    VoteActivities update(VoteActivities voteActivities);

    boolean deleteById(String id);

    List<VoteActivities> getall();
}
