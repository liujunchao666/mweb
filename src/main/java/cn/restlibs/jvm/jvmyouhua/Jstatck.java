package cn.restlibs.jvm.jvmyouhua;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Jstatck {


    //堆导致jvm奔溃
//-XX:+HeapDumpOnOutOfMemoryError -Xms20m -Xmx20m  java.lang.OutOfMemoryError: Java heap space
    //jstack -l 15312 打印锁的信息
   /* public static void main(String[] args) {
        List li=new ArrayList();
        int i=0;
        while(true){
            i++;
            try {
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(" " +i);
            li.add(new byte[1024*1024]);
        }
    }




    "main" #1 prio=5 os_prio=0 tid=0x0000000002d95800 nid=0x3158 waiting on condition [0x000000000286f000]
    java.lang.Thread.State: TIMED_WAITING (sleeping)     //等待状态 sleeping
    at java.lang.Thread.sleep(Native Method)
    at cn.restlibs.jvm.Jstat.main(Jstat.java:18)  //再18行等待

    Locked ownable synchronizers:    //没有锁
            - None
*/


/*
    public static void main(String[] args) {
        List li=new ArrayList();
        int i=0;
        while(true){
            i++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(" " +i);
            li.add(new byte[1024*1024]);
        }
    }




    "main" #1 prio=5 os_prio=0 tid=0x0000000003695800 nid=0xa18 at breakpoint[0x000000000358f000]
    java.lang.Thread.State: RUNNABLE                     //运行状态
    at cn.restlibs.jvm.Jstat.main(Jstat.java:55)       //正在再第55行运行。

    Locked ownable synchronizers:      //没有锁
            - None
*/

/*

    public static void main(String[] args) {

            ServerSocket serverSocket = null;
            Socket client = null;
            try {
                serverSocket = new ServerSocket(888);

                client = serverSocket.accept();  //阻塞
                System.out.println("server socket is start……");

            } catch (Exception e) {

            }


    }



"main" #1 prio=5 os_prio=0 tid=0x00000000033c5000 nid=0x2c38 runnable [0x0000000002e3f000]
   java.lang.Thread.State: RUNNABLE
        at java.net.DualStackPlainSocketImpl.accept0(Native Method)
        at java.net.DualStackPlainSocketImpl.socketAccept(DualStackPlainSocketImpl.java:131)
        at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:409)
        at java.net.PlainSocketImpl.accept(PlainSocketImpl.java:199)
        - locked <0x00000000d6044a28> (a java.net.SocksSocketImpl)  //锁
        at java.net.ServerSocket.implAccept(ServerSocket.java:545)
        at java.net.ServerSocket.accept(ServerSocket.java:513)
        at cn.restlibs.jvm.Jstatck.main(Jstatck.java:82)     //82行

   Locked ownable synchronizers:
        - None


*/

/*

    public static void main(String[] args) {

        final Tickt1 t=new Tickt1();

        Thread t1 =new Thread(new Runnable() {
            public void run() {
                while (true){
                    t.maipiao();
                }

            }
        });
        Thread t2 =new Thread(new Runnable() {
            public void run() {
                while (true){
                    t.maipiao();
                }
            }
        });

        Thread t3 =new Thread(new Runnable() {
            public void run() {
                while (true){
                    t.maipiao();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();


        ServerSocket serverSocket = null;
        Socket client = null;
        try {
            serverSocket = new ServerSocket(888);

            client = serverSocket.accept();  //阻塞
            System.out.println("server socket is start……");

        } catch (Exception e) {

        }

    }



    "Thread-2" #16 prio=5 os_prio=0 tid=0x000000001aef3000 nid=0xc7c waiting for monitor entry [0x000000001baae000]
    java.lang.Thread.State: BLOCKED (on object monitor)         //阻塞
    at cn.restlibs.jvm.Tickt.maipiao(Tickt.java:10)
            - waiting to lock <0x00000000d6181dc8> (a cn.restlibs.jvm.Tickt) //等待锁对象
    at cn.restlibs.jvm.Jstatck$3.run(Jstatck.java:137)
    at java.lang.Thread.run(Thread.java:748)

    Locked ownable synchronizers:
            - None

"Thread-1" #15 prio=5 os_prio=0 tid=0x000000001aef2800 nid=0xf14 waiting on condition [0x000000001b9af000]
    java.lang.Thread.State: TIMED_WAITING (sleeping)     //等待  sleeping
    at java.lang.Thread.sleep(Native Method)
    at cn.restlibs.jvm.Tickt.maipiao(Tickt.java:10)
            - locked <0x00000000d6181dc8> (a cn.restlibs.jvm.Tickt)   锁住了被 Tickt对象
    at cn.restlibs.jvm.Jstatck$2.run(Jstatck.java:129)
    at java.lang.Thread.run(Thread.java:748)

    Locked ownable synchronizers:
            - None

"Thread-0" #14 prio=5 os_prio=0 tid=0x000000001aef1800 nid=0x3a8 waiting for monitor entry [0x000000001b8af000]
    java.lang.Thread.State: BLOCKED (on object monitor)   //阻塞
    at cn.restlibs.jvm.Tickt.maipiao(Tickt.java:10)
            - waiting to lock <0x00000000d6181dc8> (a cn.restlibs.jvm.Tickt)  //等待锁对象
    at cn.restlibs.jvm.Jstatck$1.run(Jstatck.java:121)
    at java.lang.Thread.run(Thread.java:748)

    Locked ownable synchronizers:
            - None
*/
  /*
    public static void main(String[] args) {

        final Tickt t=new Tickt();

        Thread t1 =new Thread(new Runnable() {
            public void run() {
                while (true){
                    t.maipiao();
                }

            }
        });
        Thread t2 =new Thread(new Runnable() {
            public void run() {
                while (true){
                    t.jiapiao();
                }
            }
        });


        t1.start();
        t2.start();


        ServerSocket serverSocket = null;
        Socket client = null;
        try {
            serverSocket = new ServerSocket(888);

            client = serverSocket.accept();  //阻塞
            System.out.println("server socket is start……");

        } catch (Exception e) {

        }

    }




"Thread-1" #13 prio=5 os_prio=0 tid=0x000000001a9ca000 nid=0x29b8 waiting on condition [0x000000001b38e000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(Native Method)
        at cn.restlibs.jvm.Tickt.jiapiao(Tickt.java:51)
        - locked <0x00000000d60464a8> (a java.lang.Object)
        at cn.restlibs.jvm.Jstatck$2.run(Jstatck.java:209)
        at java.lang.Thread.run(Thread.java:748)

   Locked ownable synchronizers:
        - None

"Thread-0" #12 prio=5 os_prio=0 tid=0x000000001a8dd800 nid=0x2728 in Object.wait() [0x000000001b28f000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x00000000d60464a8> (a java.lang.Object)
        at java.lang.Object.wait(Object.java:502)
        at cn.restlibs.jvm.Tickt.maipiao(Tickt.java:29)
        - locked <0x00000000d60464a8> (a java.lang.Object)
        at cn.restlibs.jvm.Jstatck$1.run(Jstatck.java:201)
        at java.lang.Thread.run(Thread.java:748)

   Locked ownable synchronizers:
        - None


*/



/*
    public static void main(String[] args) {
        final Outputter1 output = new Outputter1();
        new Thread() {
            public void run() {
                output.output("zhangsan");
            };
        }.start();
        new Thread() {
            public void run() {
                output.output("lisi");
            };
        }.start();

        ServerSocket serverSocket = null;
        Socket client = null;
        try {
            serverSocket = new ServerSocket(888);
            client = serverSocket.accept();  //阻塞
            System.out.println("server socket is start……");

        } catch (Exception e) {

        }

    }


    "Thread-1" #13 prio=5 os_prio=0 tid=0x000000001ae97800 nid=0x2900 waiting on condition [0x000000001b85e000]
    java.lang.Thread.State: WAITING (parking)
    at sun.misc.Unsafe.park(Native Method)
            - parking to wait for  <0x00000000d604bfc0> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)  //等待lock锁
    at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
    at java.util.concurrent.locks.AbstractQueuedSynchronizer.parkAndCheckInterrupt(AbstractQueuedSynchronizer.java:836)
    at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquireQueued(AbstractQueuedSynchronizer.java:870)
    at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1199)
    at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:209)
    at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:285)
    at cn.restlibs.jvm.Outputter1.output(Outputter1.java:10)
    at cn.restlibs.jvm.Jstatck$2.run(Jstatck.java:275)

    Locked ownable synchronizers:
            - None

"Thread-0" #12 prio=5 os_prio=0 tid=0x000000001aea8000 nid=0x39fc waiting on condition [0x000000001b75e000]
    java.lang.Thread.State: TIMED_WAITING (sleeping)
    at java.lang.Thread.sleep(Native Method)
    at cn.restlibs.jvm.Outputter1.output(Outputter1.java:14)
    at cn.restlibs.jvm.Jstatck$1.run(Jstatck.java:270)

    Locked ownable synchronizers:                        //lock 锁
            - <0x00000000d604bfc0> (a java.util.concurrent.locks.ReentrantLock$NonfairSync)

    */


    public static void main(String[] args) {


        try{

            for(int i=0;i<100000;i++){
                System.out.println("qwe " +i+" ");

                try{
                    Socket socket =null;

                    socket = new Socket();
                    socket.connect(new InetSocketAddress("www.restlib.com", 8080),3000);

                    PrintWriter writer=new PrintWriter(socket.getOutputStream());
                    BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                }catch (Exception e){
                    System.out.println("qwe " +i+" "+e.getMessage());
                }
       /*     writer.println("客户端");
            writer.flush();

            System.out.println("客户端 ："+reader.readLine());

            reader.close();
            writer.close();
            socket.close();*/
            }

        }catch (Exception e){

            System.out.println("12345");
        }
    }

}
