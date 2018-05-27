package cn.itcast.shop.admin.product.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.admin.categorysecond.service.AdminCategorySecondService;
import cn.itcast.shop.admin.product.service.AdminProductService;
import cn.itcast.shop.categorysecond.domain.CategorySecond;
import cn.itcast.shop.product.domain.Product;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminProductAction extends ActionSupport implements
		ModelDriven<Product> {
	private Product product = new Product();
	private AdminProductService adminProductService;
	private AdminCategorySecondService adminCategorySecondService;

	public void setAdminCategorySecondService(
			AdminCategorySecondService adminCategorySecondService) {
		this.adminCategorySecondService = adminCategorySecondService;
	}

	public void setAdminProductService(AdminProductService adminProductService) {
		this.adminProductService = adminProductService;
	}

	public Product getModel() {
		return product;
	}

	/*
	 * 分页查询所有商品
	 */
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	public String findAllByPage() {
		PageBean<Product> pageBean = adminProductService.findAllByPage(page);
		// 放如值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}

	/*
	 * 到添加页面
	 */
	public String toAddPage() {
		// 先查询所属的二级
		List<CategorySecond> categorySeconds = adminCategorySecondService
				.findAll();
		// 放在值栈
		ActionContext.getContext().getValueStack()
				.set("categorySeconds", categorySeconds);
		return "toAddPage";
	}

	/*
	 * 添加商品
	 */
	private File upload;
	private String uploadFileName;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String save() throws IOException {
		/*
		 * 必须有图片,否则就返回
		 */
		if (upload == null) {
			// 先查询所属的二级
			List<CategorySecond> categorySeconds = adminCategorySecondService
					.findAll();
			// 放在值栈
			ActionContext.getContext().getValueStack()
					.set("categorySeconds", categorySeconds);
			this.addActionMessage("您没有上传图片");
			return "toAddPage";
		}
		// 获取文件存放路径
		String rootPath = ServletActionContext.getServletContext().getRealPath(
				"/products");
		// 创建目标文件对象
		File destFile = new File(rootPath, uploadFileName);
		// 复制文件
		FileUtils.copyFile(upload, destFile);

		// 设置product
		product.setImage("products/" + uploadFileName);

		product.setPdate(new Date());

		adminProductService.save(product);
		return "save";
	}

	/*
	 * 删除商品
	 */
	public String delete() {
		// 先查后删
		Product existProduct = adminProductService.findByPid(product.getPid());
		/*
		 * 删除图片
		 */
		String path = existProduct.getImage();
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/" + path);

		File file = new File(realPath);
		file.delete();

		adminProductService.delete(product);
		return "deleteSuccess";
	}

	/*
	 * 到修改页面
	 */
	public String edit() {
		product = adminProductService.findByPid(product.getPid());
		// 先查询所属的二级
		List<CategorySecond> categorySeconds = adminCategorySecondService
				.findAll();
		// 放在值栈
		ActionContext.getContext().getValueStack()
				.set("categorySeconds", categorySeconds);
		return "edit";
	}

	/*
	 * 修改
	 */
	public String update() throws IOException {
		// 获取已存在的product
		Product existProduct = adminProductService.findByPid(product.getPid());
		product.setPdate(new Date());
		/*
		 * 先判断是否上传文件，如果有文件的就得删除旧文件
		 */
		if (upload != null) {

			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/" + existProduct.getImage());
			File existFile = new File(realPath);
			// 删除旧文件
			existFile.delete();

			/*
			 * 保存新文件
			 */
			realPath = ServletActionContext.getServletContext().getRealPath(
					"/products");

			File destFile = new File(realPath, uploadFileName);

			FileUtils.copyFile(upload, destFile);

			// 设置product
			product.setImage("products/" + uploadFileName);
		}
		
		/*
		 * 保存product
		 */
		adminProductService.update(product);
		
		return "update";
	}

}
