package cn.itcast.shop.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	/*
	 * 发送激活邮件
	 */
	public static void sendMail(String to, String code) {
		/*
		 * 配置连接参数
		 */
		Properties props = new Properties();
		// 打开验证权限，因为客户端连接服务器必须要验证
		props.setProperty("mail.smtp.auth", "true");
		// 设置通信协议必须是smtp
		props.setProperty("mail.transport.protocol", "smtp");
		// 设置主机
		props.setProperty("mail.smtp.host", "smtp.163.com");
		// 设置端口
		props.setProperty("mail.smtp.port", "25");

		// 打开域服务器的连接
		Session session = Session.getInstance(props);
		// 打印日志
		session.setDebug(true);

		// 创建邮件
		Message message = new MimeMessage(session);
		// 设置主题
		try {
			message.setSubject("这是来自ssh网上商城的激活邮件");
			// 设置发件人
			message.setFrom(new InternetAddress("18235430106@163.com"));
			// 设置邮件内容
			message.setContent(
					"<h1>请点击下面的连接激活</h1><a href='http://localhost:8080/ssh_shop2/user_active.action?code="
							+ code + "'>点击激活</a>", "text/html;charset=utf-8");

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// 得到发送对象
		Transport transport;
		try {
			transport = session.getTransport();
			// 设置帐号密码
			transport.connect("18235430106@163.com", "wang123456");
			// 发送邮件
			transport.sendMessage(message,
					new InternetAddress[] { new InternetAddress(to) });
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		// 断开连接
		try {
			transport.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
