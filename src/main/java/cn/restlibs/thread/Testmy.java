package cn.restlibs.thread;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Testmy {


    public static void main(String[] args) {
    	
    
    	for(int i=0;i<10;i++){
    		ThreadA a=new ThreadA("����"+i);
        	Thread t=new Thread(a);
        	t.start();
    	}
   
    	 try {
 			Thread.sleep(200000);
 		} catch (InterruptedException e) {
 			e.printStackTrace();
 		}

    }


}
