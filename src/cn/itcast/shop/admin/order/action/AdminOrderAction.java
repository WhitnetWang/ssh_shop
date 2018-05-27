package cn.itcast.shop.admin.order.action;

import cn.itcast.shop.admin.order.service.AdminOrderService;
import cn.itcast.shop.order.domain.Order;
import cn.itcast.shop.product.domain.Product;
import cn.itcast.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminOrderAction extends ActionSupport implements
		ModelDriven<Order> {
	private Order order = new Order();
	private AdminOrderService adminOrderService;

	public void setAdminOrderService(AdminOrderService adminOrderService) {
		this.adminOrderService = adminOrderService;
	}

	public Order getModel() {
		return order;
	}

	/*
	 * 分页查询所有订单
	 */
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	public String findAllByPage() {
		PageBean<Order> pageBean = adminOrderService.findAllByPage(page);
		// 放在值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}

	/*
	 * 异步查看订单详情
	 */
	public String show() {
		order = adminOrderService.findByOid(order.getOid());
		return "show";
	}

	/*
	 * 发货
	 */
	public String updateState() {
		// 先查后改
		Order existOrder = adminOrderService.findByOid(order.getOid());
		existOrder.setState(3);
		adminOrderService.update(existOrder);
		return "updateState";
	}

	/*
	 * 根据订单状态查询
	 */
	public String findByState() {
		PageBean<Order> pageBean = adminOrderService.findByState(
				order.getState());
		//存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByState";
	}

}
