package com.zenghui.easywrite.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 *  所有数据综合统计
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("数据统计实体")
public class CountVo {
    /**
     * 文章数
     */
    private Integer articleWebCount;
    /**
     * 视频数
     */
    private Integer videoWebCount;
    /**
     * 轮播图数
     */
    private Integer swiperWebCount;
    /**
     * 网站浏览量（暂无）
     */
    private Integer viewWebCount;

    /**
     * 使用次数
     */
    private Integer activityMiniCount;
    /**
     * 小程序使用人数
     */
    private Integer userMiniCount;
    /**
     * 赞助商数
     */
    private Integer merchantMiniCount;
    /**
     * 员工数
     */
    private Integer staffWebCount;

}