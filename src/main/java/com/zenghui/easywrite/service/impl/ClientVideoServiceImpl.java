package com.zenghui.easywrite.service.impl;

import com.zenghui.easywrite.entity.client.ClientVideo;
import com.zenghui.easywrite.dao.ClientVideoDao;
import com.zenghui.easywrite.service.ClientVideoService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 这张表是官网放视频的 视频数据表(ClientVideo)表服务实现类
 *
 * @author zenghui
 * @since 2021-02-09 14:19:24
 */
@Service("clientVideoService")
public class ClientVideoServiceImpl implements ClientVideoService {
    @Resource
    private ClientVideoDao clientVideoDao;

    @Override
    public ClientVideo queryById(String id) {
        return this.clientVideoDao.getOne(id);
    }

    @Override
    public List<ClientVideo> getall() {
        return this.clientVideoDao.findAll();

    }

    @Override
    public Page<ClientVideo> queryAllByLimit(int offset, int limit) {
        return this.clientVideoDao.findAll(PageRequest.of((offset - 1)
                * limit, limit));
    }

    @Override
    public ClientVideo insert(ClientVideo clientVideo) {

        return this.clientVideoDao.save(clientVideo);
    }


    @Override
    public ClientVideo update(ClientVideo clientVideo) {

        return this.clientVideoDao.save(clientVideo);
    }


    @Override
    public boolean deleteById(String id) {

        try {
            this.clientVideoDao.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }
}
