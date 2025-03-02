package com.hzx.conc.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.List;

public class JsonDataReader {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T readJsonData(String path, Class<T> valueType) {
        try (InputStream inputStream = new ClassPathResource("data/" + path).getInputStream()) {
            return mapper.readValue(inputStream, valueType);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON data: " + path, e);
        }
    }

    // 特定类型快捷方法
    public static List<String> readStringList(String filename) {
        return readJsonData(filename, List.class);
    }

    public static List<Double> readDoubleList(String filename) {
        return readJsonData(filename, List.class);
    }

    // 增加专门处理二维double数组的方法
    public static List<double[]> readScatterData(String filename) {
        try (InputStream inputStream = new ClassPathResource("data/" + filename).getInputStream()) {
            return mapper.readValue(inputStream, new TypeReference<List<double[]>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to read scatter data: " + filename, e);
        }
    }
}