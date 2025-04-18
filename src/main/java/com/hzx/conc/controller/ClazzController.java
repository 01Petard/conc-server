package com.hzx.conc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hzx.conc.common.result.Result;
import com.hzx.conc.model.dto.ClazzAddDto;
import com.hzx.conc.model.entity.Clazz;
import com.hzx.conc.model.entity.Dept;
import com.hzx.conc.model.vo.ClazzVo;
import com.hzx.conc.service.ClazzService;
import com.hzx.conc.service.DeptService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 班级管理
 * @author hzx
 * @create 2024-04-17
 */
@CrossOrigin
@RestController
@RequestMapping("/clazz")
@Slf4j
public class ClazzController {
    @Resource
    private ClazzService clazzService;
    @Resource
    private DeptService deptService;

    @ApiOperation("新增班级")
    @PostMapping("/add")
    public Result<String> addClazz(@RequestBody ClazzAddDto dto) {
        Clazz clazz = new Clazz();
        clazz.setClazzName(dto.getClazzName());
        clazz.setDeptId(dto.getDeptId());
        clazzService.save(clazz);
        return Result.success();
    }

    @ApiOperation("删除班级")
    @DeleteMapping("/delete/{id}")
    public Result<String> deleteClazz(@PathVariable Long id) {
        boolean remove = clazzService.removeById(id);
        return Result.success();
    }

    @ApiOperation("更新班级信息")
    @PutMapping("/update")
    public Result<String> updateClazz(@RequestBody Clazz clazz) {
        boolean update = clazzService.updateById(clazz);
        return Result.success();
    }

    @ApiOperation("根据ID查询班级")
    @GetMapping("/get/{id}")
    public Result<Clazz> getClazzById(@PathVariable Long id) {
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    @ApiOperation("查询所有班级")
    @GetMapping("/list")
    public Result<List<Clazz>> listClazz() {
        QueryWrapper<Clazz> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted", 0);
        List<Clazz> list = clazzService.list(wrapper);
        return Result.success(list);
    }

    @ApiOperation("根据学院名称查询所有班级")
    @GetMapping("/listByDeptId")
    public Result<List<Clazz>> listDeptClazzs(@RequestParam Long deptId) {
        if (deptId == null) {
            return Result.error("学院名称不能为空");
        }

        List<Clazz> clazzList = clazzService.list(new LambdaQueryWrapper<Clazz>()
                .eq(Clazz::getDeptId, deptId));

        return Result.success(clazzList);
    }

}