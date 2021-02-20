package com.zenghui.controller;

import com.zenghui.entity.ClientVideo;
import com.zenghui.service.ClientVideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 这张表是官网放视频的 视频数据表(ClientVideo)表控制层
 *
 * @author zenghui
 * @since 2021-02-09 14:19:24
 */
@RestController
@RequestMapping("clientVideo")
public class ClientVideoController {
    /**
     * 服务对象
     */
    @Resource
    private ClientVideoService clientVideoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ClientVideo selectOne(String id) {
        return this.clientVideoService.queryById(id);
    }

}
