package com.sun.webexample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class ReentrantReadWriteLockService {
	private String acc = "0";
	private Logger logger = LoggerFactory.getLogger(getClass());
	final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
	
	public List<String> read() {
		ArrayList<String> list = Lists.newArrayList();
		
		logger.info("开始读取。。。。。。。");
		reentrantReadWriteLock.readLock().lock();
		
		logger.info("读操作 read... ReadHoldCount:{},WriteHoldCount{}",reentrantReadWriteLock.getReadHoldCount(),reentrantReadWriteLock.getWriteHoldCount());
		try {
			list.add(acc);
		} finally {
			reentrantReadWriteLock.readLock().unlock();
		}
		logger.info("读取结束。。。。。。。");
		return list;
	}
	
	public List<String> write() {
		ArrayList<String> list = Lists.newArrayList();
		logger.info("开始写入。。。。。。。");
		reentrantReadWriteLock.writeLock().lock();
		
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("写操作 write... ReadHoldCount:{},WriteHoldCount{}",reentrantReadWriteLock.getReadHoldCount(),reentrantReadWriteLock.getWriteHoldCount());
		try {
			acc="1";
			list.add(acc);
		} finally {
			reentrantReadWriteLock.writeLock().unlock();
		}
		acc="0";
		logger.info("写入结束。。。。。。。");
		return list;
	}
	
	

}
