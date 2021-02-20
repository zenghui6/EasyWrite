package com.zenghui.easywrite.dao;

import com.zenghui.easywrite.entity.miniprogram.VoteActivities;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * (VoteActivities)表数据库访问层
 *
 * @author zenghui
 * @since 2021-02-09 20:33:50
 */
@Repository
public interface VoteActivitiesDao extends JpaRepository<VoteActivities, String> {


}

