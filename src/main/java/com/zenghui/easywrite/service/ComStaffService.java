package com.zenghui.easywrite.service;

import com.zenghui.easywrite.dto.LoginDTO;
import com.zenghui.easywrite.dto.RegisterDTO;
import com.zenghui.easywrite.entity.staff.ComStaff;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * 员工信息等级表(ComStaff)表服务接口
 *
 * @author zenghui
 * @since 2021-02-20 18:12:17
 */
public interface ComStaffService {
    void add(ComStaff account);

    Page<ComStaff> findAllExist(int page, int size);

    Page<ComStaff> findAllDeleted(int page,int size);

    Page<ComStaff> findAllByKeywords(String keywords, int page, int size, Sort.Direction direction);

    ComStaff findOneById(String id);

    ComStaff executeRegister(RegisterDTO dto);

    String executeLogin(LoginDTO dto);

    ComStaff getUserByUsername(String username);

    void updata(ComStaff comStaff);

    void modify(String ids, String active);

    Page<ComStaff> findAllByKeywordsAndLevel(String keywords, String level, int page, int size, Sort.Direction direction);
}
