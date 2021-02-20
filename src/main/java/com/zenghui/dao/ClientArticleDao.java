package com.zenghui.dao;

import com.zenghui.entity.ClientArticle;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * (ClientArticle)表数据库访问层
 *
 * @author zenghui
 * @since 2021-02-09 11:07:57
 */
@Repository
public interface ClientArticleDao extends JpaRepository<ClientArticle, String> {


}

