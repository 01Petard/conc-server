package com.hzx.conc.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author ouyangan
 * @Date 2017/1/13/16:17
 * @Description http请求工具类
 */
@Slf4j
public class HttpUtil {

    //private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);
    /**
     * 通用请求头
     */
    private static final Header[] headers = new Header[]{
            new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36"),
            new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
    };

    private static final Header[] headersJson = new Header[]{new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36"), new BasicHeader("Content-Type", "application/json; charset=UTF-8")};

    public static boolean IsEmpty(String obj) {
        if ("null".equals(obj)) {
            return true;
        } else if ("".equals(obj)) {
            return true;
        } else if (null == obj) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * @param url
     * @param map
     * @return
     * @throws IOException
     * @Author ouyangan
     * @Date 2017-1-14 15:01:02
     * @Description post请求
     */
    public static String post(String url, Map<String, Object> map) throws IOException {
        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String node = request.getParameter("node");
            if (!HttpUtil.IsEmpty(node)) {
                url = getUrl(url, Integer.valueOf(node));
            }
        }
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
                .setSocketTimeout(60000)
                .setConnectTimeout(2000)
                .setConnectionRequestTimeout(2000)
                .build();
        HttpPost post = new HttpPost(url);
        post.setHeaders(headersJson);
        post.setConfig(requestConfig);
        List<NameValuePair> list = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key) != null) {
                String value = map.get(key).toString();
                log.debug("post.param.key-{}.value-{}", value);
                list.add(new BasicNameValuePair(key, value));
            }
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
        post.setEntity(entity);
        CloseableHttpResponse execute = client.execute(post);
        HttpEntity httpEntity = execute.getEntity();
        String responseString = EntityUtils.toString(httpEntity);
        post.releaseConnection();
        client.close();
        log.debug("responseString:{}", responseString);
        return responseString;
    }

    /**
     * @param url
     * @param map
     * @return
     * @throws IOException
     * @Author ouyangan
     * @Date 2017-1-14 15:01:02
     * @Description post请求
     */
    public static String post(String url, Map<String, Object> map, Integer Timeout) throws IOException {
        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String node = request.getParameter("node");
            if (!HttpUtil.IsEmpty(node)) {
                url = getUrl(url, Integer.valueOf(node));
            }
        }
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
                .setSocketTimeout(Timeout)
                .setConnectTimeout(Timeout)
                .setConnectionRequestTimeout(Timeout)
                .build();
        HttpPost post = new HttpPost(url);
        post.setHeaders(headers);
        post.setConfig(requestConfig);
        List<NameValuePair> list = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key) != null) {
                String value = map.get(key).toString();
                log.debug("post.param.key-{}.value-{}", value);
                list.add(new BasicNameValuePair(key, value));
            }
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
        post.setEntity(entity);
        CloseableHttpResponse execute = client.execute(post);
        HttpEntity httpEntity = execute.getEntity();
        String responseString = EntityUtils.toString(httpEntity);
        post.releaseConnection();
        client.close();
        log.debug("responseString:{}", responseString);
        return responseString;
    }

    public static String getUrl(String url, Integer node) throws IOException {
        if (node != null) {
            switch (node) {

                /*case 2:
                    url=url.replaceAll(DaoUrl.Ip, DaoUrl.ahIp);
                    break;
*/
            }
        }
        return url;
    }

    /**
     * post请求
     *
     * @param url
     * @param param
     * @return
     * @throws IOException
     */
    public static String post(String url, String param) throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String node = request.getParameter("node");

        if (!HttpUtil.IsEmpty(node)) {
            url = getUrl(url, Integer.valueOf(node));
        }
        log.debug("post.请求参数.url:{},entity:{}", url, param);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
                .setSocketTimeout(2000)
                .setConnectTimeout(2000)
                .setConnectionRequestTimeout(2000)
                .build();
        HttpPost post = new HttpPost(url);
        post.setHeaders(headersJson);
        post.setConfig(requestConfig);
        log.debug("post.param.{}", param);
        StringEntity stringEntity = new StringEntity(param, "UTF-8");
        post.setEntity(stringEntity);
        CloseableHttpResponse execute = client.execute(post);
        HttpEntity httpEntity = execute.getEntity();
        String responseString = EntityUtils.toString(httpEntity, "UTF-8");
        post.releaseConnection();
        client.close();
        log.debug("responseString:{}", responseString);
        return responseString;
    }

    /**
     * post请求
     *
     * @param url
     * @param param
     * @return
     * @throws IOException
     */
    public static String post(String url, String param, Header[] headers2) throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String node = request.getParameter("node");

        if (!HttpUtil.IsEmpty(node)) {
            url = getUrl(url, Integer.valueOf(node));
        }
        log.debug("post.请求参数.url:{},entity:{}", url, param);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
                .setSocketTimeout(2000)
                .setConnectTimeout(2000)
                .setConnectionRequestTimeout(2000)
                .build();
        HttpPost post = new HttpPost(url);
        if (headers2 != null && headers2.length > 0) {
            post.setHeaders(headers2);
        } else {
            post.setHeaders(headers);
        }
        post.setConfig(requestConfig);
        log.debug("post.param.{}", param);
        StringEntity stringEntity = new StringEntity(param, "UTF-8");
        post.setEntity(stringEntity);
        CloseableHttpResponse execute = client.execute(post);
        HttpEntity httpEntity = execute.getEntity();
        String responseString = EntityUtils.toString(httpEntity, "UTF-8");
        post.releaseConnection();
        client.close();
        log.debug("responseString:{}", responseString);
        return responseString;
    }

    /**
     * @param url
     * @return
     * @throws IOException
     * @Author ouyangan
     * @Date 2017-1-14 15:02:11
     * @Description get请求
     */
    public static String get(String url) throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String node = request.getParameter("node");
        if (!HttpUtil.IsEmpty(node)) {
            url = getUrl(url, Integer.valueOf(node));
        }

        HttpClients.createDefault();
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
                .setSocketTimeout(2000)
                .setConnectTimeout(2000)
                .setConnectionRequestTimeout(2000)
                .build();
        HttpGet get = new HttpGet(url);
        log.debug("get.url-{}", url);
        get.setHeaders(headers);
        get.setConfig(requestConfig);
        CloseableHttpResponse execute = client.execute(get);
        HttpEntity httpEntity = execute.getEntity();
        String s = EntityUtils.toString(httpEntity, "utf8");
        get.releaseConnection();
        client.close();
        return s;
    }


