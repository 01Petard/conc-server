package com.hzx.conc.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private Long userId;

    private String userName;

    private String password;

}

