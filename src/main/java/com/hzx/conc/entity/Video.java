package com.hzx.conc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (Video)表实体类
 *
 * @author hzx
 * @since 2025-02-20 14:46:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("video")
public class Video extends BaseEntity {
    //视频id
    @TableId(type = IdType.AUTO)
    private Long vid;
    //视频名称
    private String vname;
    //上课班级
    private Integer classId;
    //课程名称
    private String cname;

}

