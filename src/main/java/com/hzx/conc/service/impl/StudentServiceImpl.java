package com.hzx.conc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hzx.conc.common.exception.BadRequestException;
import com.hzx.conc.common.utils.Collections3;
import com.hzx.conc.entity.Clazz;
import com.hzx.conc.entity.Student;
import com.hzx.conc.mapper.ClazzMapper;
import com.hzx.conc.mapper.StudentMapper;
import com.hzx.conc.service.StudentService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (Student)表服务实现类
 *
 * @author hzx
 * @since 2025-02-20 14:46:42
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private ClazzMapper clazzMapper;


    @Override
    public Map<Long, List<Student>> listClazz(Integer pageIndex, Integer pageSize) {

        Page<Student> page = new Page<>(pageIndex, pageSize);
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();

        Page<Student> result = studentMapper.selectPage(page, queryWrapper);
        List<Student> studentList = result.getRecords();
        // 每班的学生集合
        Map<Long, List<Student>> studentMap = Collections3.groupingBy(
                studentList,
                Student::getClazzId
        );
        return studentMap;
    }

    @Override
    public List<Student> listByclazzId(Long clazzId, Integer pageIndex, Integer pageSize) {

        Clazz clazz = clazzMapper.selectOne(new LambdaQueryWrapper<Clazz>().eq(Clazz::getClazzId, clazzId));
        if (ObjectUtils.isEmpty(clazz)){
            throw new BadRequestException("班级id不存在");
        }

        Page<Student> page = new Page<>(pageIndex, pageSize);
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<>();

        // 如果 班级id 为空，就查询所有学生
        if (clazzId != null) {
            queryWrapper.eq(Student::getClazzId, clazzId);
        }

        Page<Student> result = studentMapper.selectPage(page, queryWrapper);
        return result.getRecords();
    }
}

