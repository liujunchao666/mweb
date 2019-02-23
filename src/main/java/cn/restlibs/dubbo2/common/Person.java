package cn.restlibs.dubbo2.common;



import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {

	private static final long serialVersionUID = -1923645274767028479L;
	
	private String[] address;

	private Date brithday; 
	
	private boolean gender;
	
	private double height;
	
	private int id;
	
	private String name;
	
	private int phone;
	
	private float weight;

	
	public String[] getAddress() {
		return address;
	}

	public void setAddress(String[] address) {
		this.address = address;
	}


	public Date getBrithday() {
		return brithday;
	}

	public void setBrithday(Date brithday) {
		this.brithday = brithday;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}


}
