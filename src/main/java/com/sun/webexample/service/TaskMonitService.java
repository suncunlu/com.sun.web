/*package com.sun.webexample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskMonitService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	// 总任务状态缓存表
	private List<TaskStatusBean> totalStatus = new ArrayList<TaskStatusBean>(); // 审核柜员任务处理缓存表
	private Map<String, UserTaskCountDto> userTaskMap = new ConcurrentHashMap<String, UserTaskCountDto>(); // 监控工作线程引用
	private static MonitorWorkThread workThread = null;

	@PostConstruct
	private void initalizal() { // 实例化之后执行的初始化动作，用于启动值守监控线程来刷新加载数据
		workThread = new MonitorWorkThread();
		workThread.setDaemon(true);
		workThread.setName("AuthTaskMonitor");
		workThread.start();
	}

	*//**
	 * 此为对外提供方法用于外部根据监控用户号获取内存中缓存的监控数据
	 * 
	 * @param userno 监控用户号
	 * @return map key1:totalStatus key2:userno
	 * @throws Exception
	 *//*
	public Map<String, Object> monitorDataByUser(String userno) throws Exception {
		if (null == userno || "".equals(userno)) {
			return null;
		}
		Map<String, Object> retMap = new HashMap<String, Object>();
		if (null != workThread) { // 每次请求都去看看异步值守线程是否需求唤醒
			workThread.unPack();
		}
		retMap.put("totalStatus", totalStatus);
		if (userTaskMap.containsKey(userno)) {
			retMap.put(userno, userTaskMap.get(userno));
		} else {
			UserTaskCountDto dto = new UserTaskCountDto(userno);
			retMap.put(userno, dto);
		}
		return retMap;
	}

	*//**
	 * 从数据库中加载内存数据至内存
	 *//*
	private void loadDataFromDB() throws Exception {

		logger.info("开始从数据库中加载任务监控数据..."); // do something about business....

		logger.info("从数据库中加载任务监控数据完毕...");
	}

	*//**
	 * 清理监控缓存数据map
	 *//*
	public void clearMonitorCache() {
		this.totalStatus.clear();
		this.userTaskMap.clear();
	}

}
*/