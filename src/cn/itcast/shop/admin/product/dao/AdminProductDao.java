package cn.itcast.shop.admin.product.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.shop.product.domain.Product;
import cn.itcast.shop.utils.PageHibernateCallback;

public class AdminProductDao extends HibernateDaoSupport {

	public int findCount() {
		String hql = "select count(*) from Product";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		return list.isEmpty() ? 0 : list.get(0).intValue();
	}

	public List<Product> findPage(int begin, int limit) {
		String hql = "from Product order by pdate desc";
		return this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, null, begin, limit));
	}

	public void save(Product product) {
		this.getHibernateTemplate().save(product);
	}

	public void delete(Product product) {
		this.getHibernateTemplate().delete(product);
	}

	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	public void update(Product product) {
		this.getHibernateTemplate().update(product);
	}

}
