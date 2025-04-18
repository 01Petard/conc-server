package com.hzx.conc.controller;

import com.hzx.conc.common.result.Result;
import com.hzx.conc.common.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件管理
 * @author 15203
 * @since 2025-02-25 15:20:50
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {


    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    public Result<Map<String, String>> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileName") String fileName
    ) {

        try {
            // 将文件转换为字节数组
            byte[] bytes = file.getBytes();
            // 上传文件到OSS，返回文件的访问URL
            String fileUrl = aliOssUtil.upload(bytes, fileName);

            // 返回上传成功的文件 URL
            Map<String, String> response = new HashMap<>();
            response.put("url", fileUrl);
            return Result.success(response);
        } catch (IOException e) {
            return Result.error("文件上传失败");
        }
    }
}
