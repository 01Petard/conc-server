package com.hzx.conc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.relational.core.mapping.Table;

/**
 * (Class)表实体类
 *
 * @author hzx
 * @since 2025-02-20 14:46:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table("clazz")
public class Clazz extends BaseEntity {
    //班级ID
    @TableId(type = IdType.AUTO)
    private Long clazzId;
    //班级名称
    private String clazzName;

}

