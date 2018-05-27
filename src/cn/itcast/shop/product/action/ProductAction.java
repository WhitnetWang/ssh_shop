package cn.itcast.shop.product.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.product.domain.Product;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements
		ModelDriven<Product> {
	private Product product = new Product();
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Product getModel() {
		return product;
	}

	// 根据pid查询商品
	public String findByPid() {
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}

	/*
	 * 根据一级分类分页查询商品
	 */
	// 一级分类的cid
	private Integer cid;

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCid() {
		return cid;
	}

	// page
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	public String findByCid() {
		PageBean<Product> pageBean = productService.findByCid(cid, page);
		// 在到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}

	/*
	 * 根据二级分类分页查询商品
	 */
	private Integer csid;

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public String findByCsid() {
		PageBean<Product> pageBean = productService.findByCsid(csid, page);
		// 在到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}

}
