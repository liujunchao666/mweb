package cn.restlibs.tomcat;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;

public class Task implements Callable{

	private Socket 	client;
	private HttpServer server;
	public Task(Socket 	client,HttpServer server) {
		this.client=client;
		this.server=server;
	}
	@Override
	public Object call() throws Exception {
		client.setSoTimeout(1000*5);
		
	    InputStream input = null;  
        OutputStream output = null;  

        input=client.getInputStream();
        output=client.getOutputStream();
        
      
        System.out.println(client.getLocalPort());
        System.out.println(client.getLocalAddress());
        System.out.println(client.getRemoteSocketAddress());

        Request request = new Request(input,server);  
        request.analyze(); //解析
            
        
        
        //client.shutdownInput();
        Response response = new Response(output,request);  
        response.handle(request,server); //处理请求
        
    	
	    System.out.println("是否邦定   "+client.isBound()); // 是否邦定  
        System.out.println("是否关闭   "+client.isClosed()); // 是否关闭  
        System.out.println("是否连接   "+client.isConnected()); // 是否连接  
        System.out.println("是否关闭输入流   "+client.isInputShutdown()); // 是否关闭输入流  
        System.out.println(" 是否关闭输出流   "+client.isOutputShutdown()); // 是否关闭输出流  
        
        
        /*System.out.println("是否连接 "+client.isConnected()+" " +client.isClosed()); // 是否连接  
        System.out.println("是否关闭输入流 "+client.isInputShutdown()); // 是否关闭输入流  
        System.out.println("是否关闭输出流 "+client.isOutputShutdown()); // 是否关闭输出流  

        if (client!=null) {
		    client.close();
		}
        System.out.println("是否连接 "+client.isConnected()+" "+client.isClosed()); // 是否连接  
*/		return null;
	}
	
	
	

}
