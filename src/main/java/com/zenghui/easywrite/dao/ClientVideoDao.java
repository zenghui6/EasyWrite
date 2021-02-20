package com.zenghui.easywrite.dao;

import com.zenghui.easywrite.entity.client.ClientVideo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 这张表是官网放视频的 视频数据表(ClientVideo)表数据库访问层
 *
 * @author zenghui
 * @since 2021-02-09 14:19:24
 */
@Repository
public interface ClientVideoDao extends JpaRepository<ClientVideo, String> {


}

