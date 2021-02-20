package com.zenghui.easywrite.service.impl;

import com.zenghui.easywrite.entity.staff.ComStaff;
import com.zenghui.easywrite.dao.ComStaffDao;
import com.zenghui.easywrite.service.ComStaffService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 员工信息等级表(ComStaff)表服务实现类
 *
 * @author zenghui
 * @since 2021-02-20 18:12:17
 */
@Service("comStaffService")
public class ComStaffServiceImpl implements ComStaffService {
    @Resource
    private ComStaffDao comStaffDao;

    @Override
    public ComStaff queryById(String id) {
        return this.comStaffDao.getOne(id);
    }

    @Override
    public List<ComStaff> getall() {
        return this.comStaffDao.findAll();

    }

    @Override
    public Page<ComStaff> queryAllByLimit(int offset, int limit) {
        return this.comStaffDao.findAll(PageRequest.of((offset - 1)
                * limit, limit));
    }

    @Override
    public ComStaff insert(ComStaff comStaff) {

        return this.comStaffDao.save(comStaff);
    }


    @Override
    public ComStaff update(ComStaff comStaff) {

        return this.comStaffDao.save(comStaff);
    }


    @Override
    public boolean deleteById(String id) {

        try {
            this.comStaffDao.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }
}
