package com.sun.webexample.service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MonitorWorkThread extends Thread {
	private Logger logger = LoggerFactory.getLogger(getClass());
	//当前线程停车标志
	private volatile boolean isPark = false;
	// 工作线程默认一秒钟加载一次,count即为工作监控线程每一次unpack之后会继续工作的时间，此值可根据实际需求配置化
	private int maxWorkCount = 300;

	@Override
	public void run() {
		int indexCount = 0;
		logger.info("成功启动审核任务监控工作线程，当前工作线程每次unpack连续工作的时间设定为" + maxWorkCount + "秒");
		while (true) {
			if (indexCount >= maxWorkCount) {
				logger.info("当前监控工作线程已到达连续工作时间设定上限，现在进入pack休眠状态");
				isPark = true;
				indexCount = 0;
				LockSupport.park();
			} // 从数据库中加载数据至内存
			try {
				loadDataFromDB();
			} catch (Exception e1) {
				logger.warn("从数据库中加载监控数据至内存发生异常", e1);
			}
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				logger.warn("工作线程被异常中断唤醒", e);
			}
			indexCount++;
		}
	}

	private void loadDataFromDB() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 假如当前线程正在运行状态，donothing
	 */
	public void unPack() {
		if (isPark) { // 唤醒当前监控工作线程，此处有并发唤醒动作需加锁
			synchronized (this) {
				isPark = false;
				LockSupport.unpark(this);
				logger.info("当前监控工作线程已被唤醒");
			}
		}
	}

}
