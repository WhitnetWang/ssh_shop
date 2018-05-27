package cn.itcast.shop.admin.category.action;

import java.util.List;

import cn.itcast.shop.admin.category.service.AdminCategoryService;
import cn.itcast.shop.category.domain.Category;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategoryAction extends ActionSupport implements
		ModelDriven<Category> {
	private Category category = new Category();
	private AdminCategoryService adminCategoryService;

	public void setAdminCategoryService(
			AdminCategoryService adminCategoryService) {
		this.adminCategoryService = adminCategoryService;
	}

	public Category getModel() {
		return category;
	}
	/*
	 * 查询所有一级分类
	 */
	public String findAll(){
		List<Category> categories = adminCategoryService.findAll();
		//放在值栈中
		ActionContext.getContext().getValueStack().set("categories", categories);
		return "findAll";
	}
	
	/*
	 * 添加一级分类
	 */
	public String save(){
		adminCategoryService.save(category);
		return "saveSuccess";
	}
	
	/*
	 * 删除一级分类
	 */
	public String delete(){
		//先查后删,配置级联删除后，必须删除持久态对象
		category = adminCategoryService.findByCid(category.getCid());
		adminCategoryService.delete(category);
		return "deleteSuccess";
	}
	
	/*
	 * 到修改页面
	 */
	public String edit(){
		category = adminCategoryService.findByCid(category.getCid());
		return "edit";
	}
	
	/*
	 * 修改一级分类
	 */
	public String update(){
		adminCategoryService.update(category);
		return "updateSuccess";
	}

}
