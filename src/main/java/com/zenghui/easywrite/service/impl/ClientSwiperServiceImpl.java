package com.zenghui.easywrite.service.impl;

import com.zenghui.easywrite.entity.client.ClientSwiper;
import com.zenghui.easywrite.dao.ClientSwiperDao;
import com.zenghui.easywrite.service.ClientSwiperService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 官网轮播图(ClientSwiper)表服务实现类
 *
 * @author zenghui
 * @since 2021-02-09 13:17:08
 */
@Service("clientSwiperService")
public class ClientSwiperServiceImpl implements ClientSwiperService {
    @Resource
    private ClientSwiperDao clientSwiperDao;

    @Override
    public ClientSwiper queryById(String id) {
        return this.clientSwiperDao.getOne(id);
    }

    @Override
    public List<ClientSwiper> getall() {
        return this.clientSwiperDao.findAll();

    }

    @Override
    public Page<ClientSwiper> queryAllByLimit(int offset, int limit) {
        return this.clientSwiperDao.findAll(PageRequest.of((offset - 1)
                * limit, limit));
    }

    @Override
    public ClientSwiper insert(ClientSwiper clientSwiper) {

        return this.clientSwiperDao.save(clientSwiper);
    }


    @Override
    public ClientSwiper update(ClientSwiper clientSwiper) {

        return this.clientSwiperDao.save(clientSwiper);
    }


    @Override
    public boolean deleteById(String id) {

        try {
            this.clientSwiperDao.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }
}
