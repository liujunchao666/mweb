package cn.restlibs.tomcat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 加入session  cookie  失效时间
 * 浏览器 服务器 struts hibernate Spring mybatis 多线程 io socket tcp/ip 集合 jvm 编码加密  jbpm webservice sql优化 分布式 
 * @author mmry
 *
 */
public class HttpServer {
	
	 private String projectName;//项目名字
	 private String context=""; //页面存放路径
     private	ExecutorService pool = Executors.newFixedThreadPool(50);//最多50个请求

     private static  Map<String,Session>  sessionMap=new HashMap<String,Session>();
     
     



	public Map<String, Session> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, Session> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public static void main(String[] args) {
		  HttpServer server = new HttpServer();  
		  server.setProjectName("dove");
		  
		  Runnable runnable = new Runnable() {  
	            public void run() {  
	                while (true) {  
	                    try {  
	                        Thread.sleep(1000);  
	                    } catch (InterruptedException e) {  
	                        e.printStackTrace();  
	                    }  
	                    
	                    for (Map.Entry<String, Session> entry : sessionMap.entrySet()) {
							
	                    	String key = entry.getKey();
	                    	Session session = entry.getValue();
	                    	if (System.currentTimeMillis()>=session.getEndDate()) {
	                    		sessionMap.remove(key);
	                    		System.out.println("remove session key "+key);
							}
	                    	
	                    	
						}
	                    
	                }  
	            }  
	        };  
	        Thread thread = new Thread(runnable);  
	        thread.start(); 
	        
		  server.start();  
		  
	
	       


      	
    
	}
	 
	 public  void start() {
			ServerSocket  server = null;  
			try {
			    String proPath= System.getProperty("user.dir");
		        context=proPath+"/WebContent";
				server=new ServerSocket(8090, 50); 
				while (true) {
					Socket 	client = server.accept();  
					Task task=new Task(client,this);
					pool.submit(task);
				}
				
			} catch (Exception e) {

				e.printStackTrace();
			}
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}



	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}



	public ExecutorService getPool() {
		return pool;
	}

	public void setPool(ExecutorService pool) {
		this.pool = pool;
	}
	 
 /*
	F:\workspace\javafx/WebContent
	GET /dovepay/index.html HTTP/1.1
	GET /dovepay/index.html HTTP/1.1
	Accept: text/html, application/xhtml+xml, image/jxr, *
	Accept-Language: zh-Hans-CN,zh-Hans;q=0.9,en-US;q=0.8,ja;q=0.6,zh-Hant-TW;q=0.5,zh-Hant;q=0.4,en-GB;q=0.3,en;q=0.1
	User-Agent: Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko
	Accept-Encoding: gzip, deflate
	DNT: 1
	Host: localhost:8090
	Connection: Keep-Alive
	Pragma: no-cache
	Cookie: JSESSIONID=2017020905244011

	 localhost:8090/dovepay/index.html
	是否连接 true false
	是否关闭输入流 false
	是否关闭输出流 false
	是否连接 true true
	GET http://127.0.0.1:8090/dovepay/index.html  HTTP/1.1
	GET http://127.0.0.1:8090/dovepay/index.html  HTTP/1.1
	Host:127.0.0.1:8090

	127.0.0.1:8090http://127.0.0.1:8090/dovepay/index.html
	是否连接 true false
	是否关闭输入流 false
	是否关闭输出流 false
	是否连接 true true
*/

}


