package com.hzx.conc.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * (Dept)表实体类
 * @author hzx
 * @since 2025-02-20 14:46:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("dept")
public class Dept extends BaseEntity {
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;

    @TableField("dept_name")
    private String deptName;
}
