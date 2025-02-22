package com.hzx.conc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (Student)表实体类
 *
 * @author hzx
 * @since 2025-02-20 14:46:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("student")
public class Student extends BaseEntity {
    //学生学号
    @TableId(type = IdType.ASSIGN_ID)
    private String sno;
    //学生姓名
    private String sname;
    //学生性别
    private String ssex;
    //学生所属学院
    private String sdept;
    //班级ID
    private Long clazzId;
    //学生所在班级
    private String clazzName;
    //学生出生日期
    private Date sbday;
    //学生生源地
    private String sbplace;
    //学生身份证号
    private String sidnum;
    //学生手机号
    private String sphone;
    //人脸图片地址
    private String faceimg;

}

