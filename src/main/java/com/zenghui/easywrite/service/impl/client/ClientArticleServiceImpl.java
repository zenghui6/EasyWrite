package com.zenghui.easywrite.service.impl.client;

import com.zenghui.easywrite.dao.client.ClientArticleDao;
import com.zenghui.easywrite.entity.client.ClientArticle;
import com.zenghui.easywrite.service.ClientArticleService;
import com.zenghui.easywrite.utils.SnowflakeIdWorker;
import com.zenghui.easywrite.vo.web.ArticleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * (ClientClientArticle)表服务实现类
 *
 * @author zenghui
 * @since 2021-02-09 11:07:59
 */
@Service("clientClientArticleService")
public class ClientArticleServiceImpl implements ClientArticleService {
    @Resource
    private ClientArticleDao clientArticleDao;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private HttpSession session;

    public void ClientArticleService() {
    }

    /**
     * ※员工方法
     *
     * 添加文章
     * @param article
     * @return 返回文章id
     */
    @Override
    public String add(ClientArticle article){
//        article.setCreateAt(new Date());
        article.setUpdateAt(new Date());
        article.setId(snowflakeIdWorker.nextId());
        article.setIsDel(false);
        article.setStatus("draft");
        clientArticleDao.save(article);
        return article.getId();
    }

    /**
     * ※审核方法
     *
     * 批量下架文章
     * @param ids
     */
    @Override
    public void delete(String ids){
        String[] deleteIds = ids.split(",");
        for (String deleteId : deleteIds) {
            clientArticleDao.deleteSomeById(deleteId, new Date(), (String) session.getAttribute("user"));
        }
    }

    /**
     * ※员工方法
     *
     * 删除一篇文章
     * @param id
     */
    @Override
    public void deleteOne(String id){
        clientArticleDao.deleteById(id);
    }


    /**
     * ※客户方法
     *
     * 返回挂上首页的文章条目
     * @param keywords
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<ArticleVo> findSome(String keywords, int page, int size){
        page--;
        Pageable pageable = PageRequest.of(page, size);
        return clientArticleDao.findSomeArticle(keywords, pageable);
    }

    /**
     * ※客户方法
     *
     * 分页返回标题或内容包含某关键字且未被下架的文章条目
     * @param keywords
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<ClientArticle> findAllByKeywords(String keywords,int page,int size){
        page--;
        Pageable pageable = PageRequest.of(page, size);
        return clientArticleDao.findArticleByKeywords(keywords,pageable);
    }

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
    @Override
    public Page<ClientArticle> staffFindAllByKeywords(String keywords, int page, int size, Sort.Direction direction){
        page--;
        Pageable pageable = PageRequest.of(page, size, direction, "update_at");
        return clientArticleDao.staffFindArticleByKeywords(keywords, pageable);
    }

    /**
     * ※审核方法
     *
     * 分页分类返回所有文章条目
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<ClientArticle> findAllClass(String keywords, int page,int size, String active, Sort.Direction direction){
        page--;
        Pageable pageable=PageRequest.of(page,size, direction, "create_at");
        Boolean activeTemp = Boolean.parseBoolean(active);
        return clientArticleDao.findAllClassArticle(keywords, pageable, activeTemp);
    }

    /**
     * ※审核方法
     *
     * 分页不分类返回所有文章条目
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<ClientArticle> findAllExist(String keywords, int page,int size, Sort.Direction direction){
        page--;
        Pageable pageable=PageRequest.of(page,size, direction, "create_at");
        return clientArticleDao.findAllExistArticle(keywords, pageable);
    }

    /**
     * 通过id找文章
     * @param id
     * @return ClientArticle
     */
    @Override
    public ClientArticle findOneById(String id)
    {
        return clientArticleDao.findArticleById(id);
    }

    /**
     * 更新文章
     * @param article
     */
    @Override
    public void update(ClientArticle article){
        ClientArticle articleTemp=clientArticleDao.findArticleById(article.getId());
        BeanUtils.copyProperties(article, articleTemp);
        articleTemp.setUpdateBy((String) session.getAttribute("user"));
        articleTemp.setUpdateAt(new Date());
        clientArticleDao.saveAndFlush(articleTemp);
    }
}
