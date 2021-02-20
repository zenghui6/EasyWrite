package com.zenghui.easywrite.controller;

import com.zenghui.easywrite.entity.staff.ComStaff;
import com.zenghui.easywrite.service.ComStaffService;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

/**
 * 员工信息等级表(ComStaff)表控制层
 *
 * @author zenghui
 * @since 2021-02-20 18:12:18
 */
@Api("员工信息等级表")
@RestController
@RequestMapping("comStaff")
public class ComStaffController {
    /**
     * 服务对象
     */
    @Resource
    private ComStaffService comStaffService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("")
    @GetMapping("comStaff/{id}")
    public ComStaff selectOne(@PathVariable("id") String id) {
        return this.comStaffService.queryById(id);
    }

}
