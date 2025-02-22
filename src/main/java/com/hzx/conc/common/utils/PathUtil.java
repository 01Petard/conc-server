package com.hzx.conc.common.utils;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * path工具类
 * @author zhangzhan
 */
public class PathUtil {

    private static final AntPathMatcher MATCHER = new AntPathMatcher();

    public static boolean isPathMatch(String pattern, String path) {
        return MATCHER.match(pattern, path);
    }

    /**
     * url匹配
     * ‘*’一个路径匹配
     * ‘**’ 多个路径匹配
     * @param pattern
     * @param path
     * @return
     */
    public static boolean isPathMatch(List<String> pattern, String path) {
        if (CollectionUtils.isEmpty(pattern)) {
            return false;
        }
        for (String p : pattern) {
            if (MATCHER.match(p, path)) {
                return true;
            }
        }
        return false;
    }
}
