package com.taotao.portal.pojo;

public class CartItem {

	private long id;
	private String title;
	private Integer num;
	private long price;
	private String image;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public CartItem(long id, String title, Integer num, long price, String image) {
		super();
		this.id = id;
		this.title = title;
		this.num = num;
		this.price = price;
		this.image = image;
	}

	public CartItem() {
		super();
	}

}