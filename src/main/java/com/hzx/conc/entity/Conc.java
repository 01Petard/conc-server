package com.hzx.conc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
    @TableField(fill = FieldFill.INSERT)
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