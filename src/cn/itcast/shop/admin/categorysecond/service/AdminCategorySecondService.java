package cn.itcast.shop.admin.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.admin.categorysecond.dao.AdminCategorySecondDao;
import cn.itcast.shop.categorysecond.domain.CategorySecond;
import cn.itcast.shop.utils.PageBean;

@Transactional
public class AdminCategorySecondService {
	private AdminCategorySecondDao adminCategorySecondDao;

	public void setAdminCategorySecondDao(
			AdminCategorySecondDao adminCategorySecondDao) {
		this.adminCategorySecondDao = adminCategorySecondDao;
	}

	public PageBean<CategorySecond> findAllByPage(Integer page) {
		// 创建分页对象
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		// 设置当前页
		pageBean.setPage(page);
		// 设置每页记录数
		int limit = 10;
		// 查询总记录数
		int totalCount = adminCategorySecondDao.findCount();
		// 计算总页数
		int totalPage = totalCount % limit == 0 ? totalCount / limit
				: totalCount / limit + 1;
		//设置总页数
		pageBean.setTotalPage(totalPage);
		//计算开始位置
		int begin = (page - 1) * limit;
		//查询list
		List<CategorySecond> list = adminCategorySecondDao.findPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(CategorySecond categorySecond) {
		adminCategorySecondDao.save(categorySecond);
	}

	public CategorySecond findByCsid(Integer csid) {
		return adminCategorySecondDao.findByCsid(csid);
	}

	public void delete(CategorySecond categorySecond) {
		adminCategorySecondDao.delete(categorySecond);
	}

	public void update(CategorySecond categorySecond) {
		adminCategorySecondDao.update(categorySecond);
	}

	public List<CategorySecond> findAll() {
		return adminCategorySecondDao.findAll();
	}

}
