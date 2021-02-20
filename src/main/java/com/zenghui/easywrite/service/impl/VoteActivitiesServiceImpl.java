package com.zenghui.easywrite.service.impl;

import com.zenghui.easywrite.entity.miniprogram.VoteActivities;
import com.zenghui.easywrite.dao.VoteActivitiesDao;
import com.zenghui.easywrite.service.VoteActivitiesService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (VoteActivities)表服务实现类
 *
 * @author zenghui
 * @since 2021-02-09 20:33:50
 */
@Service("voteActivitiesService")
public class VoteActivitiesServiceImpl implements VoteActivitiesService {
    @Resource
    private VoteActivitiesDao voteActivitiesDao;

    @Override
    public VoteActivities queryById(String id) {
        return this.voteActivitiesDao.getOne(id);
    }

    @Override
    public List<VoteActivities> getall() {
        return this.voteActivitiesDao.findAll();

    }

    @Override
    public Page<VoteActivities> queryAllByLimit(int offset, int limit) {
        return this.voteActivitiesDao.findAll(PageRequest.of((offset - 1)
                * limit, limit));
    }

    @Override
    public VoteActivities insert(VoteActivities voteActivities) {

        return this.voteActivitiesDao.save(voteActivities);
    }


    @Override
    public VoteActivities update(VoteActivities voteActivities) {

        return this.voteActivitiesDao.save(voteActivities);
    }


    @Override
    public boolean deleteById(String id) {

        try {
            this.voteActivitiesDao.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }
}
