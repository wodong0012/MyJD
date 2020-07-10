package com.ye.volleydemo.bean;

public class SProductList {

	private long categoryId;
	private int filterType=1;//1-�ۺ� 2-��Ʒ 3-����
	private int sortType;//1-���� 2-�۸�ߵ��� 3-�۸�͵���
	private int deliverChoose;//0-������ѡ�� 1���������� 2-����������� 4-��������л� 3��������1+2 5��������1+4 6��������2+4
	private int minPrice;//��ͼ۸�
	private int maxPrice;//��߼۸�
	private long brandId;//��߼۸�
	
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public int getFilterType() {
		return filterType;
	}
	public void setFilterType(int filterType) {
		this.filterType = filterType;
	}
	public int getSortType() {
		return sortType;
	}
	public void setSortType(int sortType) {
		this.sortType = sortType;
	}
	public int getDeliverChoose() {
		return deliverChoose;
	}
	public void setDeliverChoose(int deliverChoose) {
		this.deliverChoose = deliverChoose;
	}
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	public long getBrandId() {
		return brandId;
	}
	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}
	@Override
	public String toString() {
		return "SProductList [categoryId=" + categoryId + ", filterType="
				+ filterType + ", sortType=" + sortType + ", deliverChoose="
				+ deliverChoose + ", minPrice=" + minPrice + ", maxPrice="
				+ maxPrice + ", brandId=" + brandId + "]";
	}
	
}
