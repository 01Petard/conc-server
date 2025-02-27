package com.hzx.conc.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.hzx.conc.common.constant.MessageConstant;
import com.hzx.conc.common.result.Result;
import com.hzx.conc.entity.Clazz;
import com.hzx.conc.entity.Student;
import com.hzx.conc.entity.Video;
import com.hzx.conc.service.ClazzService;
import com.hzx.conc.service.VideoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/video")
@Slf4j
public class VideoController {

    @Resource
    private VideoService videoService;

    @Resource
    private ClazzService clazzService;

    private final String PATH_PREFIX = "C:\\Users\\15203\\Downloads\\elementui资料\\video_url";

    @ApiOperation("获取所有学生数据")
    @GetMapping("/list")
    public Result<List<Video>> list() {
        List<Video> videoList = videoService.list();
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




}