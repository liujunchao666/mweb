package cn.restlibs.jvm.outmemory1;

import cn.restlibs.jvm.UserVO;

import java.util.ArrayList;
import java.util.List;

public class OutMemoryError {


    // gc 回收不了 堆导致jvm奔溃
//-XX:+HeapDumpOnOutOfMemoryError -Xms20m -Xmx20m  java.lang.OutOfMemoryError: Java heap space

  public static void main(String[] args) {
        List li=new ArrayList<UserVO>();       //gc 回收不了  是gc root
        while(true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UserVO uservo=new UserVO();
            uservo.setNickName("nervermore");
            uservo.setBytes(new byte[1024*1024]);
            li.add(uservo);
        }
    }


    //gc 可以回收
/*
   public static void main(String[] args) {
        while(true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UserVO uservo=new UserVO();  // uservo是不是gc root 作用域内是gc root 作用域外不是
            uservo.setNickName("nervermore");  //出了作用域可以回收
            uservo.setBytes(new byte[1024*1024]);      //出了作用域 并且 伊甸园满了就 gc 回收了
        }
    }
*/









  //-Xss256k 影响不大
  /*   private int stackLength = 1;
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        OutMemoryError oom = new OutMemoryError();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }

   Exception in thread "main" java.lang.StackOverflowError
    stack length:19569  -Xss1m
    stack length:2486   -Xss256k
*/







    //栈导致服务器奔溃。
    //进程一般2g -堆xms-永久代maxpermsize=栈
    // -XX:+HeapDumpOnOutOfMemoryError -Xms5m -Xmx5m -Xss500M -Xss2M （这时候不妨设大些）
    //java.lang.OutOfMemoryError: Java heap space  堆设置太小。先报堆了。
    // -Xss500M  堆不设置。线程太多。线程都阻塞。不能开新的线程。程序假死。 java.lang.OutOfMemoryError: unable to create new native thread
    //  Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread 49441
    /*private void dontStop() {
        count++;
        System.out.println(count);
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    int  count=0;
    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) throws Throwable {
        OutMemoryError oom = new OutMemoryError();
        oom.stackLeakByThread();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }*/



    //   jdk8以前 方法区就是永久代。字符串放在永久代中
//    之后 方法区是元空间。不在jvm 字符串放在堆中 -XX:MaxPermSize=5M  失效

 /*   public static void main(String[] args) {
        // 使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<String>();
        // 10MB的PermSize在integer范围内足够产生OOM了
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++));
        }
    }*/


    //方法区。jdk8中元数据。没有永久代  -XX:MaxPermSize=5M  失效
   // -Xms20m -Xmx20m   -XX:MaxMetaspaceSize=24m -XX:+PrintGCDetails
/*    public static void main(String[] args) {
       int i=0;
        while (true) {
            i++;
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OutMemoryError.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object o, java.lang.reflect.Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper( o, objects);
                }

            });
            enhancer.create();
        }


    }*/


    //Caused by: java.lang.OutOfMemoryError: Metaspace



/*
   private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
*/
}
