package com.zenghui.controller;

import com.zenghui.entity.ClientSwiper;
import com.zenghui.service.ClientSwiperService;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

/**
 * 官网轮播图(ClientSwiper)表控制层
 *
 * @author zenghui
 * @since 2021-02-09 19:46:14
 */
@Api("官网轮播图")
@RestController
@RequestMapping("client")
public class ClientSwiperController {
    /**
     * 服务对象
     */
    @Resource
    private ClientSwiperService clientSwiperService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过主键查找一条")
    @GetMapping("swiper/{id}")
    public ClientSwiper selectOne(@PathVariable("id") String id) {
        return this.clientSwiperService.queryById(id);
    }

}
