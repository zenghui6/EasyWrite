package com.zenghui.service.impl;

import com.zenghui.entity.ClientArticle;
import com.zenghui.dao.ClientArticleDao;
import com.zenghui.service.ClientArticleService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

import org.springframework.data.domain.Page;

/**
 * (ClientArticle)表服务实现类
 *
 * @author zenghui
 * @since 2021-02-09 11:07:59
 */
@Service("clientArticleService")
public class ClientArticleServiceImpl implements ClientArticleService {
    @Resource
    private ClientArticleDao clientArticleDao;

    @Override
    public ClientArticle queryById(String id) {
        return this.clientArticleDao.getOne(id);
    }

    @Override
    public List<ClientArticle> getall() {
        return this.clientArticleDao.findAll();

    }

    @Override
    public Page<ClientArticle> queryAllByLimit(int offset, int limit) {
        return this.clientArticleDao.findAll(PageRequest.of((offset - 1)
                * limit, limit));
    }

    @Override
    public ClientArticle insert(ClientArticle clientArticle) {

        return this.clientArticleDao.save(clientArticle);
    }


    @Override
    public ClientArticle update(ClientArticle clientArticle) {

        return this.clientArticleDao.save(clientArticle);
    }


    @Override
    public boolean deleteById(String id) {

        try {
            this.clientArticleDao.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }
}
