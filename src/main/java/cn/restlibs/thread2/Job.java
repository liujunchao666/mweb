package cn.restlibs.thread2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Job  extends Thread  {

	private static ExecutorService executor = Executors.newFixedThreadPool(2);


	@Override
	public void run() {

		System.out.println(this.currentThread().getName()+" " );
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			executor.submit(new Myrun(this.currentThread().getName()+" 任务 " +i));

		}


	}

 /*
	Thread-1
	Thread-1 任务 0 执行 pool-1-thread-1
	Thread-1 任务 1 执行 pool-1-thread-2
	Thread-2
	Thread-1 任务 2 执行 pool-1-thread-1
	Thread-1 任务 3 执行 pool-1-thread-2
	Thread-1 任务 4 执行 pool-1-thread-1
	Thread-2 任务 0 执行 pool-1-thread-2
	Thread-1 任务 5 执行 pool-1-thread-1
	Thread-2 任务 1 执行 pool-1-thread-2
	Thread-1 任务 6 执行 pool-1-thread-1
	Thread-2 任务 2 执行 pool-1-thread-2
	Thread-1 任务 7 执行 pool-1-thread-1
	Thread-2 任务 3 执行 pool-1-thread-2
	Thread-1 任务 8 执行 pool-1-thread-1
	Thread-2 任务 4 执行 pool-1-thread-2
	Thread-1 任务 9 执行 pool-1-thread-1
	Thread-2 任务 5 执行 pool-1-thread-2
	Thread-2 任务 6 执行 pool-1-thread-1
	Thread-2 任务 7 执行 pool-1-thread-2
	Thread-2 任务 8 执行 pool-1-thread-1
	Thread-2 任务 9 执行 pool-1-thread-2*/

}
