package com.ye.volleydemo.bean;

public class Banner {

	private long id;
	private int type;//��ת���ͣ�1��ת����ҳ��2��ת����Ʒ���飬3��ת������ȥ��
	private String adUrl;//ͼƬ·��
	private String webUrl;//�������ת��ҳ���ͣ��򷵻���ҳ��ַ
	private int adKind;//1Ϊ����banner��2Ϊ���banner
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getAdUrl() {
		return adUrl;
	}
	public void setAdUrl(String adUrl) {
		this.adUrl = adUrl;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public int getAdKind() {
		return adKind;
	}
	public void setAdKind(int adKind) {
		this.adKind = adKind;
	}
	
}
