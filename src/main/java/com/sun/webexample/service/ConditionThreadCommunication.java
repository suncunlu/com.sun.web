package com.sun.webexample.service;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 线程间通信
 * 目标：实现三个线程顺序打印ABC
 * @author jh
 *
 */
public class ConditionThreadCommunication {
	final Lock lock = new ReentrantLock();
	
	private Condition conditionA=lock.newCondition();
	private Condition conditionB=lock.newCondition();
	private Condition conditionC=lock.newCondition();
	
	private String type="A";
	
	private void printA() {
		lock.lock();
		try {
			while(!Objects.equals("A", type)) {
				conditionA.await();
			}
			
			System.out.println(Thread.currentThread().getName()+" 正在打印A");
			type="B";
			conditionB.signal();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	private void printB() {
		lock.lock();
		try {
			while(!Objects.equals("B", type)) {
				conditionB.await();
			}
			
			System.out.println(Thread.currentThread().getName()+" 正在打印B");
			type="C";
			conditionC.signal();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	private void printC() {
		lock.lock();
		try {
			while(!Objects.equals("C", type)) {
				conditionC.await();
			}
			
			System.out.println(Thread.currentThread().getName()+" 正在打印C");
			type="A";
			conditionA.signal();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
	
	public static void main(String[] args) {
		final ConditionThreadCommunication conditionThreadCommunication = new ConditionThreadCommunication();
		
		
		Thread ta = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 10; i++) {
					conditionThreadCommunication.printA();
				}
			}
		});
		
		Thread tb = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 10; i++) {
					conditionThreadCommunication.printB();
				}
			}
		});
		
		Thread tc = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 10; i++) {
					conditionThreadCommunication.printC();
				}
			}
		});
		
		ta.start();
		tb.start();
		tc.start();
		
	}
}
