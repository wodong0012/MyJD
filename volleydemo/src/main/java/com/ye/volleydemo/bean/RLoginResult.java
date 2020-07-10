package com.ye.volleydemo.bean;

public class RLoginResult {

	private long id;
	private String userName;
	private String userIcon;//ͷ��·��
	private int waitPayCount;//��������
	private int waitReceiveCount;//���ջ���
	private int userLevel;//1ע���Ա2ͭ�ƻ�Ա3���ƻ�Ա4���ƻ�Ա5��ʯ��Ա
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	public int getWaitPayCount() {
		return waitPayCount;
	}
	public void setWaitPayCount(int waitPayCount) {
		this.waitPayCount = waitPayCount;
	}
	public int getWaitReceiveCount() {
		return waitReceiveCount;
	}
	public void setWaitReceiveCount(int waitReceiveCount) {
		this.waitReceiveCount = waitReceiveCount;
	}
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	
}
