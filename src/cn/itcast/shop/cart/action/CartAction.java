package cn.itcast.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.cart.domain.Cart;
import cn.itcast.shop.cart.domain.CartItem;
import cn.itcast.shop.product.domain.Product;
import cn.itcast.shop.product.service.ProductService;

import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport {
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/*
	 * 添加购物车
	 */
	private Integer pid;
	private Integer count;

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	/*
	 * 添加购物车
	 */
	public String addCart() {
		//先根据pid查询商品
		Product product = productService.findByPid(pid);
		//创建一个购物项
		CartItem cartItem = new CartItem();
		//设置购物项
		cartItem.setCount(count);
		cartItem.setProduct(product);
		//从session中得到购物车
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart != null){//session中有购物车
			cart.add(cartItem);
		}else{//没有
			Cart cart2 = new Cart();
			cart2.add(cartItem);
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart2);
		}
		return "addCart";
	}
	/*
	 * 移除购物项
	 */
	public String remove(){
		//从session中得到购物车
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		cart.remove(pid);
		return "addCart";
	}
	/*
	 * 清空购物车
	 */
	public String clear(){
		//从session中得到购物车
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		cart.clear();
		ServletActionContext.getRequest().getSession().setAttribute("cart", null);
		return "addCart";
	}
	/*
	 * 跳转到购物车页面
	 */
	public String toCartPage(){
		return "toCartPage";
	}
}
