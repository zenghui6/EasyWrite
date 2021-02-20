package com.zenghui.dao;

import com.zenghui.entity.ClientSwiper;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 官网轮播图(ClientSwiper)表数据库访问层
 *
 * @author zenghui
 * @since 2021-02-09 13:17:07
 */
@Repository
public interface ClientSwiperDao extends JpaRepository<ClientSwiper, String> {


}

