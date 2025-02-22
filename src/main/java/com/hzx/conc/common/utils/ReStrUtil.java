package com.hzx.conc.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 敏感信息加*模糊化
 */
public class ReStrUtil {

    /**
     * 中文正则
     */
    final static Pattern chineseCompile = Pattern.compile("[\\u4e00-\\u9fa5]");

    /**
     * 脱敏处理
     *
     * @param object    某个类
     * @param fieldName 某个类的成员变量字段名
     * @param value     给成员变量设置的属性值
     */
    public static void dataDesensitization(Object object, String fieldName, Object value) {
        if (object != null && value != null) {
            try {
                // 获取obj类的字节文件对象
                Class<?> clazz = object.getClass();
                // 获取该类的成员变量
                Field field = clazz.getDeclaredField(fieldName);
                // 取消语言访问检查
                field.setAccessible(true);
                // 给成员变量赋值
                field.set(object, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 身份证号加*号
     */
    public static String reNo(String realNo) {
        if (realNo == null || "".equals(realNo)) {
            return realNo;
        } else if ("null".equals(realNo)) {
            return null;
        }

        String newNo = "";

        if (realNo.length() == 15) {
            newNo = realNo.replaceAll("(\\d{4})\\d{7}(\\d{4})", "$1*******$2");
        } else if (realNo.contains("X") || realNo.contains("x")) {
            newNo = StringUtils.overlay(realNo, "*******", 4, 14);
        } else if (realNo.length() == 18) {
            newNo = realNo.replaceAll("(\\d{4})\\d{10}(\\d{4})", "$1**********$2");
        } else if (realNo.length() < 5) {
            newNo =  StringUtils.overlay(realNo, "**", 2, 4);
        } else {
            newNo = StringUtils.overlay(realNo, "*******", 4, 11);
        }

        return newNo;

    }

    /**
     * 手机号加*号
     */
    public static String rePhone(String realPhone) {
        if (realPhone == null || "".equals(realPhone)) {
            return realPhone;
        } else if ("null".equals(realPhone)) {
            return null;
        }
        String phoneNumber = "";

        if (realPhone.length() == 11) phoneNumber = realPhone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");

        else if (realPhone.length() < 5) phoneNumber =  StringUtils.overlay(realPhone, "**", 2, 4);

        else phoneNumber =  StringUtils.overlay(realPhone, "****", 3, 8);

        return phoneNumber;
    }

    /**
     * 名字加*号
     */
    public static String reName(String realName) {

        if (realName == null || "".equals(realName)) {
            return realName;
        } else if ("null".equals(realName)) {
            return null;
        }

        String name = StringUtils.left(realName, 1);
        name = StringUtils.rightPad(name, StringUtils.length(realName), "*");

        /*char[] r = realName.toCharArray();

        String name = "";

        if (r.length == 1) {
            name = realName;
        } else if (r.length == 2) {
            name = realName.replaceFirst(realName.substring(1), "*");
        } else if (r.length > 2) {
            name = realName.replaceFirst(realName.substring(1, r.length - 1), "*");
        }*/
        return name;
    }

    /**
     * 外国人名字脱敏（截取中文部分脱敏，若没有中文则原样返回）
     * @param realName
     * @return
     */
    public static String reForeignName(String realName) {
        if (StringUtils.isEmpty(realName)) {
            return realName;
        }
        Matcher matcher = chineseCompile.matcher(realName);
        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()) {
            stringBuilder.append(matcher.group());
        }
        if (stringBuilder.length() > 0) {
            return reName(stringBuilder.toString());
        } else {
            return realName;
        }
    }


    public static void main(String[] args) throws Exception {
//        System.out.println(rePhone("12345"));
        System.out.println(reName("王五噶登封市"));
    }



}
