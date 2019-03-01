package cn.restlibs.thread;

import java.text.DateFormat;

public class App {
	
    private   static  ThreadLocal<String>  th=new ThreadLocal<String>();
    
    public static  void set(String df){
    	th.set(df);
    }

    
    public static  String get(){
       return 	th.get();
    }

}
