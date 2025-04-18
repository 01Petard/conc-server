package com.hzx.conc.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
    //视频图片
    private String vimg;
    //上课班级
    private Long classId;
    //课程名称
    private String cname;
    //是否完成检测
    private Integer status;

}

