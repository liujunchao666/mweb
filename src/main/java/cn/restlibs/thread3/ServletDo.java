package cn.restlibs.thread3;

public class ServletDo {  //过滤器或者servlet  单例

	private User teacher=new User("张老师",40);  //单例的属性也是单例的



	public void show(User u) {  //u  相当于request  多例

		System.out.println("多线程    使用单例方法的     单例属性   "+teacher);

		System.out.println("多线程    使用单例方法的   下面的多例   "+ u);

		User user = new User(u.getName()+"代理", u.getAge());     //action 值栈  actionContext

		System.out.println(user);

		for (int i = 0; i < 10; i++) {

			System.out.println(Thread.currentThread().getName()+i);
		}



	}






}
