package cn.itcast.shop.cart.domain;

import cn.itcast.shop.product.domain.Product;

/*
 * 购物车项
 */
public class CartItem {
	private Product product;
	private Integer count;
	private Double subTotal;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Double getSubTotal() {
		return product.getShop_price() * count;
	}

}
