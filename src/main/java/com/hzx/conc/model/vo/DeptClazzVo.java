package com.hzx.conc.model.vo;

import com.hzx.conc.model.entity.Clazz;
import lombok.Data;

import java.util.List;

/**
 * @author zexiao.huang
 * @date 2025/4/17 14:43
 */
@Data
public class DeptClazzVo {

    private Long deptId;
    private String deptName;
    private List<Clazz> clazzList;

}
