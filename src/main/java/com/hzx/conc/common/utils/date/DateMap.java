package com.hzx.conc.common.utils.date;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *
 * @author zhaozhiqiang
 * Created on 2020/9/9 14:30
 */
@Data
public class DateMap {

    @JSONField(format = "yyyy-MM-dd-HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd-HH:mm:ss", timezone="GMT+8")
    private Date startDate;

    @JSONField(format = "yyyy-MM-dd-HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd-HH:mm:ss", timezone="GMT+8")
    private Date endDate;
}
