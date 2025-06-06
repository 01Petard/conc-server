package com.hzx.conc.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (Student)表实体类
 *
 * @author hzx
 * @since 2025-02-22 16:33:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("conc")
public class Conc extends BaseEntity {
    //专注度id
    @TableId(type = IdType.AUTO)
    private Long concId;
    //班级id
    private Long clazzId;
    //课程id
    private Long cno;
    //学生学号
    private String sno;
    //存储地址
    private String path;

}