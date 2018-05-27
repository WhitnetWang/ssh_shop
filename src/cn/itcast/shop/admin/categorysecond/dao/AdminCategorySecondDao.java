package cn.itcast.shop.admin.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.shop.categorysecond.domain.CategorySecond;
import cn.itcast.shop.utils.PageHibernateCallback;

public class AdminCategorySecondDao extends HibernateDaoSupport {

	public int findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		return list.isEmpty() ? 0 : list.get(0).intValue();
	}

	public List<CategorySecond> findPage(int begin, int limit) {
		String hql = "from CategorySecond";
		return this.getHibernateTemplate().execute(
				new PageHibernateCallback<CategorySecond>(hql, null, begin,
						limit));
	}

	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
	}

	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
	}

	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		return (List<CategorySecond>) this.getHibernateTemplate().find(hql);
	}

}
