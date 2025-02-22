package com.hzx.conc;

import com.hzx.conc.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

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

}
