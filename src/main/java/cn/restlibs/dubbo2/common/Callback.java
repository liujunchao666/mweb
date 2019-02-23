package cn.restlibs.dubbo2.common;

import java.io.Serializable;
import java.lang.reflect.Method;

public class Callback  implements Serializable {
	
	String interfaceName;
	String method;
	Object[] args;
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}

	
	
	
}
