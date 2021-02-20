package com.zenghui.easywrite.dao;

import com.zenghui.easywrite.entity.staff.ComStaff;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 员工信息等级表(ComStaff)表数据库访问层
 *
 * @author zenghui
 * @since 2021-02-20 18:12:17
 */
@Repository
public interface ComStaffDao extends JpaRepository<ComStaff, String> {


}

