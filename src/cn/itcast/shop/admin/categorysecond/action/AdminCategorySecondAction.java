package cn.itcast.shop.admin.categorysecond.action;

import java.util.List;

import cn.itcast.shop.admin.category.service.AdminCategoryService;
import cn.itcast.shop.admin.categorysecond.service.AdminCategorySecondService;
import cn.itcast.shop.category.domain.Category;
import cn.itcast.shop.categorysecond.domain.CategorySecond;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategorySecondAction extends ActionSupport implements
		ModelDriven<CategorySecond> {
	private CategorySecond categorySecond = new CategorySecond();

	private AdminCategorySecondService adminCategorySecondService;

	public void setAdminCategorySecondService(
			AdminCategorySecondService adminCategorySecondService) {
		this.adminCategorySecondService = adminCategorySecondService;
	}

	private AdminCategoryService adminCategoryService;

	public void setAdminCategoryService(
			AdminCategoryService adminCategoryService) {
		this.adminCategoryService = adminCategoryService;
	}

	public CategorySecond getModel() {
		return categorySecond;
	}

	/*
	 * 分页查询二级分类
	 */
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	public String findAllByPage() {
		PageBean<CategorySecond> pageBean = adminCategorySecondService
				.findAllByPage(page);
		// 放在值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}

	/*
	 * 到添加二级分类页面
	 */
	public String toAddPage() {
		// 先查询一级分类
		List<Category> categories = adminCategoryService.findAll();
		// 放到值栈中
		ActionContext.getContext().getValueStack()
				.set("categories", categories);
		return "toAddPage";
	}

	/*
	 * 添加二级分类
	 */
	public String save() {
		adminCategorySecondService.save(categorySecond);
		return "saveSuccess";
	}

	/*
	 * 删除二级分类
	 */
	public String delete() {
		// 先查后删
		categorySecond = adminCategorySecondService.findByCsid(categorySecond
				.getCsid());
		adminCategorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}

	/*
	 * 到修改页面
	 */
	public String edit() {
		// 先查询一级分类
		List<Category> categories = adminCategoryService.findAll();
		// 放到值栈中
		ActionContext.getContext().getValueStack()
				.set("categories", categories);
		categorySecond = adminCategorySecondService.findByCsid(categorySecond
				.getCsid());
		return "edit";
	}
	
	/*
	 * 修改二级分类
	 */
	public String update(){
		adminCategorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}
