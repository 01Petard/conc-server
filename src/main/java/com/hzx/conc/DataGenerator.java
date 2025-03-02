package com.hzx.conc;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {
    private static final Random random = new Random();
    private static final DecimalFormat df = new DecimalFormat("0.00");

    // 生成精确到秒的时间轴（40分钟，间隔30秒）
    public static List<String> generateXAxisData() {
        List<String> xAxisData = new ArrayList<>();
        int totalMinutes = 40;
        int intervalSeconds = 30;

        for (int min = 0; min <= totalMinutes; min++) {
            for (int sec = 0; sec < 60; sec += intervalSeconds) {
                // 跳过超过总时长的数据点
                if (min == totalMinutes && sec > 0) break;

                String time = String.format("%02d:%02d", min, sec);
                xAxisData.add(time);
            }
        }
        return xAxisData;
    }

    // 生成指定长度的随机数值列表
    public static List<Double> generateRandomRateData(int size) {
        List<Double> data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            double value = random.nextDouble();
            data.add(Double.parseDouble(df.format(value)));
        }
        return data;
    }

    // 生成与时间轴等长的散点数据
    public static List<double[]> generateScatterData(int size) {
        List<double[]> scatterData = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            double x = random.nextDouble() * 2 - 1;  // [-1,1)
            double y = random.nextDouble() * 2 - 1;
            scatterData.add(new double[]{
                    Double.parseDouble(df.format(x)),
                    Double.parseDouble(df.format(y))
            });
        }
        return scatterData;
    }


    public static void main(String[] args) {
        // 定义输出目录
        String outputDir = "src/main/resources/data";

        try {
            // 创建目录（如果不存在）
            Path dirPath = Paths.get(outputDir);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
                System.out.println("创建目录: " + dirPath.toAbsolutePath());
            }

            // 清理目录中的现有JSON文件
            cleanDirectory(outputDir);

            // 生成新数据
            List<String> xAxisData = generateXAxisData();
            List<Double> headUpRate = generateRandomRateData(xAxisData.size());
            List<Double> headFrontRate = generateRandomRateData(xAxisData.size());
            List<Double> concentration = generateRandomRateData(xAxisData.size());
            List<double[]> scatterData = generateScatterData(xAxisData.size());

            // 写入文件（带目录路径）
            writeJsonToFile(xAxisData, outputDir + "/xAxisData.json");
            writeJsonToFile(headUpRate, outputDir + "/headUpRate.json");
            writeJsonToFile(headFrontRate, outputDir + "/headFrontRate.json");
            writeJsonToFile(concentration, outputDir + "/concentration.json");
            writeJsonToFile(scatterData, outputDir + "/scatterData.json");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 清理目录中的JSON文件
    private static void cleanDirectory(String dirPath) {
        File directory = new File(dirPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) ->
                    name.endsWith(".json"));

            if (files != null) {
                for (File file : files) {
                    if (file.delete()) {
                        System.out.println("已删除旧文件: " + file.getName());
                    } else {
                        System.out.println("无法删除: " + file.getName());
                    }
                }
            }
        }
    }

    // 修改后的写入方法
    public static void writeJsonToFile(Object data, String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(filePath), data);
            System.out.println("写入成功: " + filePath);
        } catch (Exception e) {
            System.err.println("写入失败: " + filePath);
            e.printStackTrace();
        }
    }
}