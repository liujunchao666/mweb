package cn.restlibs.tomcat;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class Response {

	


    private Session session;
    private  Request request;
	OutputStream  output;
	public Response(OutputStream output ) {
		this.output=output;
	}
	
	public Response(OutputStream output,Request request ) {
		this.output=output;
		this.request=request;
	}
	public void handle(Request request,HttpServer server) throws Exception {
		
//Set-Cookie: JSESSIONID=6CAFF17898E460632EA3377BE0E1B048; Path=/myweb/; HttpOnly

		String path = request.getPath();
		String[] splits = path.split("/");
		String projectname=splits[1];
		if (projectname.equals(server.getProjectName())) {
			if (path.endsWith(".html")) {
				
				path=splits[2];
			//	/dovePay/thirdSignAction.do
			  String resource= 	server.getContext()+"/"+path;
			  File file=new File(resource);
			
			  if (file.exists()) {
				 /* output.write(("HTTP/1.1 200 OK \r\n" +  
	                        "Content-Type: text/html; charset=utf-8 \r\n" +  
	                        "Set-Cookie: name1=zhangsan;  Path=/dovepay; \r\n"+
	                        "Set-Cookie: age=19;  Path=/dovepay; \r\n"+
	                        "Set-Cookie: sex=men;  Path=/dovepay123; \r\n"+
	                        "Set-Cookie: JSESSIONID=6CAFF17898E460632EA3377BE0E1B048;  Path=/dovepay123;   HttpOnly \r\n"+
	                        "Expires: Thu, 01 Dec 1994 16:00:00 GMT \r\n "+ 
	                        "Cache-Control: no-cache=\"set-cookie\" \r\n "+
	                        "Content-Length: "+file.length()+
	                        "\r\n"+"\r\n").getBytes());*/
				  
				  StringBuffer responseheader=new  StringBuffer();
				  responseheader.append("HTTP/1.1 200 OK \r\n");
				  responseheader.append("Content-Type: text/html; charset=utf-8 \r\n");
				   session = request.getSession();
				  if (session!=null&&(session.isIsnew()==true)) {
					  responseheader.append("Set-Cookie: JSESSIONID="+session.getId()+" \r\n");
				 }
				  responseheader.append("Content-Length: "+file.length()+   "\r\n"+"\r\n");

				 try {
					output.write(responseheader.toString() .getBytes());
				} catch (Exception e) {
					e.printStackTrace();
				}

			  InputStream input=new FileInputStream(resource);
			   byte[] buffer = new byte[1024];
		        int len = 0;

				while((len= input.read(buffer)) != -1){
					output.write(buffer, 0, len);
				}
				input.close();
			  }else {
				  output.write(("HTTP/1.1 404 OK \r\n" +  
	                      "Content-Type: text/html; charset=utf-8 \r\n" +  
	                      "Content-Length: 0\r\n"+"\r\n 找不到").getBytes());
			   }
			 
			}
		}else {
			
			  output.write(("HTTP/1.1 404 OK \r\n" +  
                      "Content-Type: text/html; charset=utf-8 \r\n" +  
                      "Content-Length: 0\r\n"+"\r\n 找不到").getBytes());
		}
		
		output.flush();
		output.close();  //流关闭。socket关闭了
		
	
	}
}
