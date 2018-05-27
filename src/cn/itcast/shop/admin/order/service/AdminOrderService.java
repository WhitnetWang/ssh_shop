package cn.itcast.shop.admin.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.admin.order.dao.AdminOrderDao;
import cn.itcast.shop.order.domain.Order;
import cn.itcast.shop.utils.PageBean;

@Transactional
public class AdminOrderService {
	private AdminOrderDao adminOrderDao;

	public void setAdminOrderDao(AdminOrderDao adminOrderDao) {
		this.adminOrderDao = adminOrderDao;
	}

	public PageBean<Order> findAllByPage(Integer page) {
		// 创建分页对象
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置当前页
		pageBean.setPage(page);
		// 设置每页记录数
		int limit = 5;
		// 查询总记录数
		int totalCount = adminOrderDao.findCount();
		// 计算总页数
		int totalPage = totalCount % limit == 0 ? totalCount / limit
				: totalCount / limit + 1;
		// 设置总页数
		pageBean.setTotalPage(totalPage);
		// 计算开始位置
		int begin = (page - 1) * limit;
		// 查询list
		List<Order> list = adminOrderDao.findPage(begin, limit);
		// 设置list
		pageBean.setList(list);
		return pageBean;
	}

	public Order findByOid(String oid) {
		return adminOrderDao.findByOid(oid);
	}

	public void update(Order existOrder) {
		adminOrderDao.update(existOrder);
	}

	public PageBean<Order> findByState(Integer state) {
		// 创建分页对象
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置当前页
		pageBean.setPage(1);
		pageBean.setTotalPage(1);
		// 查询list
		List<Order> list = adminOrderDao.findPageByState(state);
		// 设置list
		pageBean.setList(list);
		return pageBean;
	}

}
