package com.zenghui.easywrite.service.impl.miniprogram;

import com.zenghui.easywrite.entity.miniprogram.VoteGroups;
import com.zenghui.easywrite.dao.miniprogram.VoteGroupsDao;
import com.zenghui.easywrite.service.VoteGroupsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 投票对象实体(VoteGroups)表服务实现类
 *
 * @author zenghui
 * @since 2021-02-09 20:53:01
 */
@Service("voteGroupsService")
public class VoteGroupsServiceImpl implements VoteGroupsService {
    @Resource
    private VoteGroupsDao voteGroupsDao;

    @Override
    public VoteGroups queryById(String id) {
        return this.voteGroupsDao.getOne(id);
    }

    @Override
    public List<VoteGroups> getall() {
        return this.voteGroupsDao.findAll();

    }

    @Override
    public Page<VoteGroups> queryAllByLimit(int offset, int limit) {
        return this.voteGroupsDao.findAll(PageRequest.of((offset - 1)
                * limit, limit));
    }

    @Override
    public VoteGroups insert(VoteGroups voteGroups) {

        return this.voteGroupsDao.save(voteGroups);
    }


    @Override
    public VoteGroups update(VoteGroups voteGroups) {

        return this.voteGroupsDao.save(voteGroups);
    }


    @Override
    public boolean deleteById(String id) {

        try {
            this.voteGroupsDao.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }
}
