package com.hzx.conc.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hzx.conc.common.constant.MessageConstant;
import com.hzx.conc.common.result.Result;
import com.hzx.conc.model.dto.VideoAddDto;
import com.hzx.conc.model.entity.Clazz;
import com.hzx.conc.model.entity.Dept;
import com.hzx.conc.model.entity.Student;
import com.hzx.conc.model.entity.Video;
import com.hzx.conc.service.ClazzService;
import com.hzx.conc.service.VideoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 视频管理
 * @author 15203
 * @since 2025-02-22 16:44:52
 */
@CrossOrigin
@RestController
@RequestMapping("/video")
@Slf4j
public class VideoController {

    @Resource
    private VideoService videoService;

    @Resource
    private ClazzService clazzService;

    private final String PATH_PREFIX = "src/main/resources/img";

    @ApiOperation("获取所有视频数据")
    @GetMapping("/list")
    public Result<List<Video>> list() {
        List<Video> videoList = videoService.list();

        // 遍历学生列表，将 faceimg 转换为 Base64 编码
        videoList.forEach(video -> {
            if (video.getVimg() != null && !video.getVimg().isEmpty()) {
                Path imagePath = Paths.get(PATH_PREFIX, video.getVimg()); // 获取图片路径
                try {
                    byte[] imageBytes = Files.readAllBytes(imagePath); // 读取图片字节
                    String base64Image = "data:image/png;base64," + Base64Utils.encodeToString(imageBytes); // 转换为 Base64
                    video.setVimg(base64Image); // 设置 Base64 字符串
                } catch (Exception e) {
                    video.setVimg(null); // 如果读取失败，设置为空
                    log.error(e.getMessage());
                }
            }
        });

        return Result.success(videoList);
    }


    @ApiOperation("视频上传")
    @GetMapping("/upload")
    public Result<String> upload(
            @RequestParam("vname") String vname,
            @RequestParam("cname") String cname,
            @RequestParam("fileName") String fileName
    ) {
        Clazz clazz = clazzService.getOne(new LambdaQueryWrapper<Clazz>()
                .eq(Clazz::getClazzName, cname));
        if (ObjectUtils.isEmpty(clazz)) {
            return Result.error(MessageConstant.CLASS_NOT_EXIST);
        }
        Video video = new Video();
        video.setClassId(clazz.getClazzId());
        video.setVname(vname);
        video.setCname(cname);
        videoService.save(video);
        return Result.success();

    }


    @ApiOperation("编辑视频")
    @PutMapping("/update")
    public Result<String> update(
            @RequestParam String vid,
            @RequestParam String vname,
            @RequestParam String cname
    ) {
        Video video = videoService.getOne(new LambdaQueryWrapper<Video>()
                .eq(Video::getVid, vid));
        if (ObjectUtils.isEmpty(video)){
            return Result.error(MessageConstant.VIDEO_NOT_EXIST);
        }

        video.setVname(vname);
        video.setCname(cname);

        videoService.updateById(video);
        return Result.success();
    }


    @ApiOperation("删除视频")
    @DeleteMapping("/delete")
    public Result<String> update(@RequestParam String vid) {
        Video video = videoService.getOne(new LambdaQueryWrapper<Video>()
                .eq(Video::getVid, vid));
        if (ObjectUtils.isEmpty(video)){
            return Result.error(MessageConstant.VIDEO_NOT_EXIST);
        }

        videoService.removeById(vid);
        return Result.success();
    }


    @ApiOperation("新增视频")
    @PostMapping("/add")
    public Result<String> add(
            @RequestParam @NotBlank String vname,
            @RequestParam @NotBlank String cname,
            @RequestParam(required = false) String vimg
    ) {
        Video video = new Video();
        video.setVname(vname);
        video.setCname(cname);
        video.setVimg(vimg);

        videoService.save(video);
        return Result.success();
    }

}