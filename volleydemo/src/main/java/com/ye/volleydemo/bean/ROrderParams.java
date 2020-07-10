package com.ye.volleydemo.bean;

public class ROrderParams {
	
	private long oid;
	private String orderNum;//�������
	private double allPrice;//��Ʒ�ܼ۸�
	private double freight;
	private double totalPrice;
	private String buyTime;//����������ʱ��
	private int errorType;//0-�ɹ� 1-��Ʒû����� 2-ϵͳʧ��
	private String pname;//������Ʒ ���ǹ������Ʒ ������ʾĳһ��ƽ̨�µ���Ʒ
	private int payWay;//���ʽ�� 0����֧�� 1�������
	private String tn;//����
	public long getOid() {
		return oid;
	}
	public void setOid(long oid) {
		this.oid = oid;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public double getAllPrice() {
		return allPrice;
	}
	public void setAllPrice(double allPrice) {
		this.allPrice = allPrice;
	}
	public double getFreight() {
		return freight;
	}
	public void setFreight(double freight) {
		this.freight = freight;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(String buyTime) {
		this.buyTime = buyTime;
	}
	public int getErrorType() {
		return errorType;
	}
	public void setErrorType(int errorType) {
		this.errorType = errorType;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPayWay() {
		return payWay;
	}
	public void setPayWay(int payWay) {
		this.payWay = payWay;
	}
	public String getTn() {
		return tn;
	}
	public void setTn(String tn) {
		this.tn = tn;
	}
	
}
