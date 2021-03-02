package com.zenghui.easywrite.service.impl.client;

import com.zenghui.easywrite.utils.SnowflakeIdWorker;
import com.zenghui.easywrite.entity.client.ClientSwiper;
import com.zenghui.easywrite.dao.client.ClientSwiperDao;
import com.zenghui.easywrite.service.ClientSwiperService;
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
import java.util.List;
import java.util.Optional;

/**
 * 官网轮播图(ClientSwiper)表服务实现类
 *
 * @author zenghui
 * @since 2021-02-09 13:17:08
 */
@Service("clientSwiperService")
public class ClientSwiperServiceImpl implements ClientSwiperService {
    @Resource
    private ClientSwiperDao clientSwiperDao;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private HttpSession session;
    /**
     * 添加文章
     * @param swiper
     */
    public void add(ClientSwiper swiper){
        swiper.setCreateAt(new Date());
        swiper.setUpdateAt(new Date());
        swiper.setId(snowflakeIdWorker.nextId());
        swiper.setIsDel(false);
        swiper.setCreateBy((String) session.getAttribute("user"));
        swiper.setUpdateBy((String) session.getAttribute("user"));
        clientSwiperDao.save(swiper);
    }

    /**
     * 批量下架轮播图
     * @param ids
     */
    public void deleteSome(String ids){
        String[] deleteIds = ids.split(",");
        for (String deleteId : deleteIds) {
            clientSwiperDao.deleteById(deleteId, new Date(), (String) session.getAttribute("user"));
        }
    }

    /**
     * 删除一个轮播图
     * @param id
     */
    public void deleteOne(String id){
        clientSwiperDao.deleteById(id);
    }

    /**
     * 分页返回标题或内容包含某关键字的文章条目（员工使用），当关键字为空时按更新时间顺序返回所有
     * @param keywords
     * @param page
     * @param size
     * @param direction
     * @return
     */
    public Page<ClientSwiper> staffFindAllByKeywords(String keywords, int page, int size, Sort.Direction direction){
        page--;
        Pageable pageable = PageRequest.of(page, size, direction, "update_at");
        return clientSwiperDao.staffFindSwiperByKeywords(keywords,pageable);
    }

    /**
     * 分页返回所有已被下架的轮播图条目
     * @param page
     * @param size
     * @return
     */
    public Page<ClientSwiper> findAllDeleted(int page,int size){
        page--;
        Pageable pageable=PageRequest.of(page,size);
        return clientSwiperDao.findAllDeletedSwiper(pageable);
    }

    /**
     * 分页返回所有未被下架的轮播图条目
     * @param page
     * @param size
     * @return
     */
    public Page<ClientSwiper> findAllExist(int page,int size){
        page--;
        Pageable pageable=PageRequest.of(page,size);
        return clientSwiperDao.findAllExistSwiper(pageable);
    }

    /**
     * 分页返回所有未被下架的轮播图条目
     * @param
     * @return
     */
    public List<ClientSwiper> clientFindAllExist(){
        return clientSwiperDao.clientFindAllExistSwiper();
    }

    /**
     * 通过id找轮播图
     * @param id
     * @return
     */
    public ClientSwiper findOneById(String id)
    {
        return clientSwiperDao.findWebSwiperById(id);
    }

    /**
     * 更新轮播图
     * @param swiper
     */
    public void update(ClientSwiper swiper){
        ClientSwiper swiperTemp=this.findOneById(swiper.getId());
        BeanUtils.copyProperties(swiper, swiperTemp);
        swiperTemp.setUpdateBy((String) session.getAttribute("user"));
        swiperTemp.setUpdateAt(new Date());
        clientSwiperDao.saveAndFlush(swiperTemp);
    }

    /**
     * ※审核方法
     *
     * 分页分类返回所有条目
     * @param page
     * @param size
     * @return
     */
    public Page<ClientSwiper> findAllClass(String keywords, int page,int size, String active, Sort.Direction direction){
        page--;
        Pageable pageable=PageRequest.of(page,size, direction, "create_at");
        Boolean activeTemp = Boolean.parseBoolean(active);
        return clientSwiperDao.findAllClassSwiper(keywords, pageable, activeTemp);
    }

    /**
     * ※审核方法
     *
     * 分页不分类返回所有条目
     * @param page
     * @param size
     * @return
     */
    public Page<ClientSwiper> findAllExist(String keywords, int page,int size, Sort.Direction direction){
        page--;
        Pageable pageable=PageRequest.of(page,size, direction, "create_at");
        return clientSwiperDao.findAllExistSwiper(keywords, pageable);
    }

    @Override
    public Page<ClientSwiper> staffFindAllByKeywordsAndStatus(String keywords, String status, int page, int size, Sort.Direction direction) {
        page--;
        Pageable pageable = PageRequest.of(page, size, direction, "update_at");
        return clientSwiperDao.staffFindSwiperByKeywordsAndStatus(keywords, pageable,status);
    }
}
