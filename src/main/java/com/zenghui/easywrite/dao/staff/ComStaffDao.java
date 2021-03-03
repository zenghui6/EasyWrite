package com.zenghui.easywrite.dao.staff;

import com.zenghui.easywrite.entity.staff.ComStaff;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 员工信息等级表(ComStaff)表数据库访问层
 *
 * @author zenghui
 * @since 2021-02-20 18:12:17
 */
@Repository
public interface ComStaffDao extends JpaRepository<ComStaff, String> {

    /**
     * 注册时查找是否 账号或昵称已被注册
     * @param nickname
     * @param name
     * @return
     */
    ComStaff findComStaffByNicknameEqualsAndNameEquals(String nickname,String name);


    /**
     * 通过id(账号)查找某一个用户，忽略是否被软删除过
     * @param id
     * @return
     */
    ComStaff findNicknameById(String id);


    /**
     * 通过name(账号)查找某一个用户，忽略是否被软删除过
     * @param name
     * @return
     */
    ComStaff findByName(String name);

    /**
     * 软删除某一个用户
     * @param id
     * @param date
     * @param updateBy
     * @param active
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "update com_staff set is_del = :#{#active} , update_at = :#{#date} , update_by = :#{#updateBy} where id = :#{#id} ", nativeQuery = true)
    void deleteByNickname(Boolean active, String id, Date date, String updateBy);

    /**
     * 查询所有未被软删除的用户
     * @param pageable
     * @return
     */
    @Query(value = "select * from com_staff where is_del = false",nativeQuery = true)
    Page<ComStaff> findAllExistAccount(Pageable pageable);

    /**
     * 查询所有已被软删除的用户
     * @param pageable
     * @return
     */
    @Query(value="select * from com_staff where is_del = true",nativeQuery = true)
    Page<ComStaff> findAllDeletedAccount(Pageable pageable);

    /**
     * 根据关键词、存在状态查询,不论是否删除
     * @param keywords
     * @param pageable
     * @return
     */
    @Query(value = "select * from com_staff WHERE  name LIKE %:#{#keywords}% OR nickname LIKE %:#{#keywords}% ", nativeQuery = true)
    Page<ComStaff> findAccountByKeywords(String keywords,  Pageable pageable);

    @Query(value = "select * from com_staff WHERE  (name LIKE %:#{#keywords}% OR nickname LIKE %:#{#keywords}%) AND level = :#{#level} ", nativeQuery = true)
    Page<ComStaff> findAccountByKeywordsAndLevel(String keywords, String level, Pageable pageable);
}

