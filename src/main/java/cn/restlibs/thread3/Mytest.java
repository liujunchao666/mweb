package cn.restlibs.thread3;
import java.io.IOException;
import java.io.InputStream;

public class Mytest {

	/* 反射找到action，创建单例或多例。把参数赋值给单例属性。调用对应方法。把多例request传给方法。把request封装到actioncontext中
        servlet单例       方法接受参数
        struts1单例       方法接受参数
        struts2多例         action属性接受参数
        springmvc单例   方法接受参数
    */
	public static void main(String[] args) throws InterruptedException, IOException {

		ServletDo servletdo=new ServletDo();         //过滤器或者servlet 配置文件 调度器


		User u1=new User("陆小秀",18);    //相当于request
		RequestThread rt1=new RequestThread(servletdo,u1); //servletdo 单例 u1多例
		rt1.setName("陆小秀请求  ");



		User u2=new User("刘俊朝",20);  //相当于request
		RequestThread rt2=new RequestThread(servletdo,u2);
		rt2.setName("刘俊朝请求   ");

		rt2.start();
		rt1.start();


	/*	Mythread2 my3=new Mythread2();
		Thread th=new Thread(my3);
		th.start();

		Thread th2=new Thread(my3);
		th2.start();*/

	/*	Printer pr=new  Printer();
		ThreadA  tha=new ThreadA(pr);
		ThreadB  thb=new ThreadB(pr);
		tha.start();
		thb.start();
		*/

		//	 thread();


	/*
		ping 192.168.33.163
		Mythread2 mt = new Mythread2() ;	 // 实例化对象
		new Thread(mt,"孙悟空").start() ;
		new Thread(mt,"猪八戒").start() ;
		new Thread(mt,"唐僧").start() ;
		mt.run();*/

	}

	public static void setdaem() throws InterruptedException {
		Thread th=	new Thread("张飞"){
			public void run() {
				for (int i = 0; i <= 1000; i++) {
					System.out.println(Thread.currentThread().getName()+i);
				}
			}

		};

		th.setDaemon(true);

		new Thread("刘备"){
			public void run() {
				for (int i = 0; i <= 10; i++) {
					System.out.println(Thread.currentThread().getName()+i);
				}
			}

		}.start();

		th.join(1000);
		th.start();
	}

	private static void thread() {

	/*	ServletDo servletdo=new ServletDo();

		RequestThread my1=new RequestThread();
		my1.setServletdo(servletdo);
		my1.setName("线程1   ");
		my1.start();


		RequestThread my2=new RequestThread();
		my2.setName("线程2  ");
		my2.setServletdo(servletdo);
		my2.start();
	*/
		
		/*Mythread2 my3=new Mythread2();
		Thread th=new Thread(my3);
		th.start();
		
		for (int i = 0; i < 1000; i++) {
			System.out.println("mian  "+i);
		
		}
	*/
	}

}
