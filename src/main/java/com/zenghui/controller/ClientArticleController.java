package com.zenghui.controller;

import com.zenghui.entity.ClientArticle;
import com.zenghui.service.ClientArticleService;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

/**
 * (ClientArticle)表控制层
 *
 * @author zenghui
 * @since 2021-02-09 19:47:54
 */
@Api(tags = "官网文章接口")
@RestController
@RequestMapping("client")
public class ClientArticleController {
    /**
     * 服务对象
     */
    @Resource
    private ClientArticleService clientArticleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过主键查找一条")
    @GetMapping("article/{id}")
    public ClientArticle selectOne(@PathVariable("id") String id) {
        return this.clientArticleService.queryById(id);
    }

}
