package cn.itcast.shop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.cart.domain.Cart;
import cn.itcast.shop.cart.domain.CartItem;
import cn.itcast.shop.order.domain.Order;
import cn.itcast.shop.order.domain.OrderItem;
import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.user.domain.User;
import cn.itcast.shop.utils.PageBean;
import cn.itcast.shop.utils.PaymentUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author wangnwenaho
 * 
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {
	private Order order = new Order();

	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Order getModel() {
		return order;
	}

	/*
	 * 提交订单
	 */
	public String save() {
		// 先判断用户是否登录
		User user = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");
		if (user == null) {
			this.addActionMessage("请先登录");
			// 如果未登录应该先去登录
			return "toLoginPage";
		}
		// 设置订单
		order.setState(1); // 1未付款 2已付款但未发货 3已发货但为确认收货 4交易完成
		order.setUser(user);
		order.setOrdertime(new Date());

		// 从session中得到购物车
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		// 设置总计
		order.setTotal(cart.getTotal());
		// 遍历购物项
		for (CartItem cartItem : cart.getCartItems()) {
			// 创建对应的订单项
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setSubtotal(cartItem.getSubTotal());
			// 订单项 关联 订单
			orderItem.setOrder(order);
			// 订单 关联 订单项
			order.getOrderItems().add(orderItem);
		}

		orderService.save(order);
		// 提交订单后应该清空购物车
		ServletActionContext.getRequest().getSession()
				.setAttribute("cart", null);
		return "save";
	}

	/*
	 * 支付
	 */
	private String pd_FrpId;

	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public void pay() throws IOException {
		// 先出根据oid查询订单
		Order existOrder = orderService.findByOid(order.getOid());
		// 设置订单
		existOrder.setAddr(order.getAddr());
		existOrder.setName(order.getName());
		existOrder.setPhone(order.getPhone());

		/*
		 * 配置支付参数
		 */
		String p0_Cmd = "Buy";
		String p1_MerId = "10001126856"; // 其实就是支付帐号
		String p2_Order = existOrder.getOid(); // 订单编号
		String p3_Amt = "0.01"; // 支付金额
		String p4_Cur = "CNY"; // 支付币种
		String p5_Pid = ""; // 商品名称
		String p6_Pcat = ""; // 商品种类
		String p7_Pdesc = ""; // 商品描述
		String p8_Url = "http://localhost:8080/ssh_shop2/order_callback.action"; // 回调方法的url
		String p9_SAF = ""; // 送货地址
		String pa_MP = ""; // 商户的扩展信息
		String pd_FrpId = this.pd_FrpId; // 用哪家银行
		String pr_NeedResponse = "1"; // 应答机制

		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 密钥
		// 通过md5加密计算hmac
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);
		// 拼接url
		StringBuilder url = new StringBuilder(
				"https://www.yeepay.com/app-merchant-proxy/node?");
		url.append("p0_Cmd=").append(p0_Cmd).append("&");
		url.append("p1_MerId=").append(p1_MerId).append("&");
		url.append("p2_Order=").append(p2_Order).append("&");
		url.append("p3_Amt=").append(p3_Amt).append("&");
		url.append("p4_Cur=").append(p4_Cur).append("&");
		url.append("p5_Pid=").append(p5_Pid).append("&");
		url.append("p6_Pcat=").append(p6_Pcat).append("&");
		url.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		url.append("p8_Url=").append(p8_Url).append("&");
		url.append("p9_SAF=").append(p9_SAF).append("&");
		url.append("pa_MP=").append(pa_MP).append("&");
		url.append("pa_MP=").append(pa_MP).append("&");
		url.append("pa_MP=").append(pa_MP).append("&");
		url.append("pd_FrpId=").append(pd_FrpId).append("&");
		url.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		url.append("hmac=").append(hmac);

		// 发重定向
		ServletActionContext.getResponse().sendRedirect(url.toString());
	}

	/*
	 * 支付成功
	 */
	private String p1_MerId;
	private String r0_Cmd;
	private String r1_Code;
	private String r2_TrxId;
	private String r3_Amt;
	private String r4_Cur;
	private String r5_Pid;
	private String r6_Order;
	private String r7_Uid;
	private String r8_MP;
	private String r9_BType;
	private String rb_BankId;
	private String ro_BankOrderId;
	private String rp_PayDate;
	private String rq_CardNo;
	private String ru_Trxtime;
	private String hmac;

	public void setHmac(String hmac) {
		this.hmac = hmac;
	}

	public void setP1_MerId(String p1_MerId) {
		this.p1_MerId = p1_MerId;
	}

	public void setR0_Cmd(String r0_Cmd) {
		this.r0_Cmd = r0_Cmd;
	}

	public void setR1_Code(String r1_Code) {
		this.r1_Code = r1_Code;
	}

	public void setR2_TrxId(String r2_TrxId) {
		this.r2_TrxId = r2_TrxId;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR4_Cur(String r4_Cur) {
		this.r4_Cur = r4_Cur;
	}

	public void setR5_Pid(String r5_Pid) {
		this.r5_Pid = r5_Pid;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public void setR7_Uid(String r7_Uid) {
		this.r7_Uid = r7_Uid;
	}

	public void setR8_MP(String r8_MP) {
		this.r8_MP = r8_MP;
	}

	public void setR9_BType(String r9_BType) {
		this.r9_BType = r9_BType;
	}

	public void setRb_BankId(String rb_BankId) {
		this.rb_BankId = rb_BankId;
	}

	public void setRo_BankOrderId(String ro_BankOrderId) {
		this.ro_BankOrderId = ro_BankOrderId;
	}

	public void setRp_PayDate(String rp_PayDate) {
		this.rp_PayDate = rp_PayDate;
	}

	public void setRq_CardNo(String rq_CardNo) {
		this.rq_CardNo = rq_CardNo;
	}

	public void setRu_Trxtime(String ru_Trxtime) {
		this.ru_Trxtime = ru_Trxtime;
	}

	public String callback() {
		// 利用本地密钥和加密算法 加密数据
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if (isValid) {
			// 有效
			if (r9_BType.equals("1")) {
				Order order = orderService.findByOid(r6_Order);
				// 修改订单状态
				order.setState(2);
				orderService.update(order);
				this.addActionMessage("订单编号:" + r6_Order + " 金额:" + r3_Amt);
			}

		} else {
			this.addActionMessage("非法操作");
		}

		return "msg";
	}

	/*
	 * 查询我的订单
	 */
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public String myOrders() {
		//从session中得到user
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		
		PageBean<Order> pageBean = orderService.findPagesByUid(page,user.getUid());
		//放在值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "myOrders";
	}
	/*
	 * 确认收货
	 */
	public String updateState(){
		Order existOrder = orderService.findByOid(order.getOid());
		
		existOrder.setState(4);
		
		orderService.update(existOrder);
		
		return "updateStateSuccess";
	}
}
