package com.hzx.conc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hzx.conc.model.entity.Clazz;
import com.hzx.conc.model.entity.Dept;
import com.hzx.conc.model.vo.DeptClazzVo;
import com.hzx.conc.service.ClazzService;
import com.hzx.conc.service.DeptService;
import com.hzx.conc.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ConcApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("Hello World");
    }

    @Resource
    private StudentService studentService;

    @Test
    void testCRUD(){
        studentService.list().forEach(System.out::println);
    }


    @Resource
    private DeptService deptService;

    @Resource
    private ClazzService clazzService;
    @Test
    void testDeptclazz(){
        List<Dept> deptList = deptService.list();
        List<DeptClazzVo> vo = new ArrayList<>();
        for (Dept dept : deptList) {
            DeptClazzVo deptClazzVo = new DeptClazzVo();
            deptClazzVo.setDeptId(dept.getDeptId());
            deptClazzVo.setDeptName(dept.getDeptName());
            List<Clazz> clazzList = clazzService.list(new LambdaQueryWrapper<Clazz>()
                    .eq(Clazz::getDeptId, dept.getDeptId()));
            deptClazzVo.setClazzList(clazzList);
            vo.add(deptClazzVo);
        }
        System.out.println(vo);
    }

    @Test
    void testDeptclazz2(){
        Long deptId = 35L;
        List<Clazz> clazzList = clazzService.list(new LambdaQueryWrapper<Clazz>()
                .eq(Clazz::getDeptId, deptId));
        clazzList.forEach(
                System.out::println
        );
    }

}
