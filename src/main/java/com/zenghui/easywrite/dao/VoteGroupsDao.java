package com.zenghui.easywrite.dao;

import com.zenghui.easywrite.entity.miniprogram.VoteGroups;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 投票对象实体(VoteGroups)表数据库访问层
 *
 * @author zenghui
 * @since 2021-02-09 20:53:00
 */
@Repository
public interface VoteGroupsDao extends JpaRepository<VoteGroups, String> {


}

