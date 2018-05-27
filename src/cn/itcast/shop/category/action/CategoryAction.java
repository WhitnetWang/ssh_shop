package cn.itcast.shop.category.action;

import cn.itcast.shop.category.domain.Category;
import cn.itcast.shop.category.service.CategoryService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CategoryAction extends ActionSupport implements
		ModelDriven<Category> {
	private Category category = new Category();
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public Category getModel() {
		return category;
	}

}