//    public static String uploadFastDfs(String fileName) throws ClientProtocolException, IOException {
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        CloseableHttpResponse httpResponse = null;
//        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000000).build();
//        HttpPost httpPost = new HttpPost("http://www.zhonshian.com/zsaimage/fastDfs/uploadFast");
//        httpPost.setConfig(requestConfig);
//        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
//        // multipartEntityBuilder.setCharset(Charset.forName("UTF-8"));
//
//        File file = new File(fileName);
//        String urlString = null;
//        // multipartEntityBuilder.addBinaryBody("file",
//        // file,ContentType.create("image/png"),"abc.pdf");
//        // 当设置了setSocketTimeout参数后，以下代码上传PDF不能成功，将setSocketTimeout参数去掉后此可以上传成功。上传图片则没有个限制
//        // multipartEntityBuilder.addBinaryBody("file",file,ContentType.create("application/octet-stream"),"abd.pdf");
//        multipartEntityBuilder.addBinaryBody("file", file);
//        multipartEntityBuilder.addTextBody("groupType", "other");
//        HttpEntity httpEntity = multipartEntityBuilder.build();
//        httpPost.setEntity(httpEntity);
//
//        httpResponse = httpClient.execute(httpPost);
//        HttpEntity responseEntity = httpResponse.getEntity();
//        int statusCode = httpResponse.getStatusLine().getStatusCode();
//        if (statusCode == 200) {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(responseEntity.getContent()));
//            StringBuffer buffer = new StringBuffer();
//            String str = "";
//            while ((str = reader.readLine()) != null) {
//                buffer.append(str);
//            }
//            String url = buffer.toString();
//            JSONObject urlJson = JSONObject.parseObject(url);
//            urlString = urlJson.get("data").toString();
//        }
//
//        httpClient.close();
//        if (httpResponse != null) {
//            httpResponse.close();
//        }
//        return urlString;
//    }

    public static String postNotNode(String url, Map<String, Object> map) throws IOException {

        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
                .setSocketTimeout(60000)
                .setConnectTimeout(2000)
                .setConnectionRequestTimeout(2000)
                .build();
        HttpPost post = new HttpPost(url);
        post.setHeaders(headers);
        post.setConfig(requestConfig);
        List<NameValuePair> list = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key) != null) {
                String value = map.get(key).toString();
                log.debug("post.param.key-{}.value-{}", value);
                list.add(new BasicNameValuePair(key, value));
            }
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
        post.setEntity(entity);
        CloseableHttpResponse execute = client.execute(post);
        HttpEntity httpEntity = execute.getEntity();
        String responseString = EntityUtils.toString(httpEntity);
        post.releaseConnection();
        client.close();
        log.debug("responseString:{}", responseString);
        return responseString;
    }

    /**
     * 提交file模拟form表单
     *
     * @param url
     * @param savefileName
     * @param fileName
     * @param param
     * @return
     */
    public static String doPostWithFile(String url, String savefileName, String fileName, String param) {
        String result = "";
        try {
            // 换行符
            final String newLine = "\r\n";
            final String boundaryPrefix = "--";
            // 定义数据分隔线
            String BOUNDARY = "========7d4a6d158c9";
            // 服务器的域名
            URL realurl = new URL(url);
            // 发送POST请求必须设置如下两行
            HttpURLConnection connection = (HttpURLConnection) realurl.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            // 头
            String boundary = BOUNDARY;
            // 传输内容
            StringBuffer contentBody = new StringBuffer("--" + BOUNDARY);
            // 尾
            String endBoundary = "\r\n--" + boundary + "--\r\n";
            OutputStream out = connection.getOutputStream();


            // 1. 处理普通表单域(即形如key = value对)的POST请求（这里也可以循环处理多个字段，或直接给json）
            //这里看过其他的资料，都没有尝试成功是因为下面多给了个Content-Type
            //form-data  这个是form上传 可以模拟任何类型
            contentBody.append("\r\n")
                    .append("Content-Disposition: form-data; name=\"")
                    .append("param" + "\"")
                    .append("\r\n")
                    .append("\r\n")
                    .append(param)
                    .append("\r\n")
                    .append("--")
                    .append(boundary);
            String boundaryMessage1 = contentBody.toString();
            out.write(boundaryMessage1.getBytes("utf-8"));

            // 2. 处理file文件的POST请求（多个file可以循环处理）
            contentBody = new StringBuffer();
            contentBody.append("\r\n")
                    .append("Content-Disposition:form-data; name=\"")
                    .append("file" + "\"; ")   // form中field的名称
                    .append("filename=\"")
                    .append(fileName + "\"")   //上传文件的文件名，包括目录
                    .append("\r\n")
                    .append("Content-Type:multipart/form-data")
                    .append("\r\n\r\n");
            String boundaryMessage2 = contentBody.toString();
            out.write(boundaryMessage2.getBytes("utf-8"));

            // 开始真正向服务器写文件
            File file = new File(savefileName);
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            int bytes = 0;
            byte[] bufferOut = new byte[(int) file.length()];
            bytes = dis.read(bufferOut);
            out.write(bufferOut, 0, bytes);
            dis.close();
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
            out.write(endData);
            out.flush();
            out.close();

            // 4. 从服务器获得回答的内容
            String strLine = "";
            String strResponse = "";
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            while ((strLine = reader.readLine()) != null) {
                strResponse += strLine + "\n";
            }
            System.out.print(strResponse);
            return strResponse;
        } catch (Exception e) {
            throw new RuntimeException("请求文件服务器异常..." + e.getMessage());
        }
    }

    //    /**
