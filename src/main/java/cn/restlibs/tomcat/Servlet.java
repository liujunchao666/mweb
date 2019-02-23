package cn.restlibs.tomcat;

import java.io.IOException;


public class Servlet {
	
	
	
	  protected void service(Request req, Response resp){
		  
		  doGet(req,resp);
		  
	  }
	

		protected void doGet(Request req, Response resp)	{
		
			System.out.println("no method");
		}
}
