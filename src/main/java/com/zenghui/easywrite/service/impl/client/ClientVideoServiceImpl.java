package com.zenghui.easywrite.service.impl.client;

import com.zenghui.easywrite.entity.client.ClientArticle;
import com.zenghui.easywrite.entity.client.ClientVideo;
import com.zenghui.easywrite.dao.client.ClientVideoDao;
import com.zenghui.easywrite.service.ClientVideoService;
import com.zenghui.easywrite.utils.SnowflakeIdWorker;
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
 * 这张表是官网放视频的 视频数据表(ClientVideo)表服务实现类
 *
 * @author zenghui
 * @since 2021-02-09 14:19:24
 */
@Service("clientVideoService")
public class ClientVideoServiceImpl implements ClientVideoService {
    @Resource
    private ClientVideoDao clientVideoDao;

    @Autowired
    private HttpSession session;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    /**
     * 批量下架视频
     * @param ids
     */
    public void delete(String ids){
        String[] deleteIds = ids.split(",");
        for (String deleteId : deleteIds) {
            clientVideoDao.deleteSomeById(deleteId, new Date(), (String) session.getAttribute("user"));
        }

    }

    /**
     * 添加视频草稿
     * @param video
     * @return
     */
    public String add(ClientVideo video){
        video.setVideoDate(new Date());
        video.setCreateAt(new Date());
        video.setUpdateAt(new Date());
        video.setId(snowflakeIdWorker.nextId());
        video.setVideoStatus("draft");
        video.setIsDel(false);
        clientVideoDao.save(video);
        return video.getId();
    }

    /**
     * 分页返回标题或内容包含某关键字且未被下架的视频条目（客户使用）
     * @param keywords
     * @param page
     * @param size
     * @return
     */
    public Page<ClientVideo> findAllByKeywords(String keywords,int page,int size){
        page--;
        Pageable pageable = PageRequest.of(page, size);
        return clientVideoDao.findVideoByKeywords(keywords,pageable);
    }

    /**
     * 分页返回标题或内容包含某关键字的视频条目（员工使用），当关键字为空时按更新时间顺序返回所有
     * @param keywords
     * @param page
     * @param size
     * @param direction
     * @return
     */
    public Page<ClientVideo> staffFindAllByKeywords(String keywords, int page, int size, Sort.Direction direction){
        page--;
        Pageable pageable = PageRequest.of(page, size, direction, "update_at");
        return clientVideoDao.staffFindVideoByKeywords(keywords,pageable);
    }

    @Override
    public Page<ClientVideo> staffFindAllByKeywordsAndStatus(String keywords, String status, int page, int size, Sort.Direction direction) {
        page--;
        Pageable pageable = PageRequest.of(page, size, direction, "update_at");
        return clientVideoDao.staffFindVideoByKeywordsAndStatus(keywords, pageable,status);
    }

    /**
     * 分页分类返回所有视频条目
     * @param page
     * @param size
     * @return
     */
    public Page<ClientVideo> findAllClass(String keywords, int page,int size,  Sort.Direction direction){
        page--;
        Pageable pageable = PageRequest.of(page,size, direction, "create_at");
        return clientVideoDao.findAllClassVideo(keywords, pageable);
    }

    /**
     * 分页不分类返回所有视频条目
     * @param page
     * @param size
     * @return
     */
    public Page<ClientVideo> findAllExist(String keywords, int page, int size, Sort.Direction direction){
        page--;
        Pageable pageable = PageRequest.of(page, size, direction, "create_at");
        return clientVideoDao.findAllExistVideo(keywords, pageable);
    }

    /**
     * 通过id找视频
     * @param id
     * @return
     */
    public ClientVideo findOneById(String id){
        return clientVideoDao.findVideoById(id);
    }

    /**
     * 更新视频
     * @param video
     */
    public void update(ClientVideo video){
        ClientVideo videoTemp=this.findOneById(video.getId());
        BeanUtils.copyProperties(video, videoTemp);
        videoTemp.setUpdateAt(new Date());
        videoTemp.setUpdateBy((String) session.getAttribute("user"));
        clientVideoDao.saveAndFlush(videoTemp);
    }

    /**
     * 删除一个视频
     * @param id
     */
    public void deleteOne(String id){
        clientVideoDao.deleteById(id);
    }
}
