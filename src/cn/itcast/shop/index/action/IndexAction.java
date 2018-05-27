package cn.itcast.shop.index.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.category.domain.Category;
import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.product.domain.Product;
import cn.itcast.shop.product.service.ProductService;

import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	private CategoryService categoryService;
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@Override
	public String execute() throws Exception {
		// 查询所有的一级分类
		List<Category> categories = categoryService.findAll();
		
		ServletActionContext.getRequest().getSession().setAttribute("categories", categories);
		//查询热门商品
		List<Product> hList = productService.findHotAll();
		//放在session中
		ServletActionContext.getRequest().getSession().setAttribute("hList", hList);
		//查询最新商品
		List<Product> nList = productService.findNewAll();
		//放在session中
		ServletActionContext.getRequest().getSession().setAttribute("nList", nList);
		
		return SUCCESS;
	}
}
