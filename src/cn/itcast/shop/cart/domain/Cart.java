package cn.itcast.shop.cart.domain;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.itcast.shop.product.domain.Product;

/*
 * 购物车
 */
public class Cart {
	//存在购物车
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	//总计
	private double total;
	
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	public Double getTotal() {
		return total;
	}
	//添加购物车
	public void add(CartItem cartItem){
		//得到商品
		Product product = cartItem.getProduct();
		
		if(map.containsKey(product.getPid())){//存在
			
			CartItem item = map.get(product.getPid());
			item.setCount(item.getCount() + cartItem.getCount());
			
		}else{//不存在
			map.put(cartItem.getProduct().getPid(), cartItem);
		}
		
		this.total += cartItem.getSubTotal();
	}
	//清空购物车
	public void clear(){
		map.clear();
		this.total = 0;
	}
	//移除购物车
	public void remove(Integer pid){
		CartItem cartItem = map.remove(pid);
		this.total -= cartItem.getSubTotal();
	}
}