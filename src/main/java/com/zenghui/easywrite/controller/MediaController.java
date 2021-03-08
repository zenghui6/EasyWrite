package com.zenghui.easywrite.controller;

import com.zenghui.easywrite.common.api.ApiResult;
import com.zenghui.easywrite.dto.SearchDto;
import com.zenghui.easywrite.entity.client.ClientVideo;
import com.zenghui.easywrite.entity.media.MediaVideo;
import com.zenghui.easywrite.service.MediaVideoService;
import com.zenghui.easywrite.vo.web.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 功能描述：
 *
 * @author HUT-zenghui
 * @date 2020-03-13 10:24
 */

@Api("员工管理接口，对视频、文章进行全平台分发")
@Slf4j
@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaVideoService videoService;

    @Autowired
    private HttpSession session;

    @ApiOperation("视频的添加")
    @PostMapping("/video/add")
    public ApiResult<String> videoAdd(@RequestBody MediaVideo video ,@RequestHeader(value = "userName",defaultValue = "曾辉") String username){
        String id = "";
        try{
            video.setCreateBy(username);
            video.setUpdateBy(username);
            id = videoService.add(video);
        }catch (Exception e){
            return ApiResult.failed();
        }
        return ApiResult.success(id);
    }

    @ApiOperation("删除单个视频")
    @DeleteMapping("/video/delete_one/{id}")
    public ApiResult<String> videoDeleteOne(@PathVariable("id") String id){
        try{
            videoService.deleteOne(id);
        }catch (Exception e){
            ApiResult.failed();
        }
        return ApiResult.success();
    }

    @ApiOperation("视频的更新")
    @PutMapping("/video/update")
    public ApiResult<String> videoUpdate(@RequestBody MediaVideo mediaVideo){
        try{
            videoService.update(mediaVideo);
        }catch (Exception e){
            return ApiResult.failed();
        }
        return ApiResult.success();
    }

    @ApiOperation("分页查找所有模糊查询的视频")
    @PostMapping("/video/find_all_by_keywords")
    public ApiResult<PageResult<MediaVideo>> videoFindAllByKeywords(@RequestBody SearchDto searchDto) {
        Page<MediaVideo> page;
        PageResult<MediaVideo> pageResult = new PageResult<>();
        try {
            //这里不同于给客户端的，给员工的字段更多
            if (searchDto.getKeywords() == null || "".equals(searchDto.getKeywords())) {
                if (searchDto.getStatus() == null || "".equals(searchDto.getStatus())){
                    // 当关键字,status为空时，查询所有
                    page = videoService.findAllClass("", searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
                }else {
                    page = videoService.staffFindAllByKeywordsAndStatus("", searchDto.getStatus(),searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());

                }
            } else {
                if (searchDto.getStatus() == null || "".equals(searchDto.getStatus())){
                    // 当关键字,status为空时，查询所有
                    page = videoService.findAllClass(searchDto.getKeywords(), searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
                }else {
                    page = videoService.staffFindAllByKeywordsAndStatus(searchDto.getKeywords(), searchDto.getStatus(),searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());

                }
            }
            pageResult.setSize(searchDto.getSize()).setPage(searchDto.getPage()).setData(page.getContent()).setTotal(page.getTotalElements());
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(pageResult);
    }

    @ApiOperation("获取某一个视频")
    @GetMapping("/video/find_one_by_id/{id}")
    public ApiResult<MediaVideo> findVideoById(@PathVariable("id") String id){
        MediaVideo video;
        try {
            video = videoService.findOneById(id);
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(video);
    }


}