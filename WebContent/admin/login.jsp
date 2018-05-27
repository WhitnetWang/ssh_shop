<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>网上商城管理中心</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link href="${pageContext.request.contextPath }/css/general.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet" type="text/css" />

	<style type="text/css">
		body {
		  color: white;
		}
	</style>
</head>
<body style="background: #278296">
	<form method="post"
		action="${pageContext.request.contextPath }/adminUser_login.action"
		target="_parent" name='theForm'>
		<table cellspacing="0" cellpadding="0" style="margin-top: 100px"
			align="center">
			<tr>
				<td style="padding-left: 50px">
					<table>
						<tr>
							<td colspan="2">
								<font color="red"><s:actionerror/></font>
							</td>
						</tr>
						<tr>
							<td>管理员姓名：</td>
							<td><input type="text" name="username" />
							</td>
						</tr>
			
						<tr>
							<td>管理员密码：</td>
							<td><input type="password" name="password" />
							</td>
						</tr>

						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="登录"/>
							</td>
						</tr>
					</table></td>
			</tr>
		</table>
	</form>
</body>
</html>