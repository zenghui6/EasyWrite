package com.zenghui.easywrite.service;

import com.zenghui.easywrite.entity.client.ClientArticle;

import java.util.List;

import com.zenghui.easywrite.vo.web.ArticleVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 * (ClientArticle)表服务接口
 *
 * @author zenghui
 * @since 2021-02-09 11:07:58
 */
public interface ClientArticleService {
    String add(ClientArticle article);

    /**
     * 批量下架文章
     * @param ids
     */
    void delete(String ids);

    void deleteOne(String id);

    /**
     * ※客户方法
     *
     * 返回挂上首页的文章条目
     * @param keywords
     * @param page
     * @param size
     * @return
     */
    Page<ArticleVo> findSome(String keywords, int page, int size);

    /**
     * ※客户方法
     *
     * 分页返回标题或内容包含某关键字且未被下架的文章条目
     * @param keywords
     * @param page
     * @param size
     * @return
     */
    Page<ClientArticle> findAllByKeywords(String keywords,int page,int size);

    /**
     * ※员工方法
     *
     * 分页返回标题或内容包含某关键字的文章条目，当关键字为空时按更新时间顺序返回所有
     * @param keywords
     * @param page
     * @param size
     * @param direction
     * @return
     */
    Page<ClientArticle> staffFindAllByKeywords(String keywords, int page, int size, Sort.Direction direction);

    /**
     * ※审核方法
     *
     * 分页分类返回所有文章条目
     * @param page
     * @param size
     * @return
     */
    Page<ClientArticle> findAllClass(String keywords, int page,int size, String active, Sort.Direction direction);

    /**
     * ※审核方法
     *
     * 分页不分类返回所有文章条目
     * @param page
     * @param size
     * @return
     */
    Page<ClientArticle> findAllExist(String keywords, int page,int size, Sort.Direction direction);

    /**
     * 通过id找文章
     * @param id
     * @return
     */
    ClientArticle findOneById(String id);

    /**
     * 更新文章
     * @param article
     */
    void update(ClientArticle article);

}
