package cn.restlibs.thread2;

public class Myrun implements Runnable{
	
	int i;
	String s;
	public Myrun(String s) {
		this.s=s;
		
	}

	@Override
	public void run() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(s  +" ִ�� "+ Thread.currentThread().getName());
	}
	
	

}
