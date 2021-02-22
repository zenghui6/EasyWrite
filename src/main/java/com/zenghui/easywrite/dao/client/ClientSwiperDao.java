package com.zenghui.easywrite.dao.client;

import com.zenghui.easywrite.entity.client.ClientSwiper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 官网轮播图(ClientSwiper)表数据库访问层
 *
 * @author zenghui
 * @since 2021-02-09 13:17:07
 */
@Repository
public interface ClientSwiperDao extends JpaRepository<ClientSwiper, String> {
    /**
     * 通过把「is_del」改为「true」来下架多个轮播图（并非删除）
     * @param id
     * @param date
     * @param updateBy
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    @Query(value = "update client_swiper set is_del = true , update_at = :#{#date},update_by = :#{#updateBy} where id = :#{#id} ", nativeQuery = true)
    void deleteById(String id, Date date, String updateBy);

    /**
     * 分页返回标题或内容包含某关键字的文章条目（员工使用），当关键字为空时按更新时间顺序返回所有
     * @param keywords
     * @param pageable
     * @return
     */
    @Query(value = "select * from client_swiper WHERE swiper_name LIKE %:#{#keywords}%",nativeQuery = true)
    Page<ClientSwiper> staffFindSwiperByKeywords(String keywords, Pageable pageable);

    /**
     * 查找所有已被软删除的轮播图
     * @param pageable
     * @return
     */
    @Query(value = "select * from client_swiper where is_del = true",nativeQuery = true)
    Page<ClientSwiper> findAllDeletedSwiper(Pageable pageable);

    /**
     * 查找所有未被软删除的轮播图
     * @param pageable
     * @return
     */
    @Query(value = "select * from client_swiper where is_del = false",nativeQuery = true)
    Page<ClientSwiper> findAllExistSwiper(Pageable pageable);

    /**
     * 查找所有未被软删除的轮播图（客户使用）
     * @param
     * @return
     */
    @Query(value = "select * from client_swiper where is_del = false and swiper_status = 'reviewed'",nativeQuery = true)
    List<ClientSwiper> clientFindAllExistSwiper();

    /**
     * 通过id找轮播图
     * @param id
     * @return
     */
    ClientSwiper findWebSwiperById(String id);

    /**
     * ※审核方法
     *
     * 分页按条件分类查找删除与否的文章
     * @param pageable
     * @param active
     * @param keywords
     * @return
     */
    @Query(value = "select * from client_swiper where ( swiper_name LIKE %:#{#keywords}% ) and is_del = :active and swiper_status != 'draft'", nativeQuery = true)
    Page<ClientSwiper> findAllClassSwiper(String keywords, Pageable pageable, Boolean active);

    /**
     * ※审核方法
     *
     * 分页返回所有的文章条目
     * @param pageable
     * @param keywords
     * @return
     */
    @Query(value = "select * from client_swiper where ( swiper_name LIKE %:#{#keywords}% ) and swiper_status != 'draft'", nativeQuery = true)
    Page<ClientSwiper> findAllExistSwiper(String keywords, Pageable pageable);
}

