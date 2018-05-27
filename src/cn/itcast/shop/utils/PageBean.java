package cn.itcast.shop.utils;

import java.util.List;

public class PageBean<T> {
	private Integer page; // 当前页
	private Integer totalPage;// 总页数
	private List<T> list;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

}
