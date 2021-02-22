package com.zenghui.easywrite.dao.miniprogram;

import com.zenghui.easywrite.entity.miniprogram.VoteMerchants;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 小程序活动赞助商(VoteMerchants)表数据库访问层
 *
 * @author zenghui
 * @since 2021-02-09 21:10:40
 */
@Repository
public interface VoteMerchantsDao extends JpaRepository<VoteMerchants, String> {
    /**
     * 分页查找所有标题或者内容包含该关键字且未被软删除的活动（录入活动页面用,「keywords」为空时返回所有）
     * @param keywords
     * @param pageable
     * @return
     */
//    @Query("SELECT new com.miguo.matrix.vo.miniprogram.MerchantVo(" +
//            "g.id," +
//            "g.createBy," +
//            "g.updateBy," +
//            "g.createAt," +
//            "g.updateAt," +
//            "g.isDel," +
//            "g.activityId," +
//            "g.merchantName," +
//            "g.merchantInfo," +
//            "g.merchantLogo," +
//            "a.activityName) " +
//            "FROM Merchant g " +
//            "LEFT JOIN Activity a ON g.activityId=a.id WHERE g.merchantName LIKE %:#{#keywords}% OR g.merchantInfo LIKE %:#{#keywords}%")
//    Page<MerchantVo> findMerchantByKeywords(String keywords, Pageable pageable);

    /**
     * 通过id找投票对象
     * @param id
     * @return
     */
    VoteMerchants findMerchantById(String id);

    /**
     * 通过id找激活的投票对象
     * @param id
     * @return
     */
    @Query(value = "select * from vote_merchants WHERE activity_id = :#{#id}",nativeQuery = true)
    List<VoteMerchants> findActiveMerchant(String id);
}

