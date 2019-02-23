package cn.restlibs.tomcat;

import java.util.HashMap;
import java.util.Map;

public class Session {

	private long maxTime;
	private boolean isnew;
	private String id;
	private long createDate;
	private long endDate;

	private Map<String,Object>  propertyMap=new HashMap<String,Object>(); //session 值
	public long getMaxTime() {
		return maxTime;
	}
	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Map<String, Object> getPropertyMap() {
		return propertyMap;
	}
	public void setPropertyMap(Map<String, Object> propertyMap) {
		this.propertyMap = propertyMap;
	}
	public Session( String id) {
		this.id = id;
	}
	
	
	public boolean isIsnew() {
		return isnew;
	}
	public void setIsnew(boolean isnew) {
		this.isnew = isnew;
	}
	public Session() {
	}
	public long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(long createDate) {
		this.createDate = createDate;
	}
	public long getEndDate() {
		return endDate;
	}
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	
	
}
