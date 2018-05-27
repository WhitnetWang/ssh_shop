package cn.itcast.shop.admin.category.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.shop.category.domain.Category;

public class AdminCategoryDao extends HibernateDaoSupport {

	public List<Category> findAll() {
		String hql = "from Category";
		return (List<Category>) this.getHibernateTemplate().find(hql);
	}

	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}
	
}
