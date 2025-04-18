package com.hzx.conc.model.entity;

import lombok.Data;

import java.util.List;

@Data
public class ChartData {
    private List<Double> headUpRate;
    private List<Double> headFrontRate;
    private List<Double> concentration;
    private List<double[]> scatterData;
    private List<String> xaxisData;
}