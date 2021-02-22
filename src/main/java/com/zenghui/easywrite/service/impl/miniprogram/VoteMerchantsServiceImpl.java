package com.zenghui.easywrite.service.impl.miniprogram;

import com.zenghui.easywrite.entity.miniprogram.VoteMerchants;
import com.zenghui.easywrite.dao.miniprogram.VoteMerchantsDao;
import com.zenghui.easywrite.service.VoteMerchantsService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 小程序活动赞助商(VoteMerchants)表服务实现类
 *
 * @author zenghui
 * @since 2021-02-09 21:10:41
 */
@Service("voteMerchantsService")
public class VoteMerchantsServiceImpl implements VoteMerchantsService {
    @Resource
    private VoteMerchantsDao voteMerchantsDao;

    @Override
    public VoteMerchants queryById(String id) {
        return this.voteMerchantsDao.getOne(id);
    }

    @Override
    public List<VoteMerchants> getall() {
        return this.voteMerchantsDao.findAll();

    }

    @Override
    public Page<VoteMerchants> queryAllByLimit(int offset, int limit) {
        return this.voteMerchantsDao.findAll(PageRequest.of((offset - 1)
                * limit, limit));
    }

    @Override
    public VoteMerchants insert(VoteMerchants voteMerchants) {

        return this.voteMerchantsDao.save(voteMerchants);
    }


    @Override
    public VoteMerchants update(VoteMerchants voteMerchants) {

        return this.voteMerchantsDao.save(voteMerchants);
    }


    @Override
    public boolean deleteById(String id) {

        try {
            this.voteMerchantsDao.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }
}
