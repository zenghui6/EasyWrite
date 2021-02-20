package com.zenghui.dao;

import com.zenghui.entity.VoteGroups;

import org.springframework.stereotype.Repository;

import java.util.List;

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

