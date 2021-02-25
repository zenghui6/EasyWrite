package com.zenghui.easywrite.controller;

import com.zenghui.easywrite.common.api.ApiResult;
import com.zenghui.easywrite.vo.web.PageResult;
import com.zenghui.easywrite.dto.SearchDto;
import com.zenghui.easywrite.entity.client.ClientArticle;
import com.zenghui.easywrite.entity.client.ClientSwiper;
import com.zenghui.easywrite.entity.client.ClientVideo;
import com.zenghui.easywrite.entity.staff.ComStaff;
import com.zenghui.easywrite.service.ClientArticleService;
import com.zenghui.easywrite.service.ClientSwiperService;
import com.zenghui.easywrite.service.ClientVideoService;
import com.zenghui.easywrite.service.ComStaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author HUT-zenghui
 */
@Api("员工管理接口，对视频、文章进行增删改")
@Slf4j
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private ClientArticleService articleService;

    @Autowired
    private ClientVideoService videoService;

    @Autowired
    private ClientSwiperService swiperService;

    @Autowired
    private ComStaffService accountService;

    @Autowired
    private HttpSession session;

    //    -----------以下为员工信息的增删改查----------


    @ApiOperation("获取当前员工信息")
    @PostMapping("/account/find_one")
    public ApiResult<ComStaff> findOneStaff(@RequestHeader(value = "userName") String username){
        ComStaff account ;
        try {
            account = accountService.getUserByUsername(username);
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(account);
    }

    @ApiOperation(value = "修改员工信息")
    @PostMapping("/account/update")
    public ApiResult<String> updateStaffInfo(@RequestBody ComStaff account) {
        try {
            accountService.updata(account);
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success("更新成功");
    }

    //    -----------以下为文章的增删改查----------

    @ApiOperation("文章的增加")
    @PostMapping("/article/add")
    public ApiResult<String> articleAdd(@RequestBody ClientArticle article){
        try{
            String id = articleService.add(article);
        }catch (Exception e){
           return ApiResult.failed();
        }
        return ApiResult.success("增加成功");
    }

    @ApiOperation("批量下架文章")
    @DeleteMapping("/article/delete/{ids}")
    public ApiResult<String> articleDelete(@PathVariable("ids") String ids){
        try{
           articleService.delete(ids);
        }catch (Exception e){
            return ApiResult.failed();
        }
        return ApiResult.success("批量下架文章成功");
    }

    @ApiOperation("删除单篇文章")
    @DeleteMapping("/article/delete_one/{id}")
    public ApiResult<String> articleDeleteOne(@PathVariable("id") String id){
        try{
            articleService.deleteOne(id);
        }catch (Exception e){
            return ApiResult.failed();
        }
        return ApiResult.success();
    }

    @ApiOperation("更新文章")
    @PutMapping("/article/update")
    public ApiResult<String> articleUpdate(@RequestBody ClientArticle article) {
        try{
            articleService.update(article);
        }catch (Exception e){
            return ApiResult.failed();
        }
        return ApiResult.success();
    }

    @ApiOperation("分页查找所有包含关键字的文章")
    @PostMapping("/article/find_all_by_keywords")
    public ApiResult<PageResult<ClientArticle>> articleFindAllByKeywords(@RequestBody SearchDto searchDto) {
        PageResult<ClientArticle> pageResult = new PageResult<>();
        Page<ClientArticle> page;
        try {
            //这里不同于给客户端的，给员工的字段更多
            if (searchDto.getKeywords() == null || "".equals(searchDto.getKeywords())) {
                page = articleService.staffFindAllByKeywords("", searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
                // 当关键字为空时，查询所有
            } else {
                page = articleService.staffFindAllByKeywords(searchDto.getKeywords(), searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
            }
            pageResult = new PageResult<>();
            pageResult.setSize(searchDto.getSize()).setPage(searchDto.getPage()).setData(page.getContent()).setTotal(page.getTotalElements());
        } catch (Exception e) {
           return ApiResult.failed();
        }
        return ApiResult.success(pageResult);
    }

    @ApiOperation("获取某一篇文章")
    @GetMapping("/article/find_one_by_id/{id}")
    public ApiResult<ClientArticle> findArticleById(@PathVariable("id") String id){
        ClientArticle list;
        try {
            list = articleService.findOneById(id);
        } catch (Exception e) {
           return ApiResult.failed();
        }
        return ApiResult.success(list);
    }

    //    --------以下为视频的增删改查-------

    @ApiOperation("视频的添加")
    @PostMapping("/video/add")
    public ApiResult<String> videoAdd(@RequestBody ClientVideo video){
        try{
            videoService.add(video);
        }catch (Exception e){
            ApiResult.failed();
        }
        return ApiResult.success();
    }

    @ApiOperation("视频的批量下架")
    @DeleteMapping("/video/delete/{ids}")
    public ApiResult<String> videoDelete(@PathVariable("ids") String ids) {
        try{
            videoService.delete(ids);
        }catch (Exception e){
            return ApiResult.failed();
        }
        return ApiResult.success();
    }

    @ApiOperation("删除单个视频")
    @DeleteMapping("/video/delete_one/{id}")
    public ApiResult<String> videoDeleteOne(@PathVariable("id") String id){
        try{
            videoService.deleteOne(id);
        }catch (Exception e){
            return ApiResult.failed();
        }
        return ApiResult.success();
    }

    @ApiOperation("视频的更新")
    @PutMapping("/video/update")
    public ApiResult<String> videoUpdate(@RequestBody ClientVideo video){
        try{
            videoService.update(video);
        }catch (Exception e){
            return ApiResult.failed();
        }
        return ApiResult.success();
    }

    @ApiOperation("分页查找所有模糊查询的视频")
    @PostMapping("/video/find_all_by_keywords")
    public ApiResult<PageResult<ClientVideo>> videoFindAllByKeywords(@RequestBody SearchDto searchDto) {
        Page<ClientVideo> page;
        PageResult<ClientVideo> pageResult;
        try {
            if (searchDto.getKeywords() == null || "".equals(searchDto.getKeywords())) {
                page = videoService.staffFindAllByKeywords("", searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
                // 当关键字为空时，查询所有
            } else {
                page = videoService.staffFindAllByKeywords(searchDto.getKeywords(), searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
            }
            pageResult = new PageResult<>();
            pageResult.setSize(searchDto.getSize()).setPage(searchDto.getPage()).setData(page.getContent()).setTotal(page.getTotalElements());
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(pageResult);
    }

    @ApiOperation("获取某一个视频")
    @GetMapping("/video/find_one_by_id/{id}")
    public ApiResult<ClientVideo> findVideoById(@PathVariable("id") String id){
        try {
            ClientVideo list = videoService.findOneById(id);
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success();
    }

    // ----------以下为网站轮播图的增删改-------

    @ApiOperation("网站轮播图的添加")
    @PostMapping("/web_swiper/add")
    public ApiResult<String> webSwiperAdd(@RequestBody ClientSwiper swiper){
        try{
            swiperService.add(swiper);
        }catch (Exception e){
            return ApiResult.failed();
        }
        return ApiResult.success();
    }

    @ApiOperation("网站轮播图的批量下架")
    @DeleteMapping("/web_swiper/delete/{ids}")
    public ApiResult<String> webSwiperDelete(@PathVariable("ids") String ids) {
        try{
            swiperService.deleteSome(ids);
        }catch (Exception e){
            return ApiResult.failed();
        }
        return ApiResult.success();
    }

    @ApiOperation("网站轮播图的更新")
    @PutMapping("/web_swiper/update")
    public ApiResult<String> webSwiperUpdate(@RequestBody ClientSwiper swiper){
        try{
            swiperService.update(swiper);
        }catch (Exception e){
            System.out.print(e);
            return ApiResult.failed();
        }
        return ApiResult.success();
    }

    @ApiOperation("分页查找所有已被下架的网站轮播图")
    @GetMapping("/web_swiper/find_all_deleted/{page}/{size}")
    public ApiResult<PageResult<ClientSwiper>> webSwiperFindAllDeleted(@PathVariable("page") int page, @PathVariable("size") int size){
        PageResult<ClientSwiper> pageResult;
        Page<ClientSwiper> pageTemp;
        try {
            pageTemp = swiperService.findAllDeleted(page, size);
            pageResult = new PageResult<>();
            pageResult.setTotal(pageTemp.getTotalElements()).setData(pageTemp.getContent()).setPage(page).setSize(size);
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success(pageResult);
    }

    @ApiOperation("分页查找所有未被删除的网站轮播图")
    @GetMapping("/web_swiper/find_all_exist/{page}/{size}")
    public ApiResult<PageResult<ClientSwiper>> webSwiperFindAllExist(@PathVariable("page") int page, @PathVariable("size") int size){
        PageResult<ClientSwiper> pageResult;
        Page<ClientSwiper> pageTemp;
        try {
            pageTemp = swiperService.findAllExist(page, size);
            pageResult = new PageResult<>();
            pageResult.setTotal(pageTemp.getTotalElements()).setData(pageTemp.getContent()).setPage(page).setSize(size);
        } catch (Exception e) {
            return ApiResult.failed();
        }
        return ApiResult.success();
    }

//    @ApiOperation("分页查找所有模糊查询的")
//    @PostMapping("/web_swiper/find_all_by_keywords")
//    public Result<PageResult<Swiper>> webSwiperFindAllByKeywords(@RequestBody SearchDto searchDto) {
//        Result<PageResult<Swiper>> result = new Result<>();
//        Page<Swiper> page;
//        try {
//            if (searchDto.getKeywords() == null || searchDto.getKeywords().equals("")) {
//                page = swiperService.staffFindAllByKeywords("", searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
//                // 当关键字为空时，查询所有
//            } else {
//                page = swiperService.staffFindAllByKeywords(searchDto.getKeywords(), searchDto.getPage(), searchDto.getSize(), searchDto.getDirection());
//            }
//            PageResult<Swiper> pageResult = new PageResult<>();
//            pageResult.setSize(searchDto.getSize()).setPage(searchDto.getPage()).setData(page.getContent()).setTotal(page.getTotalElements());
//            result.setMessage("success").setCode(HttpStatus.OK).setData(pageResult);
//        } catch (Exception e) {
//            result.setMessage("fail").setCode(HttpStatus.OK).setData(null);
//        }
//
//        return result;
//    }

    @ApiOperation("获取某一个轮播图")
    @GetMapping("/web_swiper/find_one_by_id/{id}")
    public ApiResult<ClientSwiper> findSwiperById(@PathVariable("id") String id){
        ClientSwiper swiper = null;
        try {
            swiper= swiperService.findOneById(id);
        } catch (Exception e) {
            ApiResult.failed();
        }
        return ApiResult.success(swiper);
    }

}