package cn.restlibs.jvm;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class Tickt {


    private int  num =20;
    private Object o   =null;
    private Object lock   =new Object();

    public   void maipiao(){


            synchronized (lock){

                if(num>0){  //控制条件必须在同步快内。不然多线程有问题。判断不准确
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("买票 "+ (num--));
                    return;
                }


                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("synchronized 买票 "+ (num--));

            }


    }

    public   void jiapiao(){

        synchronized(lock){
            if(num==0){  //控制条件必须在同步快内。不然多线程有问题。判断不准确
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                num=num+2;
                System.out.println("加票 "+ (num));
                lock.notifyAll();
            }
        }

    }
}
