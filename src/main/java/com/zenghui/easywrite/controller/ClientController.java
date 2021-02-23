package com.zenghui.easywrite.controller;

import com.zenghui.easywrite.common.api.ApiResult;
import com.zenghui.easywrite.dto.PageResult;
import com.zenghui.easywrite.dto.SearchDto;
import com.zenghui.easywrite.entity.client.ClientArticle;
import com.zenghui.easywrite.entity.client.ClientSwiper;
import com.zenghui.easywrite.entity.client.ClientVideo;
import com.zenghui.easywrite.service.ClientArticleService;
import com.zenghui.easywrite.service.ClientSwiperService;
import com.zenghui.easywrite.service.ClientVideoService;
import com.zenghui.easywrite.vo.web.ArticleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HUT-zenghui
 */
@Api(tags = "用户专用接口，无需登陆，对视频、文章的查看")
@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientArticleService articleService;

    @Autowired

    private ClientVideoService videoService;

    @Autowired
    private ClientSwiperService swiperService;

    /**
     * 根据关键字，页码，size，来搜索，已经审阅了的文章
     * @param searchDto
     * @return
     */
    @ApiOperation("分页查找所有模糊查询且未被软删除的文章")
    @PostMapping("/article/find_some")
    public ApiResult<PageResult<ArticleVo>> articleFindSome(@RequestBody SearchDto searchDto) {
        PageResult<ArticleVo> pageResult = new PageResult<>();
        Page<ArticleVo> page;
        try {
            if (searchDto.getKeywords() == null || "".equals(searchDto.getKeywords())) {
                page = articleService.findSome("", searchDto.getPage(), searchDto.getSize());
                // 当关键字为空时，查询所有
            } else {
                page = articleService.findSome(searchDto.getKeywords(), searchDto.getPage(), searchDto.getSize());
            }
            pageResult.setSize(searchDto.getSize()).setPage(searchDto.getPage()).setData(page.getContent()).setTotal(page.getTotalElements());
        } catch (Exception e){
            return ApiResult.failed();
        }
        return ApiResult.success(pageResult);
    }

    @ApiOperation("找某一篇文章")
    @GetMapping("/article/find_one_by_id/{id}")
    public ApiResult<ClientArticle> articleFindOneById(@PathVariable("id") String id) {
        ClientArticle data;
        try {
            data = articleService.findOneById(id);
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(data);
    }

    @ApiOperation("分页查找所有模糊查询且未被软删除的视频")
    @PostMapping("/video/find_all_by_keywords")
    public ApiResult<PageResult<ClientVideo>> videoFindAllByKeywords(@RequestBody SearchDto searchDto) {
        PageResult<ClientVideo> pageResult = new PageResult<>();
        Page<ClientVideo> page;
        try {
            if (searchDto.getKeywords() == null || "".equals(searchDto.getKeywords())) {
                page = videoService.findAllByKeywords("", searchDto.getPage(), searchDto.getSize());
                // 当关键字为空时，查询所有
            } else {
                page = videoService.findAllByKeywords(searchDto.getKeywords(), searchDto.getPage(), searchDto.getSize());
            }
            pageResult.setSize(searchDto.getSize()).setPage(searchDto.getPage()).setData(page.getContent()).setTotal(page.getTotalElements());
        } catch (Exception e){
            return ApiResult.failed();
        }
        return ApiResult.success(pageResult);
    }

    @ApiOperation("找某一个视频")
    @GetMapping("/video/find_one_by_id/{id}")
    public ApiResult<ClientVideo> videoFindOneById(@PathVariable("id") String id) {
        ClientVideo data;
        try {
            data = videoService.findOneById(id);
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(data);
    }

    @ApiOperation("找某一个轮播图")
    @GetMapping("/swiper/find_one_by_id/{id}")
    public ApiResult<ClientSwiper> swiperFindOneById(@PathVariable("id") String id) {
        ClientSwiper data;
        try {
            data = swiperService.findOneById(id);
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(data);
    }

    @ApiOperation("查找所有未被软删除的轮播图")
    @PostMapping("/swiper/find_all_exist")
    public ApiResult<List<ClientSwiper>> swiperFindAllByKeywords(String a) {
        List<ClientSwiper> data;
        try {
            data = swiperService.clientFindAllExist();
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(data);
    }


}