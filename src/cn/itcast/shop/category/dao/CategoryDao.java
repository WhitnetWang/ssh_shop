package cn.itcast.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.shop.category.domain.Category;

public class CategoryDao extends HibernateDaoSupport{

	public List<Category> findAll() {
		return (List<Category>) this.getHibernateTemplate().find(
				"from Category");
	}

}
