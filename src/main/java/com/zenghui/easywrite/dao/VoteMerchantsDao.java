package com.zenghui.easywrite.dao;

import com.zenghui.easywrite.entity.miniprogram.VoteMerchants;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 小程序活动赞助商(VoteMerchants)表数据库访问层
 *
 * @author zenghui
 * @since 2021-02-09 21:10:40
 */
@Repository
public interface VoteMerchantsDao extends JpaRepository<VoteMerchants, String> {


}

