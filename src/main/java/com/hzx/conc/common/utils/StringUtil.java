package com.hzx.conc.common.utils;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.SM3;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbSearcher;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * String 工具类
 */
@Slf4j
public class StringUtil {

    private static final String UNKNOWN = "unknown";

    /**
     * 常用接口
     */
    public static class Url {
        // IP归属地查询
        public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp?ip=%s&json=true";
    }

    /**
     * 用于IP定位转换
     */
    public static final String REGION = "内网IP|内网IP";

    private static boolean ipLocal = false;

    private static DbConfig config;

    private static File file = null;

    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 下划线转驼峰
     * @param str 下划线字符串
     * @return 驼峰字符串
     */
    public static String lineToHump(String str) {
        if (null == str || "".equals(str)) {
            return str;
        }
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        str = sb.toString();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);

        return str;
    }

    /**
     * 驼峰转下划线,效率比上面高
     * @param str 驼峰字符串
     * @return 下划线字符串
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线(简单写法，效率低于{@link #humpToLine(String)})
     * @param str 驼峰字符串
     * @return 下划线字符串
     */
    public static String humpToLine2(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    /**
     * 首字母转小写
     * @param s 字符串
     * @return 首字母小写的字符串
     */
    public static String toLowerCaseFirstOne(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * 首字母转大写
     * @param s 字符串
     * @return 首字母大写的字符串
     */
    public static String toUpperCaseFirstOne(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuffer()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    /**
     * object转String
     * @param object object
     * @return String
     */
    public static String getString(Object object) {
        return getString(object, "");
    }

    /**
     * object转String，提供默认值
     * @param object       object
     * @param defaultValue 默认值
     * @return String
     */
    public static String getString(Object object, String defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        try {
            return object.toString();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * object转int
     * @param object object
     * @return int
     */
    public static int getInt(Object object) {
        return getInt(object, -1);
    }

    /**
     * object转int，提供默认值
     * @param object       object
     * @param defaultValue 默认值
     * @return int
     */
    public static int getInt(Object object, Integer defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(object.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * object转Boolean
     * @param object object
     * @return boolean
     */
    public static boolean getBoolean(Object object) {
        return getBoolean(object, false);
    }

    /**
     * object转boolean，提供默认值
     * @param object       object
     * @param defaultValue 默认值
     * @return boolean
     */
    public static boolean getBoolean(Object object, Boolean defaultValue) {
        if (null == object) {
            return defaultValue;
        }
        try {
            return Boolean.parseBoolean(object.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static boolean isEmpty(String str) {
        if (str == null) {
            return true;
        }
        if ("null".equals(str)) {
            return true;
        }
        return "".equals(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        if (str != null && str.length() != 0 && !"null".equals(str)) {
            return true;
        }
        return false;
    }


    /**
     * 判断字符串是否为空
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否为空
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 获取随机四位验证码
     */
    public static String getRandomCode() {

        return RandomStringUtils.randomNumeric(4);
    }


    public static String listToString(List<?> list) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (null != list.get(i)) {
                sb.append(list.get(i));
                if (i < list.size() - 1) {
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }

    public static String listToStringSplit(List<?> list, String split) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(split);
            }
        }
        return sb.toString();
    }

    /**
     * 将拼接的字符串转换成List<Integer> 并去除为空的
     * @param str   原始字符串
     * @param regex 拼接的字符
     */
    public static List<Integer> stringToIntegerList(String str, String regex) {
        if (StringUtil.isEmpty(str)) {
            return Collections.emptyList();
        }

        return Arrays.stream(str.split(regex))
                .filter(StringUtil::isNotEmpty)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    public static List<Integer> stringToIntegerList(String ids) {
        String[] split = ids.split(",");
        List<Integer> list = new ArrayList<>();
        for (String string : split) {
            if (StringUtil.isNotEmpty(string)) {
                list.add(Integer.parseInt(string));
            }
        }
        return list;
    }

    public static List<String> stringToStringList(String ids) {
        String[] split = ids.split(",");
        List<String> list = new ArrayList<>();
        for (String string : split) {
            if (StringUtil.isNotEmpty(string)) {
                list.add(string.trim());
            }
        }
        return list;
    }

    public static Set<String> stringToStringSet(String ids) {
        String[] split = ids.split(",");
        Set<String> set = new HashSet<>();
        for (String string : split) {
            if (StringUtil.isNotEmpty(string)) {
                set.add(string.trim());
            }
        }
        return set;
    }

    /**
     * 将拼接的字符串转换成List<Integer> 并去除为空的
     * @param str   原始字符串
     * @param regex 拼接的字符
     */
    public static Set<Integer> stringToIntegerSet(String str, String regex) {
        if (StringUtil.isEmpty(str)) {
            return Collections.emptySet();
        }

        return Arrays.stream(str.split(regex))
                .filter(StringUtil::isNotEmpty)
                .map(Integer::valueOf)
                .collect(Collectors.toSet());
    }

    public static List<String> longToStringList(List<Long> ids) {
        List<String> strIds = new ArrayList<>();
        for (Long id : ids) {
            strIds.add(String.valueOf(id));
        }
        return strIds;
    }

    /**
     * 逗号分隔id转long数组集合
     */
    public static List<Long> stringToLongList(String ids) {
        return Arrays.asList(ids.split(",")).stream().map(str -> Long.parseLong(str)).collect(Collectors.toList());
    }

    /**
     * 将拼接的字符串转换成List<Long> 并去除为空的
     * @param str   原始字符串
     * @param regex 拼接的字符
     */
    public static List<Long> stringToLongList(String str, String regex) {
        if (StringUtil.isEmpty(str)) {
            return Collections.emptyList();
        }

        return Arrays.stream(str.split(regex))
                .filter(StringUtil::isNotEmpty)
                .map(Long::valueOf)
                .collect(Collectors.toList());
    }

    /**
     * 值映射为枚举
     * @param enumClass 枚举类
     * @param value     枚举值
     * @param method    取值方法
     * @param <E>       对应枚举
     */
    public static <E extends Enum<?>> E getEnumItemByValue(Class<E> enumClass, Object value, Method method) {
        E[] es = enumClass.getEnumConstants();
        for (E e : es) {
            Object evalue = null;
            try {
                method.setAccessible(true);
                evalue = method.invoke(e);
            } catch (IllegalAccessException | InvocationTargetException e1) {
                log.error("Error: NoSuchMethod in {}.  Cause: {}", e, enumClass.getName());
            }
            if (value instanceof Number && evalue instanceof Number
                    && new BigDecimal(String.valueOf(value)).compareTo(new BigDecimal(String.valueOf(evalue))) == 0) {
                return e;
            }
            if (Objects.equals(evalue, value)) {
                return e;
            }
        }
        return null;
    }

    /**
     * 缩略字符串（不区分中英文字符）
     * @param str    目标字符串
     * @param length 截取长度
     */
    public static String abbr(String str, int length) {
        if (str == null) {
            return "";
        }
        try {
            StringBuilder sb = new StringBuilder();
            int currentLength = 0;
            for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
                currentLength += String.valueOf(c).getBytes("GBK").length;
                if (currentLength <= length - 3) {
                    sb.append(c);
                } else {
                    sb.append("...");
                    break;
                }
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 替换掉HTML标签方法
     */
    public static String replaceHtml(String html) {
        if (isBlank(html)) {
            return "";
        }
        String regEx = "<.+?>";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(html);
        String s = m.replaceAll("");
        return s;
    }


    /**
     * 将阿拉伯数字转换为中文(10以内)
     */
    public static String convertNumber(Integer number) {
        switch (number) {
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "七";
            case 8:
                return "八";
            case 9:
                return "九";
            case 10:
                return "十";
            default:
                return "未知";
        }
    }

    public static boolean checkSqlStr(String src) {
        if (src.contains("%") || src.contains("_")) {
            return true;
        }
        return false;
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        if (str.indexOf(".") > 0) {//判断是否有小数点
            if (str.indexOf(".") == str.lastIndexOf(".") && str.split("\\.").length == 2) { //判断是否只有一个小数点
                return pattern.matcher(str.replace(".", "")).matches();
            } else {
                return false;
            }
        } else {
            return pattern.matcher(str).matches();
        }
    }

    /**
     * 生成一个五位随机字符串
     */
    public static String createRandomChar() {
        return RandomStringUtils.randomAlphanumeric(5);
    }


    /**
     * 生成密码 国密算法
     * @param password
     * @param salt
     * @return
     */
    public static String createPasswordSM3(String password, String salt) {
        SM3 sm3 = new SM3(salt.getBytes());
        return Base64.encode(sm3.digest(password));
    }

    public static void main(String[] args) {
        String salt = IdUtil.simpleUUID();
        String md5Password = StringUtil.createPasswordSM3("Admin@000000", salt);
        System.out.println(salt);
        System.out.println(md5Password);
    }

    /**
     * 获取ip地址
     */
    public static String getIp(HttpServletRequest request) {
        String comma = ",";
        String localhost = "127.0.0.1";

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (StringUtil.isBlank(ip)) {
            ip = localhost;
        }
        if (ip.contains(comma)) {
            ip = ip.split(",")[0];
        }
        if (localhost.equals(ip)) {
            // 获取本机真正的ip地址
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                log.error(e.getMessage(), e);
            }
        }
        return ip;
    }

    /**
     * 根据ip获取详细地址
     */
    public static String getCityInfo(String ip) {
        try {
            if (ipLocal) {
                return getLocalCityInfo(ip);
            } else {
                return getHttpCityInfo(ip);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    /**
     * 根据ip获取详细地址
     */
    public static String getHttpCityInfo(String ip) {
        String api = String.format(Url.IP_URL, ip);
        JSONObject object = JSONUtil.parseObj(HttpUtil.get(api));
        return object.get("addr", String.class);
    }

    /**
     * 根据ip获取详细地址
     */
    public static String getLocalCityInfo(String ip) {
        try {
            DataBlock dataBlock = new DbSearcher(config, file.getPath())
                    .binarySearch(ip);
            String region = dataBlock.getRegion();
            String address = region.replace("0|", "");
            char symbol = '|';
            if (address.charAt(address.length() - 1) == symbol) {
                address = address.substring(0, address.length() - 1);
            }
            return address.equals(REGION) ? "内网IP" : address;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return "";
    }

    /**
     * 查询json参数
     *
     * @param request
     * @return
     */
    public static String getParamsJsonStr(HttpServletRequest request) throws Exception {
        //从前端获取输入字节流
        InputStream stream = request.getInputStream();
        //将字节流转换为字符流,并设置字符编码为utf-8
        InputStreamReader ir = new InputStreamReader(stream, StandardCharsets.UTF_8);
        //使用字符缓冲流进行读取
        BufferedReader br = new BufferedReader(ir);
        //开始拼装json字符串
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    /**
     * sql注入字段替换
     */
    public static String sqlLikeFilter(String param) {
        if (Strings.isNotBlank(param)) {
            param = param.replace("%", "\\%");
            param = param.replace("_", "\\_");
        }
        return param;
    }

    /**
     * 解析地址
     * @param address
     * @return
     */
    public static List<Map<String, String>> addressResolution(String address) {
        /*
         * java.util.regex是一个用正则表达式所订制的模式来对字符串进行匹配工作的类库包。它包括两个类：Pattern和Matcher Pattern
         *    一个Pattern是一个正则表达式经编译后的表现模式。 Matcher
         *    一个Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查。
         *    首先一个Pattern实例订制了一个所用语法与PERL的类似的正则表达式经编译后的模式，然后一个Matcher实例在这个给定的Pattern实例的模式控制下进行字符串的匹配工作。
         */
        String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province = null, city = null, county = null, town = null, village = null;
        List<Map<String, String>> table = new ArrayList<Map<String, String>>();
        Map<String, String> row = null;
        while (m.find()) {
            row = new LinkedHashMap<String, String>();
            province = m.group("province");
            row.put("province", province == null ? "" : province.trim());
            city = m.group("city");
            row.put("city", city == null ? "" : city.trim());
            county = m.group("county");
            row.put("county", county == null ? "" : county.trim());
            town = m.group("town");
            row.put("town", town == null ? "" : town.trim());
            village = m.group("village");
            row.put("village", village == null ? "" : village.trim());
            table.add(row);
        }
        return table;
    }

    public static String getLowestRegion(String address) {
        if (address.endsWith("省")) {
            return address;
        }
        List<Map<String, String>> table = addressResolution(address);
        return StringUtil.isEmpty(table.get(0).get("village")) ? (StringUtil.isEmpty(table.get(0).get("town")) ? (StringUtil.isEmpty(table.get(0).get("county")) ? (StringUtil.isEmpty(table.get(0).get("city")) ? table.get(0).get("province") : table.get(0).get("city")) : table.get(0).get("county")) : table.get(0).get("town")) : table.get(0).get("village");
    }
}
