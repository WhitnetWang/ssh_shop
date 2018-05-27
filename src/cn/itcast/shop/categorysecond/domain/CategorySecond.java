package cn.itcast.shop.categorysecond.domain;

import java.io.Serializable;

import cn.itcast.shop.category.domain.Category;

public class CategorySecond implements Serializable{
	private Integer csid;
	private String csname;
	private Category category;

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public String getCsname() {
		return csname;
	}

	public void setCsname(String csname) {
		this.csname = csname;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
