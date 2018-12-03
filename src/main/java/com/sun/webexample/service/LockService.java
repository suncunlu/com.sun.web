package com.sun.webexample.service;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.sun.webexample.entity.locks.BoundedBuffer;

@Service
public class LockService {
	private static Integer acc=0;
	final ReentrantLock lock = new ReentrantLock();
	private Logger logger = LoggerFactory.getLogger(getClass());
	private BoundedBuffer queue= new BoundedBuffer();
	
	
	public List<String> lockqueue(){
		
		
		try {
			Object take = queue.take();
			System.out.println(take);
		} catch (InterruptedException e) {
			logger.error(e.toString());
		}
		return Lists.newArrayList();
	} 
	
	public List<String> lockqueue2(){
		
		try {
			Object object = new Object();
			queue.put(object);
			System.out.println(object);
		} catch (InterruptedException e) {
			logger.error(e.toString());
		}
		
		return Lists.newArrayList();
	} 
	
	public List<String> lockIn(){
		
		//long id = Thread.currentThread().getId();
		//logger.info("id:{}",id);
		lock.lock();
		try {
			++acc;
			handle(acc);
			logger.info("acc:{},HoldCount:{}",acc,lock.getHoldCount());
		} finally {
			// TODO: handle finally clause
			lock.unlock();
		}

		
		return Lists.newArrayList();
	}
	private void handle(Integer acc2) {
		// TODO Auto-generated method stub
		lock.lock();
		try {
			--acc;
			logger.info("acc:{},HoldCount:{}",acc,lock.getHoldCount());
		} finally {
			// TODO: handle finally clause
			lock.unlock();
		}

	}
	public List<String> lockIn2() {
		//定义2个锁对象
        final Object o1 = new Object();
        final Object o2 = new Object();
        
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (o1) {//获取o1的锁
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o2) {//获取o2的锁

                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (o2) {//获取o2的锁
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (o1) {//获取o1的锁

                    }
                }
            }
        });
        //启动
        t1.start();
        t2.start();
		return null;
	}
	

}
