package cn.restlibs.dubbo.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

import cn.restlibs.dubbo.common.Callback;
import cn.restlibs.dubbo.common.ManagerService;
import cn.restlibs.dubbo.common.ManagerServiceImpl;

public class TestProxhy {
	
	
	public static void main(String[] args) {
		
		final ManagerService service=new ManagerServiceImpl();

			 Object obj = Proxy.newProxyInstance(Projectweb1.class.getClassLoader(), new Class[]{ManagerService.class}, new InvocationHandler() {
				@Override
				public Object invoke(Object proxy, Method method, Object[] args)
						throws Throwable {
				
					System.out.println("前");
				//	Object obj = method.invoke(service, args);    
					System.out.println("后");
					return "12";
				}
			});
			
			 ManagerService serv= (ManagerService) obj;
			 
				final ManagerService service2=new ManagerServiceImpl();

			 Object obj3 = Proxy.newProxyInstance(Projectweb1.class.getClassLoader(), new Class[]{ManagerService.class}, new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
					
						System.out.println("前");
					//	Object obj = method.invoke(service2, args);    
						System.out.println("后");
						return "23";
					}
				});
				
			 ManagerService serv3= (ManagerService) obj3;
				 
			 //serv3 serv是否同一个和 method.invoke(service2, args); 参数是否同一个有关系。
			System.out.println(obj);
			System.out.println(obj3);

	}

}
