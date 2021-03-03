package com.zenghui.easywrite.controller;

import com.zenghui.easywrite.common.api.ApiResult;
import com.zenghui.easywrite.dto.AdminSearchDto;
import com.zenghui.easywrite.dto.RegisterDTO;
import com.zenghui.easywrite.entity.client.ClientArticle;
import com.zenghui.easywrite.entity.client.ClientSwiper;
import com.zenghui.easywrite.entity.client.ClientVideo;
import com.zenghui.easywrite.entity.media.MediaVideo;
import com.zenghui.easywrite.entity.staff.ComStaff;
import com.zenghui.easywrite.service.*;
import com.zenghui.easywrite.utils.SnowflakeIdWorker;
import com.zenghui.easywrite.vo.web.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Noah
 */
@Api("管理员的接口，该接口管理员工的增删查改")
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ComStaffService accountService;

    @Autowired
    private ClientArticleService articleService;

    @Resource
    private ComStaffService comStaffService;

    @Autowired
    private ClientVideoService videoService;

    @Autowired
    private ClientSwiperService swiperService;

    @Autowired
    private MediaVideoService mediaVideoService;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;


    //    -----------以下为员工信息的增删改查----------

    @ApiOperation(value = "分页查询所有被删除的员工")
    @GetMapping("/fina_all_deleted/{page}/{size}")
    public ApiResult<PageResult<ComStaff>> findAllDeleted(@PathVariable("page") int page, @PathVariable("size") int size) {
        PageResult<ComStaff> pageResult;
        try {
            Page<ComStaff> accountPage = accountService.findAllDeleted(page, size);
            // 页数减1已经在service层减了
            pageResult = new PageResult<>();
            pageResult.setData(accountPage.getContent());
            pageResult.setPage(page).setSize(size).setTotal(accountPage.getTotalElements());
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(pageResult);
    }

    @ApiOperation(value = "分页查询所有未被删除的员工")
    @GetMapping("/fina_all_exist/{page}/{size}")
    public ApiResult<PageResult<ComStaff>> findAllExist(@PathVariable("page") int page, @PathVariable("size") int size) {
        PageResult<ComStaff> pageResult;
        try {
            Page<ComStaff> accountPage = accountService.findAllExist(page, size);
            // 页数减1已经在service层减了
            pageResult = new PageResult<>();
            pageResult.setData(accountPage.getContent());
            pageResult.setPage(page).setSize(size).setTotal(accountPage.getTotalElements());
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(pageResult);
    }

    @ApiOperation("分页查找所有模糊查询的员工，接收按时间正逆排序、是否下架")
    @PostMapping("/find_all_by_keywords")
    public ApiResult<PageResult<ComStaff>> AccountFindAllByKeywordsAndLevel(@RequestBody AdminSearchDto searchDto) {
        Page<ComStaff> page;
        PageResult<ComStaff> pageResult;
        try {
            if (searchDto.getKeywords() == null || "".equals(searchDto.getKeywords())) {
                if (searchDto.getLevel() == null || "".equals(searchDto.getLevel())) {
                    page = accountService.findAllByKeywords("", searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
                    // 当关键字为空时，查询所有
                }else {
                    page = accountService.findAllByKeywordsAndLevel("",searchDto.getLevel(), searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
                }
            } else {
                if (searchDto.getLevel() == null || "".equals(searchDto.getLevel())) {
                    page = accountService.findAllByKeywords(searchDto.getKeywords(), searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
                }else {
                    page = accountService.findAllByKeywordsAndLevel(searchDto.getKeywords(),searchDto.getLevel(), searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
                }
            }
            pageResult = new PageResult<>();
            pageResult.setSize(searchDto.getSize()).setPage(searchDto.getPage()).setData(page.getContent()).setTotal(page.getTotalElements());
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(pageResult);
    }

    @ApiOperation(value = "通过username找员工信息")
    @GetMapping("/find_one_by_nickname/{username}")
    public ApiResult<ComStaff> findOneByNickname(@PathVariable("username") String username) {
        ComStaff account;
        try {
             account = accountService.getUserByUsername(username);
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(account);
    }

    /**
     * 管理员添加 staff 员工的接口,管理员账户必须要提权
     * @param dto
     * @return
     */
    @ApiOperation(value = "注册普通员工")
    @PostMapping("/add")
    public ApiResult<Map<String, Object>> register(@Valid @RequestBody RegisterDTO dto) {
        System.out.println(dto);
        ComStaff user = comStaffService.executeRegister(dto);
        if (ObjectUtils.isEmpty(user)) {
            return ApiResult.failed("账号注册失败");
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("user", user);
        return ApiResult.success(map);
    }

    /**
     * 批量下架
     * @param ids
     * @return
     */
    @ApiOperation(value = "批量软删除员工")
    @GetMapping("/modify/{ids}/{active}")
    public ApiResult<String> delete(@PathVariable("ids") String ids, @PathVariable("active") String active) {
        try {
            accountService.modify(ids,active);
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success();
    }

    //    -----------以下为文章审核的相关方法----------

    @ApiOperation("分页按条件分类查找删除与否的文章")
    @PostMapping("/article/find_all_class")
    public ApiResult<PageResult<ClientArticle>> articleFindAllClass(@RequestBody AdminSearchDto searchDto)
    {
        Page<ClientArticle> pageTemp;
        PageResult<ClientArticle> pageResult;
        try {
            if(searchDto.getKeywords() == null || "".equals(searchDto.getKeywords())){
                pageTemp = articleService.findAllClass("", searchDto.getPage(), searchDto.getSize(), searchDto.getActive(), searchDto.getDirection());
            }
            else{
                pageTemp = articleService.findAllClass(searchDto.getKeywords(), searchDto.getPage(), searchDto.getSize(), searchDto.getActive(), searchDto.getDirection());
            }
            pageResult = new PageResult<>();
            pageResult.setTotal(pageTemp.getTotalElements()).setData(pageTemp.getContent()).setPage(searchDto.getPage()).setSize(searchDto.getSize());
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(pageResult);
    }

    @ApiOperation("分页查找所有的文章")
    @PostMapping("/article/find_all")
    public ApiResult<PageResult<ClientArticle>> articleFindAllExist(@RequestBody AdminSearchDto searchDto)
    {
        Page<ClientArticle> pageTemp;
        PageResult<ClientArticle> pageResult;
        try {
            if(searchDto.getKeywords() == null || "".equals(searchDto.getKeywords())){
                pageTemp = articleService.findAllExist("", searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
            }
            else{
                pageTemp = articleService.findAllExist(searchDto.getKeywords(), searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
            }
            pageResult = new PageResult<>();
            pageResult.setTotal(pageTemp.getTotalElements()).setData(pageTemp.getContent()).setPage(searchDto.getPage()).setSize(searchDto.getSize());
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(pageResult);
    }

    //    -----------以下为视频审核的相关方法----------

    @ApiOperation("分页分类查找所有视频")
    @PostMapping("/video/find_all_class")
    public ApiResult<PageResult<ClientVideo>> videoFindAllClass(@RequestBody AdminSearchDto searchDto){
        Page<ClientVideo> pageTemp;
        PageResult<ClientVideo> pageResult;
        try {
            if (searchDto.getKeywords() == null || "".equals(searchDto.getKeywords())) {
                pageTemp = videoService.findAllClass("", searchDto.getPage(), searchDto.getSize(), searchDto.getActive(), searchDto.getDirection());
            }
            else{
                pageTemp = videoService.findAllClass(searchDto.getKeywords(), searchDto.getPage(), searchDto.getSize(), searchDto.getActive(), searchDto.getDirection());
            }
            pageResult = new PageResult<>();
            pageResult.setTotal(pageTemp.getTotalElements()).setData(pageTemp.getContent()).setPage(searchDto.getPage()).setSize(searchDto.getSize());
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(pageResult);
    }

    @ApiOperation("分页查找所有未被删除的视频")
    @PostMapping("/video/find_all")
    public ApiResult<PageResult<ClientVideo>> videoFindAllExist(@RequestBody AdminSearchDto searchDto){
        Page<ClientVideo> pageTemp;
        PageResult<ClientVideo> pageResult;
        try {
            if (searchDto.getKeywords() == null || "".equals(searchDto.getKeywords())) {
                pageTemp = videoService.findAllExist("", searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
            }
            else{
                pageTemp = videoService.findAllExist(searchDto.getKeywords(), searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
            }
            pageResult = new PageResult<>();
            pageResult.setTotal(pageTemp.getTotalElements()).setData(pageTemp.getContent()).setPage(searchDto.getPage()).setSize(searchDto.getSize());
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(pageResult);
    }

    //    -----------以下为轮播图审核的相关方法----------

    @ApiOperation("分页分类查找所有轮播图")
    @PostMapping("/swiper/find_all_class")
    public ApiResult<PageResult<ClientSwiper>> swiperFindAllClass(@RequestBody AdminSearchDto searchDto){
        Page<ClientSwiper> pageTemp;
        PageResult<ClientSwiper> pageResult;
        try {
            if (searchDto.getKeywords() == null || "".equals(searchDto.getKeywords())) {
                pageTemp = swiperService.findAllClass("", searchDto.getPage(), searchDto.getSize(), searchDto.getActive(), searchDto.getDirection());
            }
            else{
                pageTemp = swiperService.findAllClass(searchDto.getKeywords(), searchDto.getPage(), searchDto.getSize(), searchDto.getActive(), searchDto.getDirection());
            }
            pageResult = new PageResult<>();
            pageResult.setTotal(pageTemp.getTotalElements()).setData(pageTemp.getContent()).setPage(searchDto.getPage()).setSize(searchDto.getSize());
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(pageResult);
    }

    @ApiOperation("分页查找所有未被删除的视频")
    @PostMapping("/swiper/find_all")
    public ApiResult<PageResult<ClientSwiper>> swiperFindAllExist(@RequestBody AdminSearchDto searchDto){
        Page<ClientSwiper> pageTemp;
        PageResult<ClientSwiper> pageResult;
        try {
            if (searchDto.getKeywords() == null || "".equals(searchDto.getKeywords())) {
                pageTemp = swiperService.findAllExist("", searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
            }
            else{
                pageTemp = swiperService.findAllExist(searchDto.getKeywords(), searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
            }
            pageResult = new PageResult<>();
            pageResult.setTotal(pageTemp.getTotalElements()).setData(pageTemp.getContent()).setPage(searchDto.getPage()).setSize(searchDto.getSize());
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(pageResult);
    }

    //    -----------以下为视频分发审核的相关方法----------

    @ApiOperation("分页分类查找所有视频")
    @PostMapping("/media_video/find_all_class")
    public ApiResult<PageResult<MediaVideo>> mediaVideoFindAllClass(@RequestBody AdminSearchDto searchDto){
        Page<MediaVideo> pageTemp;
        PageResult<MediaVideo> pageResult;
        try {
            if (searchDto.getKeywords() == null || "".equals(searchDto.getKeywords())) {
                pageTemp = mediaVideoService.findAllClass("", searchDto.getPage(), searchDto.getSize(), searchDto.getActive(), searchDto.getDirection());
            }
            else{
                pageTemp = mediaVideoService.findAllClass(searchDto.getKeywords(), searchDto.getPage(), searchDto.getSize(), searchDto.getActive(), searchDto.getDirection());
            }
            pageResult = new PageResult<>();
            pageResult.setTotal(pageTemp.getTotalElements()).setData(pageTemp.getContent()).setPage(searchDto.getPage()).setSize(searchDto.getSize());

        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(pageResult);
    }

    @ApiOperation("分页查找所有未被删除的视频")
    @PostMapping("/media_video/find_all")
    public ApiResult<PageResult<MediaVideo>> mediaVideoFindAllExist(@RequestBody AdminSearchDto searchDto){
        Page<MediaVideo> pageTemp;
        PageResult<MediaVideo> pageResult;
        try {
            if (searchDto.getKeywords() == null || "".equals(searchDto.getKeywords())) {
                pageTemp = mediaVideoService.findAllExist("", searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
            }
            else{
                pageTemp = mediaVideoService.findAllExist(searchDto.getKeywords(), searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
            }
            pageResult = new PageResult<>();
            pageResult.setTotal(pageTemp.getTotalElements()).setData(pageTemp.getContent()).setPage(searchDto.getPage()).setSize(searchDto.getSize());
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(pageResult);
    }


}