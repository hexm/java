package com.icbc.hexm.swu.bean;

public class Student {
	public long id;
	public String name;
	public String phone;
	public String college;
	public String company;
	public String updateTime;
	
	public Student(String string, String string2) {
		this.name = string;
		this.phone = string2;
	}
	
	public Student() {
		// TODO 自动生成的构造函数存根
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}


}
