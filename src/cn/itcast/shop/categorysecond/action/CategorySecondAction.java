package cn.itcast.shop.categorysecond.action;

import cn.itcast.shop.categorysecond.domain.CategorySecond;
import cn.itcast.shop.categorysecond.service.CategorySecondService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CategorySecondAction extends ActionSupport implements
		ModelDriven<CategorySecond> {
	private CategorySecond categorySecond = new CategorySecond();
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public CategorySecond getModel() {
		return categorySecond;
	}

}
