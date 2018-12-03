package com.sun.webexample.entity.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *ReentrantLock模拟 ArrayBlockingQueue
 *参考https://www.cnblogs.com/hongdada/p/6150699.html
 * @author jh
 *
 */
public class BoundedBuffer {
	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();
	
	final Object[] items = new Object[100];
	int putptr,takeptr,count;
	public void put(Object x) throws InterruptedException {
		lock.lock();
		try {
			System.out.println("-put----------");
			while(count==items.length) notFull.await();
			
			items[putptr] = x;
			
			if(++putptr==items.length) putptr=0;
			System.out.println("+put++++++++++");
			++count;
			
			notEmpty.signal();
			
		} finally {
			// TODO: handle finally clause
			lock.unlock();
		}
	}
	
	public Object take() throws InterruptedException {
		lock.lock();
		try {
			System.out.println("-take----------");
			while(count==0) notEmpty.await();
			Object x = items[takeptr];
			if(++takeptr==items.length) takeptr=0;
			System.out.println("+take++++++++++");
			--count;
			notFull.signal();
			return x;
		} finally {
			// TODO: handle finally clause
			lock.unlock();
		}
		
	}
}
