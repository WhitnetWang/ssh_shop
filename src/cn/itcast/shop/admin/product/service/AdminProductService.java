package cn.itcast.shop.admin.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.admin.product.dao.AdminProductDao;
import cn.itcast.shop.product.domain.Product;
import cn.itcast.shop.utils.PageBean;

@Transactional
public class AdminProductService {
	private AdminProductDao adminProductDao;

	public void setAdminProductDao(AdminProductDao adminProductDao) {
		this.adminProductDao = adminProductDao;
	}

	public PageBean<Product> findAllByPage(Integer page) {
		// 创建分页对象
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页
		pageBean.setPage(page);
		// 设置每页记录数
		int limit = 10;
		// 查询总记录数
		int totalCount = adminProductDao.findCount();
		// 计算总页数
		int totalPage = totalCount % limit == 0 ? totalCount / limit
				: totalCount / limit + 1;
		//设置总页数
		pageBean.setTotalPage(totalPage);
		//计算位置
		int begin = (page - 1) * limit;
		//得到list
		List<Product> list = adminProductDao.findPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(Product product) {
		adminProductDao.save(product);
	}

	public void delete(Product product) {
		adminProductDao.delete(product);
	}

	public Product findByPid(Integer pid) {
		return adminProductDao.findByPid(pid);
	}

	public void update(Product product) {
		adminProductDao.update(product);
	}

}
