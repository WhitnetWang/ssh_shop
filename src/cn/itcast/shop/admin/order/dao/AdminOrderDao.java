package cn.itcast.shop.admin.order.dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.shop.order.domain.Order;
import cn.itcast.shop.utils.PageHibernateCallback;

public class AdminOrderDao extends HibernateDaoSupport {

	public int findCount() {
		String hql = "select count(*) from Order";
		List<Long> list = (List<Long>) this.getHibernateTemplate().find(hql);
		return list.isEmpty() ? 0 : list.get(0).intValue();
	}

	public List<Order> findPage(int begin, int limit) {
		String hql = "from Order order by ordertime desc";
		return this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, null, begin, limit));
	}

	public Order findByOid(String oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	public void update(Order existOrder) {
		this.getHibernateTemplate().update(existOrder);
	}

	public List<Order> findPageByState(Integer state) {
		String hql = "from Order where state = ? order by ordertime desc";
		return (List<Order>) this.getHibernateTemplate().find(hql, state);
	}

}
