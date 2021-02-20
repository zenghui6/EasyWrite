package com.zenghui.dao;

import com.zenghui.entity.VoteSwipers;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 小程序轮播图(VoteSwipers)表数据库访问层
 *
 * @author zenghui
 * @since 2021-02-09 21:55:39
 */
@Repository
public interface VoteSwipersDao extends JpaRepository<VoteSwipers, String> {


}

