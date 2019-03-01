package cn.restlibs.thread2;

public class Testt {



	public static void main(String[] args) {


		//模拟2个定时任务。一个定时任务里面是有线程池。
		//2个主线程并发。主线程里面有线程池并发的情况。
		Job j=new Job();
		for(int i=0;i<2;i++){



			Thread t=new Thread(j);
			t.start();

			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}



	}
}
