package com.hzx.conc.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hzx.conc.model.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author hzx
 * @since 2025-04-17 14:52:14
 */
@Data
public class ClazzVo extends BaseEntity {
    private Long clazzId;
    private String clazzName;
    private String deptId;
}

