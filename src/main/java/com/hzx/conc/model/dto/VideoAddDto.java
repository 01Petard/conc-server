package com.hzx.conc.model.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zexiao.huang
 * @date 2025/4/19 14:48
 */
@Data
public class VideoAddDto {
    private String vname;
    private String cname;
    private String vimg;
}
