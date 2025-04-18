package com.hzx.conc.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * (StudentVo)
 *
 * @author hzx
 * @since 2025-02-20 14:46:42
 */
@Data
public class StudentVo  {
    //学生学号
    @TableId(type = IdType.ASSIGN_ID)
    private String sno;
    //学生姓名
    private String sname;

}

