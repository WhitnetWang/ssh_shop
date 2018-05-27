package cn.itcast.shop.product.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.shop.product.domain.Product;
import cn.itcast.shop.utils.PageHibernateCallback;

public class ProductDao extends HibernateDaoSupport {

	public List<Product> findHotAll() {
		String hql = "from Product where is_hot = 1 order by pdate desc";
		// 得到本地session
		Session session = this.getSessionFactory().getCurrentSession();
		// hql查询
		Query query = session.createQuery(hql);
		// 分页
		query.setFirstResult(0);
		query.setMaxResults(10);
		return query.list();
	}

	public List<Product> findNewAll() {
		String hql = "from Product order by pdate desc";
		// 得到本地session
		Session session = this.getSessionFactory().getCurrentSession();
		// hql查询
		Query query = session.createQuery(hql);
		// 分页
		query.setFirstResult(0);
		query.setMaxResults(10);
		return query.list();
	}

	public Product findByPid(Integer pid) {
		return this.getHibernateTemplate().get(Product.class, pid);
	}

	public int findTotalCountByCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql,
				cid);
		return list.isEmpty() ? 0 : list.get(0).intValue();
	}

	public List<Product> findPageListByCid(int begin, int limit, Integer cid) {
		String hql = "from Product p where p.categorySecond.category.cid = ?";
		return this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, new Object[] { cid },
						begin, limit));
	}

	public int findTotalCountByCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql,
				csid);
		return list.isEmpty() ? 0 : list.get(0).intValue();
	}

	public List<Product> findPageListByCsid(int begin, int limit, Integer csid) {
		String hql = "from Product p where p.categorySecond.csid = ?";
		return this.getHibernateTemplate().execute(
				new PageHibernateCallback<Product>(hql, new Object[] { csid },
						begin, limit));
	}

}
