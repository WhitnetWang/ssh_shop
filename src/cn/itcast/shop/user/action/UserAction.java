package cn.itcast.shop.user.action;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;

import cn.itcast.shop.user.domain.User;
import cn.itcast.shop.user.service.UserService;
import cn.itcast.shop.utils.MailUtils;
import cn.itcast.shop.utils.UUIDUtils;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	private UserService userService;
	private String repassword;

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getModel() {
		return user;
	}

	/*
	 * 去注册页面
	 */
	public String toRegistPage() {
		return "toRegistPage";
	}

	/*
	 * 执行注册
	 */
	// 获取提交的验证码
	private String verifycode;

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public String regist() {
		if (!repassword.equals(user.getPassword())) {
			this.addFieldError("repassword", "两次密码输入不一致");
			return "toRegistPage";
		}
		// 获取session的中验证码
		String checkcode = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");
		if (!verifycode.equalsIgnoreCase(checkcode)) {
			this.addFieldError("checkcode", "验证码错误");
			return "toRegistPage";
		}

		user.setState(0);
		user.setCode(UUIDUtils.getUUID());
		/*
		 * 发邮件
		 */
		MailUtils.sendMail(user.getEmail(), user.getCode());
		userService.regist(user);
		this.addActionMessage("恭喜！注册成功");
		return "regist";
	}

	/*
	 * 异步校验username
	 */
	public void checkUsername() throws IOException {
		User existUser = userService.findByUsername(user.getUsername());
		if (existUser != null) {
			ServletActionContext.getResponse().setContentType(
					"text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter()
					.write("<font color='red'>该用户名已存在</font>");
		} else {
			ServletActionContext.getResponse().setContentType(
					"text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter()
					.write("<font color='green'>该用户名可用</font>");
		}
	}

	/*
	 * 邮件激活
	 */
	public String active() {
		User existUser = userService.findByCode(user.getCode());
		if (existUser != null) {
			existUser.setState(1);
			existUser.setCode("");
			this.addActionMessage("恭喜激活成功，您可以去登录了");
			userService.update(existUser);
		} else {
			this.addActionMessage("激活有误");
		}
		return "regist";
	}

	/*
	 * 到登录页面
	 */
	public String toLoginPage() {
		return "toLoginPage";
	}

	/*
	 * 登录
	 */
	public String login() {
		// 先校验验证码
		// 获取session的中验证码
		String checkcode = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");
		if (!verifycode.equalsIgnoreCase(checkcode)) {
			this.addFieldError("checkcode", "验证码错误");
			return "toLoginPage";
		}
		User existUser = userService.login(user);
		
		if(existUser != null){
			if(existUser.getState() != 1){
				this.addActionMessage("您还未激活,请到邮箱激活");
				return "toLoginPage";
			}
			ServletActionContext.getRequest().getSession().setAttribute("user", existUser);
			return "login";
		}else{
			this.addActionMessage("用户名或密码错误");
			return "toLoginPage";
		}
		
	}
	/*
	 * 退出
	 */
	public String quit(){
		ServletActionContext.getRequest().getSession().setAttribute("user", null);
		return "quit";
	}

}
