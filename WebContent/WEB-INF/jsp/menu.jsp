<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib  prefix="s" uri="/struts-tags"%>
<div class="container header">
	<div class="span5">
		<div>
			<a href="${pageContext.request.contextPath }/index.action">
				<img width="150px" height="60px" src="${pageContext.request.contextPath}/image/r___________renleipic_01/logo.jpg" alt="双体系"/>
			</a>
		</div>
	</div>
	<div class="span9">
<div class="headerAd">
	<img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障" title="正品保障"/>
</div>	</div>
	<div class="span10 last">
		<div class="topNav clearfix">
			<ul>
				<s:if test="#session.user != null">
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<s:property value="#session.user.username"/>|
				</li>
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath }/order_myOrders.action?page=1">我的订单</a>|
				</li>
				<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath }/user_quit.action">[退出]</a>|
				</li>
				</s:if>
				<s:else>
					<li id="headerLogin" class="headerLogin" style="display: list-item;">
					<a href="${pageContext.request.contextPath }/user_toLoginPage.action">登录</a>|
					</li>
					<li id="headerRegister" class="headerRegister" style="display: list-item;">
						<a href="${pageContext.request.contextPath }/user_toRegistPage.action">注册</a>|
					</li>
				</s:else>
						<li>
							<a>会员中心</a>
							|
						</li>
						<li>
							<a>购物指南</a>
							|
						</li>
						<li>
							<a>关于我们</a>
							
						</li>
			</ul>
		</div>
		<div class="cart">
			<a  href="${pageContext.request.contextPath }/cart_toCartPage.action">购物车</a>
		</div>
			<div class="phone">
				客服热线:
				<strong>96008/53277764</strong>
			</div>
	</div>
	<div class="span24">
		<ul class="mainNav">
					<li>
						<a href="${pageContext.request.contextPath }/index.action">首页</a>
						|
					</li>
				<s:iterator value="#session.categories" var="category">
					<li>
						<a href="${pageContext.request.contextPath }/product_findByCid.action?cid=<s:property value="#category.cid"/>&page=1"><s:property value="#category.cname"/></a>
						|
					</li>
				</s:iterator>	
		</ul>
	</div>


</div>	