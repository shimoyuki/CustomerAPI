package com.capi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.GenericXmlContextLoader;

import com.capi.utils.IpUtil;
import com.capi.utils.PooledHttpClientUtil;

@RunWith(value = SpringJUnit4ClassRunner.class)
//在使用所有注释前必须使用@RunWith(SpringJUnit4ClassRunner.class),让测试运行于Spring测试环境
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml", "classpath:spring-mvc.xml"} , loader = GenericXmlContextLoader.class)
//【@ContextConfiguration 用来指定加载的Spring配置文件的位置,会加载默认配置文件
public class TestProperties {
	@Value("${threadpool.core.poolsize}")
	private int passengerCorePoolSize;
	@Autowired
	ThreadPoolTaskExecutor threadpoolTask;
	
	@Test
	public void test() {
//		System.out.println(passengerTask.getMaxPoolSize());
//		System.out.println(passengerCorePoolSize);
		/*Map<String, String> headers = new HashMap<>();
		headers.put("contentType", "application/json");
		headers.put("Accept", "application/json");
		for (int i = 0; i < 10; i++) {
			headers.put("X-Real-IP", IpUtil.getRandomIp());
			PooledHttpClientUtil.doGet("http://119.18.231.129:15080/openapi/insurance/free/draw.json", headers);
		}*/
		Queue<Integer> data = new LinkedBlockingQueue<>();
		for (int i = 0; i < 10; i++) {
			data.offer(i);
		}
		
		Semaphore semaphore = new Semaphore(threadpoolTask.getMaxPoolSize());
		List<Future<String>> futures = new ArrayList<>();
		Future<String> future = null;
		AtomicInteger total = new AtomicInteger(0), count = new AtomicInteger(0);
		while (!data.isEmpty()) {
			int sendData = data.poll();
			future = threadpoolTask.submit(new Callable<String>() {

				@Override
				public String call() throws Exception {
					// TODO Auto-generated method stub
					semaphore.acquire();
					System.out.println("发送数据"+sendData+"开始");
					System.out.println("当前线程数"+threadpoolTask.getActiveCount());
					Map<String, String> headers = new HashMap<>();
					headers.put("contentType", "application/json");
					headers.put("Accept", "application/json");
					headers.put("openChannel", "application/json");
					headers.put("phone", "application/json");
					headers.put("openEncypt", "application/json");
					headers.put("X-Real-IP", IpUtil.getRandomIp());
					String response = PooledHttpClientUtil.doGet("http://119.18.231.129:15080/openapi/sms/send.json", headers);
					//System.out.println(response);
					if (new Random().nextInt() > 0) {
						count.incrementAndGet();
						System.out.println("发送数据"+sendData+"成功");
					}else {
						System.out.println("发送数据"+sendData+"失败");
					}
					total.incrementAndGet();
					semaphore.release();
					return response;
				}
				
			});
			futures.add(future);
		}
		threadpoolTask.shutdown();
		while(threadpoolTask.getActiveCount() > 0.);
		System.out.println(total);
		System.out.println(count);
	}

}
