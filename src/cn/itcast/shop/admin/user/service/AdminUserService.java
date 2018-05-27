package cn.itcast.shop.admin.user.service;

import cn.itcast.shop.admin.user.dao.AdminUserDao;
import cn.itcast.shop.admin.user.domain.AdminUser;

public class AdminUserService {
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	public AdminUser login(AdminUser user) {
		return adminUserDao.login(user);
	}

}
