package com.ye.volleydemo.bean;

public class RSubCategory {

	private long id;//2�������id
	private String name;//2�����������
	private String thirdCategory;//3�������������� JSON
	
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
	public String getThirdCategory() {
		return thirdCategory;
	}
	public void setThirdCategory(String thirdCategory) {
		this.thirdCategory = thirdCategory;
	}
}
