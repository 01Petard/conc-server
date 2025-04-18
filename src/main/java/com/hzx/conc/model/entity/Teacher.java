package com.hzx.conc.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * (Teacher)表实体类
 *
 * @author hzx
 * @since 2025-02-20 14:46:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("teacher")
public class Teacher extends BaseEntity {
    //教师编号
    @TableId(type = IdType.AUTO)
    private Long tno;
    //教师名称
    private String tname;

    //教师性别
    private String tsex;

    //教师职称
    private String ttitle;

    //教师出生日期
    private Date tbirthday;

    //教师所属部门
    private String tdept;


}

