package com.zenghui.easywrite.service;

import com.zenghui.easywrite.entity.staff.ComStaff;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * 员工信息等级表(ComStaff)表服务接口
 *
 * @author zenghui
 * @since 2021-02-20 18:12:17
 */
public interface ComStaffService {
    ComStaff queryById(String id);

    Page<ComStaff> queryAllByLimit(int offset, int limit);

    ComStaff insert(ComStaff comStaff);

    ComStaff update(ComStaff comStaff);

    boolean deleteById(String id);

    List<ComStaff> getall();
}
