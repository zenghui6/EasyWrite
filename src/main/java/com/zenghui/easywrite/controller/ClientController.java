package com.zenghui.easywrite.controller;

import cn.hutool.db.PageResult;
import com.zenghui.easywrite.common.api.ApiResult;
import com.zenghui.easywrite.dto.SearchDto;
import com.zenghui.easywrite.entity.client.ClientArticle;
import com.zenghui.easywrite.service.ClientArticleService;
import com.zenghui.easywrite.service.ClientSwiperService;
import com.zenghui.easywrite.service.ClientVideoService;
import com.zenghui.easywrite.vo.web.ArticleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HUT-zenghui
 */
@Api("用户专用接口，无需登陆，对视频、文章的查看")
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

    @ApiOperation("查找挂上首页的文章")
    @PostMapping("/article/find_some")
    public ApiResult<PageResult<ArticleVo>> articleFindSome(@RequestBody SearchDto searchDto) {

        return null;
    }
}