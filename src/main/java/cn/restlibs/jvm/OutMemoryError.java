package cn.restlibs.jvm;

public class OutMemoryError {


    // gc 回收不了 堆导致jvm奔溃
//-XX:+HeapDumpOnOutOfMemoryError -Xms20m -Xmx20m  java.lang.OutOfMemoryError: Java heap space

/*   public static void main(String[] args) {
        List li=new ArrayList<>();       //gc 回收不了  是gc root
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


    //gc 可以回收
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

}
