package com.zenghui.easywrite.dao.miniprogram;

import com.zenghui.easywrite.entity.miniprogram.VoteActivities;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * (VoteActivities)表数据库访问层
 *
 * @author zenghui
 * @since 2021-02-09 20:33:50
 */
@Repository
public interface VoteActivitiesDao extends JpaRepository<VoteActivities, String> {

    /**
     * 查找所有标题或者内容包含该关键字的活动（不分页，输入活动相关信息时联结用）
     * @param keywords
     * @return
     */
    @Query(value = "select * from vote_activities WHERE activity_name LIKE %:#{#keywords}% OR activity_profile LIKE %:#{#keywords}%",nativeQuery = true)
    List<VoteActivities> findActivityByKeywordsFromInput(String keywords);

    /**
     * 分页查找所有标题或者内容包含该关键字且未被软删除的活动（录入活动页面用,「keywords」为空时返回所有）
     * @param keywords
     * @param pageable
     * @return
     */
    @Query(value = "select * from vote_activities WHERE activity_name LIKE %:#{#keywords}% OR activity_profile LIKE %:#{#keywords}%",nativeQuery = true)
    Page<VoteActivities> findActivityByKeywords(String keywords, Pageable pageable);

    /**
     * 通过id找活动
     * @param id
     * @return
     */
    VoteActivities findActivityById(String id);

    /**
     * 查找被激活的活动
     * @return
     */
    @Query(value = "select * from vote_activities WHERE activity_active = true",nativeQuery = true)
    List<VoteActivities> findActiveActivity();
}

