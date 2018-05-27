package cn.itcast.shop.admin.user.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.shop.admin.user.domain.AdminUser;
import cn.itcast.shop.user.domain.User;
@SuppressWarnings("all")
public class AdminUserDao extends HibernateDaoSupport {
	
	public AdminUser login(AdminUser user) {
		String hql = "from AdminUser where username = ? and password = ?";
		List<AdminUser> list = (List<AdminUser>) this.getHibernateTemplate()
				.find(hql, user.getUsername(), user.getPassword());
		return list.isEmpty() ? null : list.get(0);
	}

}
