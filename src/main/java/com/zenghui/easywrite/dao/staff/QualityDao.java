package com.zenghui.easywrite.dao.staff;

import com.zenghui.easywrite.entity.client.ClientArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface QualityDao extends JpaRepository<ClientArticle, String> {
    /**
     //     * 统计文章数量
     //     * @return
     //     */
//    @Query(value = "SELECT count(*) from client_article",nativeQuery = true)
//    Integer countArticle();
//
//    /**
//     * 统计视频数量
//     * @return
//     */
//    @Query(value = "SELECT count(*) from client_video",nativeQuery = true)
//    Integer countVideo();
//
//    /**
//     * 统计轮播图数量
//     * @return
//     */
//    @Query(value = "SELECT count(*) from client_swiper",nativeQuery = true)
//    Integer countSwiper();

    /**
     * 统计所有数据
     * @return
     */
    @Query(value = "select t1.articleWebCount, t2.videoWebCount, t3.swiperWebCount, t4.viewWebCount, " +
            "t5.activityMiniCount, t6.userMiniCount, t7.merchantMiniCount, t8.staffWebCount from " +
            "(select count(*) articleWebCount from client_article) t1, " +
            "(select count(*) videoWebCount from client_video) t2, " +
            "(select count(*) swiperWebCount from client_swiper) t3, " +
            "(select count(*) viewWebCount from client_swiper) t4, " +
            "(select count(*) activityMiniCount from vote_activities) t5, " +
            "(select count(*) userMiniCount from vote_records) t6, " +
            "(select count(*) merchantMiniCount from vote_merchants) t7, " +
            "(select count(*) staffWebCount from com_staff) t8" ,nativeQuery = true)
    Map<String, Integer> countAll();
}
