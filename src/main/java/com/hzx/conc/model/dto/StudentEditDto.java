package com.hzx.conc.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author zexiao.huang
 * @date 2025/4/17 10:32
 */
@Data
public class StudentEditDto {

    public String sno;
    public String sname;
    public String ssex;
    public String sdept;
    public String className;
    public String sphone;

}
