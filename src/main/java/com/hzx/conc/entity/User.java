package com.hzx.conc.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (User)表实体类
 *
 * @author hzx
 * @since 2025-02-20 14:46:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("user")
public class User extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long uid;

    private String username;

    private String password;

}

