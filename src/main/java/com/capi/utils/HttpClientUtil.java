package com.capi.utils;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

public class HttpClientUtil {

	// 接口地址
	private String apiURL = "http://d.95jr.com/api/mutiloan/newproduct.aspx";
	private Log logger = LogFactory.getLog(this.getClass());
	private CloseableHttpClient httpClient = null;
	private HttpPost httpPost = null;
	private long startTime = 0L;
	private long endTime = 0L;
	private int status = 0;

	/**
	 * 接口地址
	 * 
	 * @param url
	 */
	public HttpClientUtil(String url) {
		if (url != null) {
			this.apiURL = url;
		}
		if (apiURL != null) {
			httpClient = HttpClients.createDefault();
			httpPost = new HttpPost(apiURL);
		}
	}

	/**
	 * 调用 API
	 * 
	 * @param parameters
	 * @return
	 */
	public String post(String contentType, String parameters) {
		String body = null;
		logger.info("parameters:" + parameters);

		if (httpPost != null & parameters != null && !"".equals(parameters.trim())) {
			try {
				// 建立一个NameValuePair数组，用于存储欲传送的参数
				httpPost.addHeader("Content-type", contentType);
				httpPost.setHeader("Accept", "application/json");
				httpPost.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));
				startTime = System.currentTimeMillis();

				CloseableHttpResponse response = httpClient.execute(httpPost);

				endTime = System.currentTimeMillis();
				int statusCode = response.getStatusLine().getStatusCode();

				logger.info("statusCode:" + statusCode);
				logger.info("调用API 花费时间(单位：毫秒)：" + (endTime - startTime));
				if (statusCode != HttpStatus.SC_OK) {
					logger.error("httpPost failed:" + response.getStatusLine());
					status = 1;
				}

				// Read the response body
				body = EntityUtils.toString(response.getEntity());
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				status = 2;
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				status = 1;
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				status = 3;
				e.printStackTrace();
			} finally {  
                logger.info("调用接口状态：" + status);  
            }  
		}
		return body;
	}

	/**
	 * 0.成功 1.执行方法失败 2.协议错误 3.网络错误
	 * 
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the startTime
	 */
	public long getStartTime() {
		return startTime;
	}

	/**
	 * @return the endTime
	 */
	public long getEndTime() {
		return endTime;
	}
	
	public void openClient() {
		if (apiURL != null) {
			httpClient = HttpClients.createDefault();
			httpPost = new HttpPost(apiURL);
		}
	}
	
	public void closeClient() {
		try {
			this.httpClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}