package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.example.demo.dao.entity.Student;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalExample {
    private ThreadLocal<Map<String,Student>> parentThreadLocal;
    private ExecutorService executor;
    private Gson gson = new Gson();
    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalExample.class);
    private InheritableThreadLocal<Map<String,Student>> parentInheritableThreadLocal;
    private TransmittableThreadLocal<Map<String,Student>> parentTransmittableThreadLocal;
    
    public ThreadLocalExample(){
    	parentThreadLocal = new ThreadLocal<>();
    	executor = Executors.newFixedThreadPool(2);
    	executor = TtlExecutors.getTtlExecutorService(executor);
    	parentInheritableThreadLocal = new InheritableThreadLocal<>();
    	parentTransmittableThreadLocal = new TransmittableThreadLocal<>();
    }
    
    @GetMapping("")
    public Map<String,Map<String,Student>> multi(){
    	parentThreadLocal.set(Maps.newConcurrentMap());
    	parentInheritableThreadLocal.set(Maps.newConcurrentMap());
    	parentTransmittableThreadLocal.set(Maps.newConcurrentMap());
    	logger.info("value of parentThreadLocal in parentThread is [{}]",gson.toJson(parentThreadLocal.get()));
    	logger.info("value of parentInheritableThreadLocal in parentThread is [{}]",gson.toJson(parentInheritableThreadLocal.get()));
    	logger.info("value of parentTransmittableThreadLocal in parentThread is [{}]",gson.toJson(parentTransmittableThreadLocal.get()));
    	List<Future<Void>> threadResults = Lists.newArrayList();
    	for(int i =0 ;i<=2;i++){
    		threadResults.add(executor.submit(new SubThread("Thread"+i)));
    	}
    	
    	for(Future<Void> future:threadResults){
    		try {
				future.get();
			} catch (InterruptedException | ExecutionException e) {
				logger.error(e.getMessage(),e);
			}
    	}
    	logger.info("value of parentThreadLocal in parentThread is [{}]",gson.toJson(parentThreadLocal.get()));
    	logger.info("value of parentInheritableThreadLocal in parentThread is [{}]",gson.toJson(parentInheritableThreadLocal.get()));
    	logger.info("value of parentTransmittableThreadLocal in parentThread is [{}]",gson.toJson(parentTransmittableThreadLocal.get()));
    	Map<String,Map<String,Student>> resultMap = Maps.newHashMap();
    	resultMap.put("parentThreadLocal", parentThreadLocal.get());
    	resultMap.put("parentInheritableThreadLocal", parentInheritableThreadLocal.get());
    	resultMap.put("parentTransmittableThreadLocal", parentTransmittableThreadLocal.get());
    	return resultMap;
    }
    
    private final class SubThread implements Callable<Void>{
    	private String identification;
    	
    	public SubThread(String identification){
    		this.identification = identification;
    	}

		@Override
		public Void call() throws Exception {
			logger.info("start to execute thread[{}]",identification);
			/***
			 * 输出的值为null，并不能直接使用父线程中定义在parentThreadLocal中的值
			 */
			logger.info("value of parentThreadLocal in subThread is [{}]",gson.toJson(parentThreadLocal.get()));
			logger.info("value of parentInheritableThreadLocal in subThread is [{}]",gson.toJson(parentInheritableThreadLocal.get()));
			logger.info("value of parentTransmittableThreadLocal in subThread is [{}]",gson.toJson(parentTransmittableThreadLocal.get()));
			Student subStudent = new Student();
			subStudent.setName(identification);
			if(parentThreadLocal.get() != null){
				parentThreadLocal.get().put(identification, subStudent);
			}
			parentInheritableThreadLocal.get().put(identification, subStudent);
			parentTransmittableThreadLocal.get().put(identification, subStudent);
			logger.info("end:value of parentThreadLocal in subThread is [{}]",gson.toJson(parentThreadLocal.get()));
			logger.info("end:value of parentInheritableThreadLocal in subThread is [{}]",gson.toJson(parentInheritableThreadLocal.get()));
			logger.info("end:value of parentTransmittableThreadLocal in subThread is [{}]",gson.toJson(parentTransmittableThreadLocal.get()));
			return null;
		}
    }
}
