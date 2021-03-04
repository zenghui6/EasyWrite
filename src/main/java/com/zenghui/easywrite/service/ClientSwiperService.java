package com.zenghui.easywrite.service;

import com.zenghui.easywrite.entity.client.ClientSwiper;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * 官网轮播图(ClientSwiper)表服务接口
 *
 * @author zenghui
 * @since 2021-02-09 13:17:07
 */
public interface ClientSwiperService {
    void add(ClientSwiper clientSwiper);
    void deleteSome(String ids);
    void deleteOne(String id);
    Page<ClientSwiper> staffFindAllByKeywords(String keywords, int page, int size, Sort.Direction direction);
    Page<ClientSwiper> findAllDeleted(int page,int size);
    Page<ClientSwiper> findAllExist(int page,int size);
    List<ClientSwiper> clientFindAllExist();
    ClientSwiper findOneById(String id);
    void update(ClientSwiper swiper);
    Page<ClientSwiper> findAllClass(String keywords, int page,int size, Sort.Direction direction);
    Page<ClientSwiper> findAllExist(String keywords, int page,int size, Sort.Direction direction);

    Page<ClientSwiper> staffFindAllByKeywordsAndStatus(String keywords, String status, int page, int size, Sort.Direction direction);
}
