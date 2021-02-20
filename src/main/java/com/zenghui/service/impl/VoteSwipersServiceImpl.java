package com.zenghui.service.impl;

import com.zenghui.entity.VoteSwipers;
import com.zenghui.dao.VoteSwipersDao;
import com.zenghui.service.VoteSwipersService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 小程序轮播图(VoteSwipers)表服务实现类
 *
 * @author zenghui
 * @since 2021-02-09 21:55:40
 */
@Service("voteSwipersService")
public class VoteSwipersServiceImpl implements VoteSwipersService {
    @Resource
    private VoteSwipersDao voteSwipersDao;

    @Override
    public VoteSwipers queryById(String id) {
        return this.voteSwipersDao.getOne(id);
    }

    @Override
    public List<VoteSwipers> getall() {
        return this.voteSwipersDao.findAll();

    }

    @Override
    public Page<VoteSwipers> queryAllByLimit(int offset, int limit) {
        return this.voteSwipersDao.findAll(PageRequest.of((offset - 1)
                * limit, limit));
    }

    @Override
    public VoteSwipers insert(VoteSwipers voteSwipers) {

        return this.voteSwipersDao.save(voteSwipers);
    }


    @Override
    public VoteSwipers update(VoteSwipers voteSwipers) {

        return this.voteSwipersDao.save(voteSwipers);
    }


    @Override
    public boolean deleteById(String id) {

        try {
            this.voteSwipersDao.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }
}
