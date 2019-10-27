package com.capi.utils;

import com.capi.utils.PropertiesUtil;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PooledHttpClientUtil {
    private static Logger logger = LoggerFactory.getLogger(PooledHttpClientUtil.class);

    public static PoolingHttpClientConnectionManager connectionManager = null;

    public static CloseableHttpClient httpClient = null;

    //默认contentType
    private static final String DEFAULT_CONTENT_TYPE = PropertiesUtil.getPropertiesValue("threadpool.properties", "httpclient.contentType");

    //连接超时时间（秒）
    private static final int DEFAUL_TIME_OUT = Integer.parseInt(PropertiesUtil.getPropertiesValue("threadpool.properties", "httpclient.timeout.seconds"));

    //单个路由最大连接数
    private static final int COUNT = Integer.parseInt(PropertiesUtil.getPropertiesValue("threadpool.properties", "httpclient.route.max"));

    //总最大连接数
    private static final int TOTAL_COUNT = Integer.parseInt(PropertiesUtil.getPropertiesValue("threadpool.properties", "httpclient.total.max"));

    //连接时间（秒）
    private static final int HTTP_DEFAULT_KEEP_TIME = Integer.parseInt(PropertiesUtil.getPropertiesValue("threadpool.properties", "httpclient.keepAlive.seconds"));

    /**
     * 初始化连接池
     */
    public static synchronized void initPools() {
        if (httpClient == null) {
            connectionManager = new PoolingHttpClientConnectionManager();
            connectionManager.setDefaultMaxPerRoute(COUNT);
            connectionManager.setMaxTotal(TOTAL_COUNT);
            httpClient = HttpClients.custom().setKeepAliveStrategy(defaultStrategy).setConnectionManager(connectionManager).build();
        }
    }

    /**
     * Http connection keepAlive 设置
     */
    public static ConnectionKeepAliveStrategy defaultStrategy = new ConnectionKeepAliveStrategy() {
        public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
            HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            int keepTime = HTTP_DEFAULT_KEEP_TIME;
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && param.equalsIgnoreCase("timeout")) {
                    try {
                        return Long.parseLong(value) * 1000;
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("format KeepAlive timeout exception, exception:" + e.toString());
                    }
                }
            }
            return keepTime * 1000;
        }
    };

    public static CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public static PoolingHttpClientConnectionManager getHttpConnectionManager() {
        return connectionManager;
    }

    /**
     * 创建请求
     *
     * @param uri 请求url
     * @param methodName 请求的方法类型
     * @param contentType contentType类型
     * @param timeout 超时时间
     * @return
     */
    public static HttpRequestBase getRequest(String uri, String methodName, Map<String, String> headers, int timeout) {
        if (httpClient == null) {
            initPools();
        }
        HttpRequestBase method = null;
        if (timeout <= 0) {
            timeout = DEFAUL_TIME_OUT;
        }
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout * 1000).setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000).setExpectContinueEnabled(false).build();

        if (HttpPut.METHOD_NAME.equalsIgnoreCase(methodName)) {
            method = new HttpPut(uri);
        } else if (HttpPost.METHOD_NAME.equalsIgnoreCase(methodName)) {
            method = new HttpPost(uri);
        } else if (HttpGet.METHOD_NAME.equalsIgnoreCase(methodName)) {
            method = new HttpGet(uri);
        } else {
            method = new HttpPost(uri);
        }
        
        if (StringUtils.isBlank(headers.get("Content-Type"))) {
        	headers.put("Content-Type", DEFAULT_CONTENT_TYPE);
        }
        if (StringUtils.isBlank(headers.get("Accept"))) {
        	headers.put("Accept", DEFAULT_CONTENT_TYPE);
        }
        Set<String> keys = headers.keySet();
        for (String key : keys) {
			method.setHeader(key, headers.get(key));
		}
        method.setConfig(requestConfig);
        return method;
    }
    
    /**
     * 执行http post请求 默认采用Content-Type：application/json，Accept：application/json
     *
     * @param uri 请求地址
     * @param data  请求数据
     * @param headers  请求头
     * @return
     */
    public static String doPost(String uri, String data, Map<String, String> headers) {
        long startTime = System.currentTimeMillis();
        HttpEntity httpEntity = null;
        HttpEntityEnclosingRequestBase method = null;
        String responseBody = "";
        try {
            if (httpClient == null) {
                initPools();
            }
            method = (HttpEntityEnclosingRequestBase) getRequest(uri, HttpPost.METHOD_NAME, headers, 0);
            method.setEntity(new StringEntity(data, Charset.forName("UTF-8")));
            HttpContext context = HttpClientContext.create();
            CloseableHttpResponse httpResponse = httpClient.execute(method, context);
            httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                responseBody = EntityUtils.toString(httpEntity, "UTF-8");
            }

        } catch (Exception e) {
            if(method != null){
                method.abort();
            }
            e.printStackTrace();
            logger.error(
                    "execute post request exception, url:" + uri + ", exception:" + e.toString() + ", cost time(ms):"
                            + (System.currentTimeMillis() - startTime));
        } finally {
            if (httpEntity != null) {
                try {
                    EntityUtils.consumeQuietly(httpEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(
                            "close response exception, url:" + uri + ", exception:" + e.toString() + ", cost time(ms):"
                                    + (System.currentTimeMillis() - startTime));
                }
            }
        }
        return responseBody;
    }

    /**
     * 执行GET 请求
     *
     * @param uri
     * @param headers  请求头
     * @return
     */
    public static String doGet(String uri, Map<String, String> headers) {
    	logger.info("请求开始");
        long startTime = System.currentTimeMillis();
        HttpEntity httpEntity = null;
        HttpRequestBase method = null;
        String responseBody = "";
        try {
            if (httpClient == null) {
                initPools();
            }
            method = getRequest(uri, HttpGet.METHOD_NAME, headers, 0);
            HttpContext context = HttpClientContext.create();
            CloseableHttpResponse httpResponse = httpClient.execute(method, context);
            httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                responseBody = EntityUtils.toString(httpEntity, "UTF-8");
                logger.info("请求URL: "+uri+"+  返回状态码："+httpResponse.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            if(method != null){
                method.abort();
            }
            e.printStackTrace();
            logger.error("execute get request exception, url:" + uri + ", exception:" + e.toString() + ",cost time(ms):"
                    + (System.currentTimeMillis() - startTime));
        } finally {
            if (httpEntity != null) {
                try {
                    EntityUtils.consumeQuietly(httpEntity);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("close response exception, url:" + uri + ", exception:" + e.toString() + ",cost time(ms):"
                            + (System.currentTimeMillis() - startTime));
                }
            }
        }
        logger.info("请求结束");
        return responseBody;
    }
}
