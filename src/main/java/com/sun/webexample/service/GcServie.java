package com.sun.webexample.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class GcServie {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final int _1MB=1024*1024;
	public List<String> on() {
		// TODO Auto-generated method stub
		logger.info("on.............");
		System.gc();
		return Lists.newArrayList();
	}
	public List<String> serialAndSerialOld() {
		// TODO Auto-generated method stub
		return null;
	}
	public List<String> config() throws InterruptedException {
		// TODO Auto-generated method stub
		//ArrayList<byte[]> list = Lists.newArrayList();
		byte[] allocationAray1;
		//allocationAray2,allocationAray3,allocationAray4,allocationAray5,allocationAray6;
			allocationAray1 = new byte[1* _1MB];
			
		return null;
	}

}
