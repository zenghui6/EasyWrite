package com.zenghui.service;

import com.zenghui.entity.VoteNotes;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 小程序公告栏(VoteNotes)表服务接口
 *
 * @author zenghui
 * @since 2021-02-09 21:16:30
 */
public interface VoteNotesService {
    VoteNotes queryById(String id);

    Page<VoteNotes> queryAllByLimit(int offset, int limit);

    VoteNotes insert(VoteNotes voteNotes);

    VoteNotes update(VoteNotes voteNotes);

    boolean deleteById(String id);

    List<VoteNotes> getall();
}
