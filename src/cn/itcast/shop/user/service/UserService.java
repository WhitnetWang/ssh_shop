package cn.itcast.shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.user.dao.UserDao;
import cn.itcast.shop.user.domain.User;

@Transactional
public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void regist(User user) {
		userDao.save(user);
	}

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public User findByCode(String code) {
		return userDao.findByCode(code);
	}

	public void update(User existUser) {
		userDao.update(existUser);
	}

	public User login(User user) {
		return userDao.login(user);
	}

}
