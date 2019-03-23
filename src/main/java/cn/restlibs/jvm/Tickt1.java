package cn.restlibs.jvm;

public class Tickt1 {


    private int  num =20;

    public  synchronized void maipiao(){

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("synchronized 买票 "+ (num--));


    }


}
