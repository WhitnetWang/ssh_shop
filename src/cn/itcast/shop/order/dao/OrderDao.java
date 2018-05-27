package cn.itcast.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.shop.order.domain.Order;
import cn.itcast.shop.utils.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport {

	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	public Order findByOid(String oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	public void update(Order order) {
		this.getHibernateTemplate().update(order);
	}

	public int findCountByUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql, uid);
		return list.isEmpty() ? 0 : list.get(0).intValue();
	}

	public List<Order> findPageByUid(Integer uid, Integer bgein, int limit) {
		String hql = "from Order o where o.user.uid = ? order by ordertime desc";
		return this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, bgein, limit));
	}
	
}
