package com.zenghui.easywrite.service;

import com.zenghui.easywrite.entity.client.ClientSwiper;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 官网轮播图(ClientSwiper)表服务接口
 *
 * @author zenghui
 * @since 2021-02-09 13:17:07
 */
public interface ClientSwiperService {
    ClientSwiper queryById(String id);

    Page<ClientSwiper> queryAllByLimit(int offset, int limit);

    ClientSwiper insert(ClientSwiper clientSwiper);

    ClientSwiper update(ClientSwiper clientSwiper);

    boolean deleteById(String id);

    List<ClientSwiper> getall();
}
