package cn.restlibs.thread3;

public class RequestThread extends Thread{
	
	private ServletDo servletdo;
	private User uer;
	
	@Override
	public void run() {
		
		servletdo.show(uer);
	}
	public ServletDo getServletdo() {
		return servletdo;
	}
	public void setServletdo(ServletDo servletdo) {
		this.servletdo = servletdo;
	}
	public User getUer() {
		return uer;
	}
	public void setUer(User uer) {
		this.uer = uer;
	}
	public RequestThread(ServletDo servletdo, User uer) {
		super();
		this.servletdo = servletdo;
		this.uer = uer;
	}

	



}
