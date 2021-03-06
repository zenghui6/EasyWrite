package com.zenghui.easywrite.service.impl.media;

import com.zenghui.easywrite.entity.media.MediaVideo;
import com.zenghui.easywrite.dao.media.MediaVideoDao;
import com.zenghui.easywrite.service.MediaVideoService;
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
 * 这个表是分发表，用来记录视频分发(MediaVideo)表服务实现类
 *
 * @author zenghui
 * @since 2021-02-09 20:27:11
 */
@Service("mediaVideoService")
public class MediaVideoServiceImpl implements MediaVideoService {
    @Resource
    private MediaVideoDao mediaVideoDao;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Autowired
    private HttpSession session;

    /**
     * 添加视频
     * @param mediaVideo
     */
    public String add(MediaVideo mediaVideo){
        mediaVideo.setCreateAt(new Date());
        mediaVideo.setUpdateAt(new Date());
        mediaVideo.setUpdateBy("zh");
        mediaVideo.setCreateBy("zh");
        mediaVideo.setId(snowflakeIdWorker.nextId());
        mediaVideo.setIsDel(false);
        mediaVideoDao.save(mediaVideo);
        System.out.println(mediaVideo.getId());
        return mediaVideo.getId();
    }


    /**
     * 分页返回标题或内容包含某关键字的视频条目（员工使用），当关键字为空时按更新时间顺序返回所有
     * @param keywords
     * @param page
     * @param size
     * @param direction
     * @return
     */
    public Page<MediaVideo> staffFindAllByKeywords(String keywords, int page, int size, Sort.Direction direction){
        page--;
        Pageable pageable = PageRequest.of(page, size, direction, "update_at");
        return mediaVideoDao.staffFindVideoByKeywords(keywords,pageable);
    }

    @Override
    public Page<MediaVideo> staffFindAllByKeywordsAndStatus(String keywords, String status, int page, int size, Sort.Direction direction) {
        page--;
        Pageable pageable = PageRequest.of(page, size, direction, "update_at");
        return mediaVideoDao.staffFindVideoByKeywordsAndStatus(keywords, pageable,status);
    }
    /**
     * 分页分类返回所有视频条目
     * @param page
     * @param size
     * @return
     */
    public Page<MediaVideo> findAllClass(String keywords, int page, int size, String active, Sort.Direction direction){
        page--;
        Pageable pageable = PageRequest.of(page,size, direction, "create_at");
        Boolean activeTemp = Boolean.parseBoolean(active);
        return mediaVideoDao.findAllClassVideo(keywords, pageable, activeTemp);
    }

    /**
     * 分页不分类返回所有视频条目
     * @param page
     * @param size
     * @return
     */
    public Page<MediaVideo> findAllExist(String keywords, int page, int size, Sort.Direction direction){
        page--;
        Pageable pageable = PageRequest.of(page, size, direction, "create_at");
        return mediaVideoDao.findAllExistVideo(keywords, pageable);
    }

    /**
     * 通过id找视频
     * @param id
     * @return
     */
    public MediaVideo findOneById(String id){
        return mediaVideoDao.findVideoById(id);
    }

    /**
     * 更新视频
     * @param mediaVideo
     */
    public void update(MediaVideo mediaVideo){
        MediaVideo mediaVideoTemp =this.findOneById(mediaVideo.getId());
        BeanUtils.copyProperties(mediaVideo, mediaVideoTemp);
        mediaVideoTemp.setUpdateAt(new Date());
        mediaVideoTemp.setUpdateBy((String) session.getAttribute("user"));
        mediaVideoDao.saveAndFlush(mediaVideoTemp);
    }

    /**
     * 删除一个视频
     * @param id
     */
    public void deleteOne(String id){
        mediaVideoDao.deleteById(id);
    }
}
