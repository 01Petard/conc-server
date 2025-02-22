package com.hzx.conc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hzx.conc.entity.Student;

import java.util.List;
import java.util.Map;

/**
 * (Student)表服务接口
 *
 * @author hzx
 * @since 2025-02-20 14:46:42
 */
public interface StudentService extends IService<Student> {

    Map<Long, List<Student>> listClazz();

    List<Student> listByclazzId(Long clazzId, Integer pageIndex, Integer pageSize);
}

