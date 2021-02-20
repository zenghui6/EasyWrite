package com.zenghui.easywrite.service.impl;

import com.zenghui.easywrite.entity.miniprogram.VoteNotes;
import com.zenghui.easywrite.dao.VoteNotesDao;
import com.zenghui.easywrite.service.VoteNotesService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 小程序公告栏(VoteNotes)表服务实现类
 *
 * @author zenghui
 * @since 2021-02-09 21:16:31
 */
@Service("voteNotesService")
public class VoteNotesServiceImpl implements VoteNotesService {
    @Resource
    private VoteNotesDao voteNotesDao;

    @Override
    public VoteNotes queryById(String id) {
        return this.voteNotesDao.getOne(id);
    }

    @Override
    public List<VoteNotes> getall() {
        return this.voteNotesDao.findAll();

    }

    @Override
    public Page<VoteNotes> queryAllByLimit(int offset, int limit) {
        return this.voteNotesDao.findAll(PageRequest.of((offset - 1)
                * limit, limit));
    }

    @Override
    public VoteNotes insert(VoteNotes voteNotes) {

        return this.voteNotesDao.save(voteNotes);
    }


    @Override
    public VoteNotes update(VoteNotes voteNotes) {

        return this.voteNotesDao.save(voteNotes);
    }


    @Override
    public boolean deleteById(String id) {

        try {
            this.voteNotesDao.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }
}