//     * 获取截图
//     * @param url 视频流地址
//     * @return
//     * @throws IOException
//     */
//    public static String monitorCaptureImageUrlPost(String url, String param) throws Exception {
//        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
//        CloseableHttpClient client = httpClientBuilder.build();
//        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
//                .setSocketTimeout(10000)
//                .setConnectTimeout(6000)
//                .setConnectionRequestTimeout(10000)
//                .build();
//        HttpPost post = new HttpPost(url);
//        post.setHeaders(headers);
//        post.setConfig(requestConfig);
//        log.debug("post.param.{}", param);
//        StringEntity stringEntity = new StringEntity(param, "UTF-8");
//        post.setEntity(stringEntity);
//        HttpEntity httpEntity = null;
//        int count = 2; // 失败重试次数
//        do {
//            CloseableHttpResponse response = client.execute(post);
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode == 200) {
//                httpEntity = response.getEntity();
//                break;
//            }
//        } while (count-- > 0);
//        if (httpEntity != null) {
//            InputStream content = httpEntity.getContent();
//            String result = new FastUploadImg().uploadFile(null, FastGroupType.PERSONAL_INFO, content);
//            if (StringUtils.isNotBlank(result)) {
//                return JSONObject.parseObject(result).getString("data");
//            }
//        }
//        return null;
//    }
    public static String post(String url, Map<String, Object> map, Header[] header) throws IOException {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT).setSocketTimeout(2000).setConnectTimeout(2000).setConnectionRequestTimeout(2000).build();
        HttpPost post = new HttpPost(url);
        post.setHeaders(header);
        post.setConfig(requestConfig);
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        for (String key : map.keySet()) {
            if (map.get(key) != null) {
                String value = map.get(key).toString();
                log.debug("post.param.key-{}.value-{}", value);
                list.add(new BasicNameValuePair(key, value));
            }
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
        post.setEntity(entity);
        CloseableHttpResponse execute = client.execute(post);
        HttpEntity httpEntity = execute.getEntity();
        String responseString = EntityUtils.toString(httpEntity);
        post.releaseConnection();
        client.close();
        log.debug("responseString:{}", responseString);
        return responseString;
    }

    public static String postJson(String url, String param) throws IOException {
        log.info("post.请求参数.url:{},entity:{}", url, param);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT).setSocketTimeout(5000).setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();
        HttpPost post = new HttpPost(url);
        Header[] header = new Header[]{
                new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36"),
                new BasicHeader("Content-Type", "application/json; charset=UTF-8")
        };
        post.setHeaders(header);
        post.setConfig(requestConfig);
        log.debug("post.param.{}", param);
        StringEntity stringEntity = new StringEntity(param, "UTF-8");
        post.setEntity(stringEntity);
        CloseableHttpResponse execute = client.execute(post);
        HttpEntity httpEntity = execute.getEntity();
        String responseString = EntityUtils.toString(httpEntity, "UTF-8");
        post.releaseConnection();
        client.close();
        log.debug("responseString:{}", responseString);
        return responseString;
    }

    public static String postJson(String url, String param, Header[] headers) throws IOException {
        log.debug("post.请求参数.url:{},entity:{}", url, param);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT).setSocketTimeout(2000).setConnectTimeout(2000).setConnectionRequestTimeout(2000).build();
        HttpPost post = new HttpPost(url);
        post.setHeaders(headers);
        post.setConfig(requestConfig);
        log.debug("post.param.{}", param);
        StringEntity stringEntity = new StringEntity(param, "UTF-8");
        post.setEntity(stringEntity);
        CloseableHttpResponse execute = client.execute(post);
        HttpEntity httpEntity = execute.getEntity();
        String responseString = EntityUtils.toString(httpEntity, "UTF-8");
        post.releaseConnection();
        client.close();
        log.debug("responseString:{}", responseString);
        return responseString;
    }

    /**
     * @param url
     * @param map
     * @return
     * @throws IOException
     * @Author ouyangan
     * @Date 2017-1-14 15:01:02
     * @Description post请求
     */
    public static String post1(String url, Map<String, Object> map) throws IOException {
        if (RequestContextHolder.getRequestAttributes() != null) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();


            String node = request.getParameter("node");
            if (!HttpUtil.IsEmpty(node)) {
                url = getUrl(url, Integer.valueOf(node));
            }
        }
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
                .setSocketTimeout(6000)
                .setConnectTimeout(2000)
                .setConnectionRequestTimeout(2000)
                .build();
        HttpPost post = new HttpPost(url);
        post.setHeaders(headers);
        post.setConfig(requestConfig);
        List<NameValuePair> list = new ArrayList<>();
        for (String key : map.keySet()) {
            if (map.get(key) != null) {
                String value = map.get(key).toString();
                log.debug("post.param.key-{}.value-{}", value);
                list.add(new BasicNameValuePair(key, value));
            }
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
        post.setEntity(entity);
        CloseableHttpResponse execute = client.execute(post);
        HttpEntity httpEntity = execute.getEntity();
        String responseString = EntityUtils.toString(httpEntity);
        post.releaseConnection();
        client.close();
        log.debug("responseString:{}", responseString);
        return responseString;
    }

    public static String postJsonCookie(String url, String param, String sessionId) throws IOException {
        log.info("post.请求参数.url:{},entity:{}", url, param);
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient client = httpClientBuilder.build();
        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT).setSocketTimeout(60000).setConnectTimeout(60000).setConnectionRequestTimeout(60000).build();
        HttpPost post = new HttpPost(url);
        Header[] header = new Header[]{
                new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36"),
                new BasicHeader("Content-Type", "application/json; charset=UTF-8"),
                new BasicHeader("Cookie", "kdservice-sessionid=" + sessionId)
        };
        post.setHeaders(header);
        post.setConfig(requestConfig);
        log.debug("post.param.{}", param);
        StringEntity stringEntity = new StringEntity(param, "UTF-8");
        post.setEntity(stringEntity);
        CloseableHttpResponse execute = client.execute(post);
        HttpEntity httpEntity = execute.getEntity();
        String responseString = EntityUtils.toString(httpEntity, "UTF-8");
        post.releaseConnection();
        client.close();
        log.debug("responseString:{}", responseString);
        return responseString;
    }
}
