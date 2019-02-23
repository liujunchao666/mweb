package cn.restlibs.dubbo.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;






import com.caucho.hessian.io.Hessian2Output;
import cn.restlibs.dubbo.common.Apple;
import cn.restlibs.dubbo.common.ManagerService;
import cn.restlibs.dubbo.common.Person;


public class ManagerServiceImpl implements ManagerService {
	
	
	public String   get(){
		
		System.out.println("执行");
		return "wuwu";
	}

	
	@Override
	public Person say() {
		
	      /*  try {
	        	
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				Hessian2Output h2o = new Hessian2Output(os);
				h2o.startMessage();
				h2o.writeObject(getPerson());
				h2o.writeString("I am client.");
				h2o.completeMessage();
				h2o.close();
 
				byte[] buffer = os.toByteArray();
				os.close();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}*/
	        
		return getPerson();
	}
	
	public static void main(String[] args) {
		
		String paytype="x";
		if (paytype==null||"B".equals(paytype)||"B|".equals(paytype)||paytype==""||paytype=="DEFAULT"||paytype.trim().equals("")) { 
			paytype=""; //兼容以前不传 是为了不签名。以前没有签名
			System.out.println("11");
		}
	}

	  public  Person getPerson() {
	        Person person = new Person();
	        person.setAddress(new String[] { "Beijing", "TaiWan", "GuangZhou" });
	        person.setBrithday(new Date());
	        person.setGender(false);
	        person.setHeight(168.5D);
	        person.setId(300);
	        person.setName("Jack");
	        person.setPhone(188888888);
	        person.setWeight(55.2F);
	        return person;
	    }


	@Override
	public Person eat(Apple apple) {
	
		   Person person = new Person();
	        person.setAddress(new String[] { "Beijing", "TaiWan", "GuangZhou" });
	        person.setBrithday(new Date());
	        person.setGender(false);
	        person.setHeight(apple.getWeight());
	        person.setId(300);
	        person.setName(apple.getName());
	        person.setPhone(188888888);
	        person.setWeight(55.2F);
	        return person;
	}
	
}
