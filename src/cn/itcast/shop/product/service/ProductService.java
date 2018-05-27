package cn.itcast.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.product.dao.ProductDao;
import cn.itcast.shop.product.domain.Product;
import cn.itcast.shop.utils.PageBean;

@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> findHotAll() {
		return productDao.findHotAll();
	}

	public List<Product> findNewAll() {
		return productDao.findNewAll();
	}

	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	public PageBean<Product> findByCid(Integer cid, Integer page) {
		// 创建分页
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页
		pageBean.setPage(page);
		// 设置每页记录数
		int limit = 12;
		// 查询总记录数
		int totalCount = productDao.findTotalCountByCid(cid);
		// 计算总页数
		int totalPage = totalCount % limit == 0 ? totalCount / limit
				: totalCount / limit + 1;
		// 设置总页数
		pageBean.setTotalPage(totalPage);
		// 计算开始位置
		int begin = (page - 1) * limit;
		// 得到list
		List<Product> list = productDao.findPageListByCid(begin, limit, cid);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Product> findByCsid(Integer csid, Integer page) {
		// 创建分页
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页
		pageBean.setPage(page);
		// 设置每页记录数
		int limit = 12;
		// 查询总记录数
		int totalCount = productDao.findTotalCountByCsid(csid);
		// 计算总页数
		int totalPage = totalCount % limit == 0 ? totalCount / limit
				: totalCount / limit + 1;
		// 设置总页数
		pageBean.setTotalPage(totalPage);
		// 计算开始位置
		int begin = (page - 1) * limit;
		// 得到list
		List<Product> list = productDao.findPageListByCsid(begin, limit, csid);
		pageBean.setList(list);
		return pageBean;
	}

}
