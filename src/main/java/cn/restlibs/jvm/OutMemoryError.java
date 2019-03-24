package cn.restlibs.jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.catalina.Globals;
import org.springframework.asm.commons.Method;

import java.util.ArrayList;
import java.util.List;

public class OutMemoryError {


    // gc 回收不了 堆导致jvm奔溃
//-XX:+HeapDumpOnOutOfMemoryError -Xms20m -Xmx20m  java.lang.OutOfMemoryError: Java heap space

 public static void main(String[] args) {
        List li=new ArrayList<UserVO>();       //gc 回收不了  是gc root
        while(true){
            try {
                Thread.sleep(500);
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
    /* private int stackLength = 1;
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
/*   private void dontStop() {
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
// -Xms20m -Xmx20m   -XX:MaxMetaspaceSize=30m -XX:+PrintGCDetails  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=E:\Java\dump
   /* public static void main(String[] args) {
        // 使用List保持着常量池引用，避免Full GC回收常量池行为

      String s1="helloword";
      String s2="helloword";
      String s3="helloword".intern();

      String s4=new String("helloword");
      String s5=new String("helloword").intern();

      System.out.println(s1==s2); //true
      System.out.println(s2==s3); //true
      System.out.println(s3==s4); //false
      System.out.println(s4==s5); //false
      System.out.println(s3==s5); //true

      System.out.println("----1----");
      String ss1=new String("hellojvm").intern();
      String ss2="hellojvm";
      System.out.println(ss1==ss2); //true

      System.out.println("----2----");
      String sss1=new String("hellojvm");
      String sss2="hellojvm";
      String sss3=new String("hellojvm").intern();

      System.out.println(sss1==sss2); //false
      System.out.println(sss2==sss3); //true



    List<String> list = new ArrayList<String>();
        // 10MB的PermSize在integer范围内足够产生OOM了
        int i = 0;
        while (true) {
            list.add(("hello jvm "+i++));
        }

    } */


/*
    Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    at java.util.Arrays.copyOf(Arrays.java:3210)
    at java.util.Arrays.copyOf(Arrays.java:3181)
    at java.util.ArrayList.grow(ArrayList.java:265)
    at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:239)
    at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:231)
    at java.util.ArrayList.add(ArrayList.java:462)
    at cn.restlibs.jvm.OutMemoryError.main(OutMemoryError.java:136)
    Heap dump file created [24463871 bytes in 0.101 secs]
    Heap
    PSYoungGen      total 6144K, used 5560K [0x00000000ff980000, 0x0000000100000000, 0x0000000100000000)
    eden space 5632K, 98% used [0x00000000ff980000,0x00000000ffeee208,0x00000000fff00000)
    from space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
    to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
    ParOldGen       total 13824K, used 13377K [0x00000000fec00000, 0x00000000ff980000, 0x00000000ff980000)
    object space 13824K, 96% used [0x00000000fec00000,0x00000000ff9104c0,0x00000000ff980000)
    Metaspace       used 3504K, capacity 4496K, committed 4864K, reserved 1056768K
    class space    used 379K, capacity 388K, committed 512K, reserved 1048576K
*/

    /*public static void main(String[] args) {

        String sss3=new String("hellojvm").intern();

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
*/

    //方法区。jdk8中元数据。没有永久代  -XX:MaxPermSize=5M  失效
   // -Xms20m -Xmx20m   -XX:MaxMetaspaceSize=24m -XX:+PrintGCDetails
    // -Xms20m -Xmx20m   -XX:MaxMetaspaceSize=30m -XX:+PrintGCDetails  -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=E:\Java\dump
/*   public static void main(String[] args) {
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

/*
    Metaspace       used 30145K, capacity 30292K, committed 30720K, reserved 1077248K    方法code
    class space    used 2646K, capacity 2701K, committed 2816K, reserved 1048576K         class用了2兆
    Caused by: java.lang.OutOfMemoryError: Metaspace
*/



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
