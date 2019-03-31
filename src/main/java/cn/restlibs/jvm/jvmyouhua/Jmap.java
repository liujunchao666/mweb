package cn.restlibs.jvm.jvmyouhua;

import java.util.ArrayList;
import java.util.List;

public class Jmap {



    //堆导致jvm奔溃
//-XX:+HeapDumpOnOutOfMemoryError -Xms20m -Xmx20m  java.lang.OutOfMemoryError: Java heap space
    // fullgc 了但是没有回收内存。一直涨
    //  jmap -heap 19476 实时查看 相当于  jstat -gc     jstat -gcutil

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
 /*
    Attaching to process ID 19476, please wait...
    Debugger attached successfully.
    Server compiler detected.
    JVM version is 25.161-b12

    using thread-local object allocation.
    Parallel GC with 8 thread(s)

    Heap Configuration:
    MinHeapFreeRatio         = 0
    MaxHeapFreeRatio         = 100
    MaxHeapSize              = 20971520 (20.0MB)
    NewSize                  = 6815744 (6.5MB)
    MaxNewSize               = 6815744 (6.5MB)
    OldSize                  = 14155776 (13.5MB)
    NewRatio                 = 2
    SurvivorRatio            = 8
    MetaspaceSize            = 21807104 (20.796875MB)
    CompressedClassSpaceSize = 1073741824 (1024.0MB)
    MaxMetaspaceSize         = 17592186044415 MB
            G1HeapRegionSize         = 0 (0.0MB)

    Heap Usage:
    PS Young Generation
    Eden Space:
    capacity = 5767168 (5.5MB)
    used     = 5166888 (4.927528381347656MB)
    free     = 600280 (0.5724716186523438MB)
            89.59142511541194% used
    From Space:
    capacity = 524288 (0.5MB)
    used     = 507920 (0.4843902587890625MB)
    free     = 16368 (0.0156097412109375MB)
            96.8780517578125% used
    To Space:
    capacity = 524288 (0.5MB)
    used     = 0 (0.0MB)
    free     = 524288 (0.5MB)
            0.0% used
    PS Old Generation
            capacity = 14155776 (13.5MB)
    used     = 7925752 (7.558586120605469MB)
    free     = 6230024 (5.941413879394531MB)
            55.98952681929977% used
*/


 //jmap -dump:format=b,file=d://dump.txt 80016

//jmap -dump:format=b,file=d://dump23.txt 80016
}
