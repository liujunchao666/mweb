package cn.restlibs.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ThreadA  extends Thread {
	
	private String s;
	public ThreadA(String sss) {
		s=sss;
	}
	
	@Override
	public void run() {
		
		Ttt sdf=     new Ttt();
        App.set(s);
        System.out.println(Thread.currentThread().getName()+"begin:"+App.get());

        try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        System.out.println(Thread.currentThread().getName()+"end:"+App.get());

        
        
		
	}
	

}
