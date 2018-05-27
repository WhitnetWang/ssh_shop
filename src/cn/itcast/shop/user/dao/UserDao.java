package cn.itcast.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.shop.user.domain.User;

public class UserDao extends HibernateDaoSupport{

	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	public User findByUsername(String username) {
		String hql  = "from User where username = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, username);
		return list.isEmpty() ? null : list.get(0);
	}

	public User findByCode(String code) {
		String hql  = "from User where code = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql, code);
		return list.isEmpty() ? null : list.get(0);
	}

	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}

	public User login(User user) {
		String hql = "from User where username = ? and password = ?";
		List<User> list = (List<User>) this.getHibernateTemplate().find(hql,
				user.getUsername(), user.getPassword());
		return list.isEmpty() ? null : list.get(0);
	}

}
