package cn.restlibs.jvm;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Outputter1 {
    private Lock lock = new ReentrantLock();// 锁对象
    public void output(String name) {
        // TODO 线程输出方法
        lock.lock();// 得到锁
        try {
            for(int i = 0; i < name.length(); i++) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(name.charAt(i));
            }
        } finally {
            lock.unlock();// 释放锁
        }
    }
}
