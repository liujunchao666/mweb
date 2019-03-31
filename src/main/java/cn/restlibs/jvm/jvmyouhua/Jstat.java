package cn.restlibs.jvm.jvmyouhua;

import java.util.ArrayList;
import java.util.List;

public class Jstat {


    //堆导致jvm奔溃
//-XX:+HeapDumpOnOutOfMemoryError -Xms20m -Xmx20m  -XX:+PrintGCDetails   查看gc 情况    java.lang.OutOfMemoryError: Java heap space
    // fullgc 了但是没有回收内存。一直涨
    //
/*
    public static void main(String[] args) {
        List li=new ArrayList(); //gc root  回收不了 这不叫内存泄漏吧
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



    $ jstat -gc 15712

    幸存1   幸存2               伊甸园             老年代              元数据                           younggc  耗时     fullgc 耗时
    S0C    S1C    S0U    S1U      EC       EU        OC         OU       MC     MU    CCSC   CCSU        YGC     YGCT    FGC    FGCT     GCT
    512.0  512.0   0.0    0.0    5632.0   3295.4   13824.0    13374.7   4864.0 3240.0 512.0  347.7       3     0.009      1      0.009    0.018


正常情况
先 伊甸园  增加 减少 gc  折线图 频率密集
幸存1 增加减少  gc   折线图  频率不密集
老年代  增加减少  fullgc   折线图 频率不密集


异常情况
老年代  fullgc后只增加不减少。

soc*2+ EC +OC=20m
   单位kb
比例一直变


fgc回收不了问题

[Full GC (Ergonomics) [PSYoungGen: 496K->0K(6144K)] [ParOldGen: 13084K->13438K(13824K)] 13580K->13438K(19968K), [Metaspace: 3831K->3831K(1056768K)], 0.0107264 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]

                                     新生代6m                 老年代 13.5                           共19.5
[Full GC (Ergonomics) [PSYoungGen: 5416K->5131K(6144K)] [ParOldGen: 13744K->13744K(13824K)] 19161K->18876K(19968K), [Metaspace: 9283K->9283K(1058816K)], 0.0130600 secs] [Times: user=0.09 sys=0.03, real=0.01 secs]


*/

// -Xms20m -Xmx20m  -XX:+PrintGCDetails
/*
    public static void main(String[] args) {
        while(true){ //重用
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("nervermore");
            UserVO uservo=new UserVO();
            uservo.setNickName("nervermore");
            uservo.setBytes(new byte[100]);      //出了作用域 并且 伊甸园满了就 gc 回收了
            System.gc(); //最后不要使用
        }
    }



                                   新生代前   新生代后 新生代共   堆内存前 堆内存后 总共   耗时
   [GC (Allocation Failure) [PSYoungGen: 5632K->488K(6144K)]      5632K->1554K(19968K),    0.0033428 secs] [Times: user=0.13 sys=0.00, real=0.00 secs]

*/

/*    gc -heap

    gc日志*/

}
