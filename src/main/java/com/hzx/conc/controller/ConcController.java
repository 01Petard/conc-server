package com.hzx.conc.controller;

import com.hzx.conc.common.result.Result;
import com.hzx.conc.common.utils.JsonDataReader;
import com.hzx.conc.model.entity.ChartData;
import com.hzx.conc.model.entity.Conc;
import com.hzx.conc.service.ConcService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 专注度分析管理
 * @author 15203
 * @since 2025-02-22 16:44:52
 */
@CrossOrigin
@RestController
@RequestMapping("/conc")
@Slf4j
public class ConcController {

    @Resource
    private ConcService concService;

    @ApiOperation("查询学生的专注度")
    @GetMapping("/query")
    public Result<String> query(
            @RequestParam("sno") Long sno
    ) {

        Conc conc = concService.getById(sno);
        String path = conc.getPath();
        if (path == null) {
            return Result.error("没有该学生的专注度");
        }
        // 调用获取专注度接口
        return Result.success("pong");
    }

    @GetMapping("/chart-data")
    public ChartData getChartData() {
        ChartData cachedData = new ChartData();
        cachedData.setXaxisData(JsonDataReader.readStringList("xAxisData.json"));
        cachedData.setHeadUpRate(JsonDataReader.readDoubleList("headUpRate.json"));
        cachedData.setHeadFrontRate(JsonDataReader.readDoubleList("headFrontRate.json"));
        cachedData.setConcentration(JsonDataReader.readDoubleList("concentration.json"));
        cachedData.setScatterData(JsonDataReader.readScatterData("scatterData.json"));
        return cachedData;
    }



}
