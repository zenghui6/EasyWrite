package com.zenghui.easywrite.service.impl;

import com.zenghui.easywrite.entity.media.MediaVideo;
import com.zenghui.easywrite.dao.MediaVideoDao;
import com.zenghui.easywrite.service.MediaVideoService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public MediaVideo queryById(String id) {
        return this.mediaVideoDao.getOne(id);
    }

    @Override
    public List<MediaVideo> getall() {
        return this.mediaVideoDao.findAll();

    }

    @Override
    public Page<MediaVideo> queryAllByLimit(int offset, int limit) {
        return this.mediaVideoDao.findAll(PageRequest.of((offset - 1)
                * limit, limit));
    }

    @Override
    public MediaVideo insert(MediaVideo mediaVideo) {

        return this.mediaVideoDao.save(mediaVideo);
    }


    @Override
    public MediaVideo update(MediaVideo mediaVideo) {

        return this.mediaVideoDao.save(mediaVideo);
    }


    @Override
    public boolean deleteById(String id) {

        try {
            this.mediaVideoDao.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;

    }
}
