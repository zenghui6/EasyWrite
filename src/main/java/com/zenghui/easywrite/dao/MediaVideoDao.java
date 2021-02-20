package com.zenghui.easywrite.dao;

import com.zenghui.easywrite.entity.media.MediaVideo;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 这个表是分发表，用来记录视频分发(MediaVideo)表数据库访问层
 *
 * @author zenghui
 * @since 2021-02-09 20:27:11
 */
@Repository
public interface MediaVideoDao extends JpaRepository<MediaVideo, String> {


}

