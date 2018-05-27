package cn.itcast.shop.admin.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.admin.category.dao.AdminCategoryDao;
import cn.itcast.shop.category.domain.Category;

@Transactional
public class AdminCategoryService {
	private AdminCategoryDao adminCategoryDao;

	public void setAdminCategoryDao(AdminCategoryDao adminCategoryDao) {
		this.adminCategoryDao = adminCategoryDao;
	}

	public List<Category> findAll() {
		return adminCategoryDao.findAll();
	}

	public void save(Category category) {
		adminCategoryDao.save(category);
	}

	public Category findByCid(Integer cid) {
		return adminCategoryDao.findByCid(cid);
	}

	public void delete(Category category) {
		adminCategoryDao.delete(category);
	}

	public void update(Category category) {
		adminCategoryDao.update(category);
	}

}
