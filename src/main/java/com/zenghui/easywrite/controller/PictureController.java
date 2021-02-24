package com.zenghui.easywrite.controller;

import com.zenghui.easywrite.common.api.ApiResult;
import com.zenghui.easywrite.utils.SnowflakeIdWorker;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author HUT-zenghui
 */
@Api("专门用来上传图片")
@RequestMapping("/picture")
@RestController
public class PictureController {


    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    @Value("${file.path}")
    private String filePath;
    // 上传的路径

    @ApiOperation("上传图片专用接口")
    @PostMapping("/upload")
    @CrossOrigin
    public ApiResult<String> upload(@RequestParam(value = "file") MultipartFile file){
        // 文件名
        String fileName = file.getOriginalFilename();
        // 后缀名
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 新文件名
        fileName = snowflakeIdWorker.nextId() + suffixName;
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            // 若不存在该文件夹，则创建一个
            boolean e = dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("返回的文件URL：" + fileName);
        return ApiResult.success(fileName);
    }

    @ApiOperation("上传小程序投票对象图片专用接口")
    @PostMapping("/upload_mp")
    @CrossOrigin
    public ApiResult<String> uploadMiniprogramPic(@RequestParam(value = "file") MultipartFile file){

        // 文件名
        String fileName = file.getOriginalFilename();
        // 后缀名
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 新文件名
        fileName = snowflakeIdWorker.nextId() + suffixName;
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            // 若不存在该文件夹，则创建一个
            boolean e = dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File thumbnailDest = new File(filePath+ "thumbnails/mp" + fileName);
        if (!thumbnailDest.getParentFile().exists()) {
            // 若不存在该文件夹，则创建一个
            boolean e = thumbnailDest.getParentFile().mkdirs();
        }
        try {
            Thumbnails.of(dest)
                    .size(160,90)
                    .outputQuality(0.9f)
                    .toFile(thumbnailDest);
        } catch (IOException e) {
            e.printStackTrace();

        }
        System.out.println("返回的文件URL：" + fileName);
        return ApiResult.success(fileName);
    }
}