package cn.restlibs.jvm;

public class Testt {

    //堆导致jvm奔溃
//-XX:+HeapDumpOnOutOfMemoryError -Xms20m -Xmx20m  java.lang.OutOfMemoryError: Java heap space

/*   public static void main(String[] args) {
        List li=new ArrayList<>();
        while(true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UserVO uservo=new UserVO();
            uservo.setNickName("nervermore");
            li.add(uservo);
        }
    }*/


    //gc
  /*  public static void main(String[] args) {
        while(true){ //重用
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            UserVO uservo=new UserVO();
            uservo.setNickName("nervermore");
        }
    }*/

  //-Xss256k   java.lang.StackOverflowError
//StackOverflowError 影响不大
   private int stackLength = 1;
    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        Testt oom = new Testt();
      //  System.out.print(System.getProperty(Globals.CATALINA_HOME_PROP));
        System.out.println(System.getProperty("java.class.path"));
        //  System.out.println(System.getProperty("java.ext.dirs"));
      //  System.out.println(System.getProperty("java.class.path"));

        ClassLoader cl = Testt.class.getClassLoader();

        System.out.println("ClassLoader is:"+cl.toString());
       /* try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }*/
    }


    //栈导致服务器奔溃。
    //进程一般2g -堆xms-永久代maxpermsize=栈
    // -XX:+HeapDumpOnOutOfMemoryError -Xms5m -Xmx5m -Xss500M -Xss2M （这时候不妨设大些）
    //java.lang.OutOfMemoryError: Java heap space  堆设置太小。先报堆了。
    // -Xss500M  堆不设置。线程太多。线程都阻塞。不能开新的线程。程序假死。 java.lang.OutOfMemoryError: unable to create new native thread
    //  Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread 49441
  /*  private void dontStop() {
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
        Testt oom = new Testt();
        oom.stackLeakByThread();
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
   /* public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(BannerController.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj, args);
                }
            });
            enhancer.create();
        }
    }*/

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
