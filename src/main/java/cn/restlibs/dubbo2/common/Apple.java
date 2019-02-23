package cn.restlibs.dubbo2.common;

import java.io.Serializable;

public class Apple implements Serializable {

	String name;
	double weight;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	
}
