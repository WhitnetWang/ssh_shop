package cn.itcast.shop.admin.user.action;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.admin.user.domain.AdminUser;
import cn.itcast.shop.admin.user.service.AdminUserService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {
	private AdminUserService adminUserService;
	private AdminUser adminUser = new AdminUser();

	public AdminUser getModel() {
		return adminUser;
	}

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	public String login() {
		AdminUser existUser = adminUserService.login(adminUser);
		if (existUser != null) {
			ServletActionContext.getRequest().getSession().setAttribute("user", existUser);
			return "loginSuccess";
		} else {
			this.addActionError("用户名或密码错误");
			return "toLoginPage";
		}

	}

}
