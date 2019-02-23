package cn.restlibs.dubbo.common;

import java.io.Serializable;
import java.lang.reflect.Method;

public class Callback  implements Serializable {
	
	//调用前后。都传送这个对象。
	String uuidString; //每一次调用的id。唯一
	String interfaceName; //接口名字
	String method; //调用方法
	Object[] args; //调用参数
	Object resultObject; //调用返回结果
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
	public String getUuidString() {
		return uuidString;
	}
	public void setUuidString(String uuidString) {
		this.uuidString = uuidString;
	}
	public Object getResultObject() {
		return resultObject;
	}
	public void setResultObject(Object resultObject) {
		this.resultObject = resultObject;
	}

	
	
	
}
