package com.zenghui.dao;

import com.zenghui.entity.VoteNotes;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 小程序公告栏(VoteNotes)表数据库访问层
 *
 * @author zenghui
 * @since 2021-02-09 21:16:30
 */
@Repository
public interface VoteNotesDao extends JpaRepository<VoteNotes, String> {


}

