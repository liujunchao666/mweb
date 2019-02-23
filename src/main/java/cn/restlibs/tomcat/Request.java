package cn.restlibs.tomcat;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Request {
	private InputStream input;
    private String url;  
    private String accept;  
    private String accept_Language;  
    private String user_Agent;  
    private String host;  
    private String connection;  
    private String httpLine;  
    private String method;  
    private String path;  
    private String sessionId;
    
    private String cookieString;
    private Session session;
	private HttpServer server;
	private List<Cookie>  cookies=new ArrayList<Cookie>();
	Socket client;
	
	public Request(InputStream input) {
		this.input=input;
	}
	
	public Request(InputStream input,HttpServer server) {
		this.input=input;
		this.server=server;
	}
	
	public Request(Socket client,HttpServer server) {
		this.client=client;
		this.server=server;
	}

	//解析input请求
	public void analyze() {
		
		try {
		       // ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		      
		        //读取一次所有available 方法返回流的大小
		        /*	int available = input.available();
		        	System.out.println(available);
			        byte[] buffer = new byte[available];

			        int len;
				    len= input.read(buffer) ;
				    System.out.println(len);
				    outputStream.write(buffer, 0, len);*/
			
		        
		        //根据超时判断
		     /*   byte[] buffer = new byte[1024];
		        int len = 0;
		        try {
					while((len= input.read(buffer)) != -1){
					    outputStream.write(buffer, 0, len);
					    System.out.println(len);
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("读取超时，socket仍然有效");
				}*/
		        
		        //根据/r/n 行空行判断
	        /*	int len;
	        	List<Integer> list=new ArrayList<Integer>();
	        	int i=0;
				while((len= input.read()) != -1){
					char c=(char) len;
					System.out.println(len+"--"+c);
				    outputStream.write(len);
				    list.add(len);
				    if (i>=4) {
				    	 if (list.get(i)==10&&list.get(i-1)==13) {
				    		   System.out.println("换行");
						    	if (list.get(i-2)==10&&list.get(i-3)==13) {
						    		 System.out.println("空行 头结束了");
						    		 break;
								}
						    	
							}
					}				    
					i++;
				}
			*/
		        //读取所有字符串。
		        /*byte[] bytes = outputStream.toByteArray();
		        outputStream.close();

		        String requestString=new String(bytes);
		        String[] split = requestString.split("\r\n");  //根据行分割
		       
		        for (int j = 0; j < split.length; j++) {
		        	String string =split[j];
		        	if (j==0) {
		        		httpLine = string;
		        		method=string.split(" ")[0];
		        		path=string.split(" ")[1];
				        System.out.println(httpLine);
					}else if (string.contains("Accept")) {
		        		accept = string.split(":")[1];
					}else if (string.contains("Accept-Language")) {
						accept_Language = string.split(":")[1];
					}else if (string.contains("Host")) {
						host = string.split(":")[1]+":"+string.split(":")[2];
					}else if (string.contains("Connection")) {
						connection = string.split(":")[1];
					}
				}*/
	      
		        
		      BufferedReader reader = new BufferedReader(new InputStreamReader(input)) ;
		        
		      String string=null;
		      int j=0;
		      boolean body=false;
		      StringBuffer sbBody=new StringBuffer();
		      try {
				while ((string=reader.readLine())!=null) {
			        System.out.println("服务端接受客户端 line "+string);

			    	if (body) {
		     			sbBody.append(string);
		     			sbBody.append(System.getProperty("line.separator")); 
		     			continue;
					}
		     		
					if (string.equals("")) { //头读完 ??post没读完
						body=true;
						continue;
					}
					
				        if (j==0) {
				    		httpLine = string;
				    		method=string.split(" ")[0];
				    		path=string.split(" ")[1];
						}else if (string.contains("Accept")) {
				    		accept = string.split(":")[1];
						}else if (string.contains("Accept-Language")) {
							accept_Language = string.split(":")[1];
						}else if (string.contains("Host")) {
							host = string.split(":")[1]+":"+string.split(":")[2];
						}else if (string.contains("Connection")) {
							connection = string.split(":")[1];
						}else if (string.contains("Cookie")) {
							cookieString = string.split(":")[1];	
							
							String[] cookiess = cookieString.split(";");
							
							for (String string2 : cookiess) {
								String[] split = string2.split("=");
								Cookie cookie=new Cookie();
								cookie.setName(split[0].trim());
								cookie.setValue(split[1].trim());
								cookies.add(cookie);
								if (cookie.getName().equals("JSESSIONID")) {
									sessionId=cookie.getValue();
								}
							}
						}
				        j++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		    
		        url=host+path;
		   
		    	
			    //找session
		        if (sessionId!=null&&!"".equals(sessionId)) {
	        	    session = server.getSessionMap().get(sessionId);
	        	    if (session!=null) {
		        	    session.setIsnew(false);
					}else {
						sessionId=creatSessionId();
			            session =new Session(sessionId);
			            session.setIsnew(true);
			            long l1=System.currentTimeMillis();
			            session.setCreateDate(l1);
			            long l2=  l1+120*1000;
			            session.setEndDate(l2);
			            server.getSessionMap().put(sessionId, session);
					}
				}else { //创建session
					sessionId=creatSessionId();
		            session =new Session(sessionId);
		            session.setIsnew(true);
		            long l1=System.currentTimeMillis();
		            session.setCreateDate(l1);
		            long l2=  l1+60*1000;
		            session.setEndDate(l2);
		            server.getSessionMap().put(sessionId, session);
				}
		      
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	public InputStream getInput() {
		return input;
	}

	public void setInput(InputStream input) {
		this.input = input;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getAccept_Language() {
		return accept_Language;
	}

	public void setAccept_Language(String accept_Language) {
		this.accept_Language = accept_Language;
	}

	public String getUser_Agent() {
		return user_Agent;
	}

	public void setUser_Agent(String user_Agent) {
		this.user_Agent = user_Agent;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getConnection() {
		return connection;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public String getHttpLine() {
		return httpLine;
	}

	public void setHttpLine(String httpLine) {
		this.httpLine = httpLine;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Session getSession() { //根据sessionId获取session 或创建session
		return session;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSession(Session session) {
		this.session = session;
	}


	
    private String  creatSessionId(){
		
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhh24mmss");
		String format = sdf.format(d);
		return format;
	}
	

}
