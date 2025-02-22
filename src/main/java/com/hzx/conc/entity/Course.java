package com.hzx.conc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (Course)表实体类
 *
 * @author hzx
 * @since 2025-02-20 14:46:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("course")
public class Course extends BaseEntity {
    //课程编号
    @TableId(type = IdType.AUTO)
    private Long cno;
    //课程名称
    private String cname;
    //先修课课号
    private String cpno;
    //课程学分
    private Integer ccredit;

}

