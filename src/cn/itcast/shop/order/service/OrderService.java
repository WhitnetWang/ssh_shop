package cn.itcast.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.domain.Order;
import cn.itcast.shop.utils.PageBean;

@Transactional
public class OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void save(Order order) {
		orderDao.save(order);
	}

	public Order findByOid(String oid) {
		return orderDao.findByOid(oid);
	}

	public void update(Order order) {
		orderDao.update(order);
	}

	public PageBean<Order> findPagesByUid(Integer page, Integer uid) {
		// 创建分页对象
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置当前页
		pageBean.setPage(page);
		// 设置每页记录数
		int limit = 5;
		// 查询总记录数
		int totalCount = orderDao.findCountByUid(uid);
		// 计算总页数
		int totalPage = totalCount % limit == 0 ? totalCount / limit
				: totalCount / limit + 1;
		// 设置总页数
		pageBean.setTotalPage(totalPage);
		// 计算开始位置
		int begin = (page - 1) * limit;
		// 得到list
		List<Order> list = orderDao.findPageByUid(uid, page, limit);
		pageBean.setList(list);
		return pageBean;
	}

}
