<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0048)http://localhost:8080/mango/product/list/1.jhtml -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>传智网上商城</title>
<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>
	
</head>
<body>
	<%@ include file="menu.jsp" %>
<div class="container productList">
		<div class="span6">
			<div class="hotProductCategory">
				<s:iterator value="#session.categories" var="c">
						<dl>
							<dt>
								<a href="${pageContext.request.contextPath }/product_findByCid.action?page=1&cid=<s:property value="#c.cid"/>"><s:property value="#c.cname"/></a>
							</dt>
								<s:iterator value="#c.categorySeconds" var="cs">
									<dd>
										<a href="${pageContext.request.contextPath }/product_findByCsid.action?page=1&csid=<s:property value="#cs.csid"/>"><s:property value="#cs.csname"/></a>
									</dd>
								</s:iterator>
						</dl>
				</s:iterator>
			</div>
		</div>
		<div class="span18 last">
			
				<div id="result" class="result table clearfix">
						<ul>
							<s:iterator value="pageBean.list" var="p">
								<li>
									<a href="${pageContext.request.contextPath }/product_findByPid.action?pid=<s:property value='#p.pid'/>">
											<img src="${pageContext.request.contextPath}/<s:property value='#p.image'/>" width="170" height="170" style="display: inline-block;" /> 
										<span style='color:green'> <s:property value="#p.pname" />
										</span> 
										<span class="price"> 商城价： ￥<s:property value="#p.shop_price" />/份 </span> 
									</a>
								</li>
							</s:iterator>
						</ul>
				</div>
	<div class="pagination">
		<s:if test="cid != null">
			<span>第<s:property value="pageBean.page" />/<s:property value="pageBean.totalPage" />页</span>
			<s:if test="pageBean.page != 1">
				<a class="firstPage" href="${pageContext.request.contextPath }/product_findByCid.action?page=1&cid=<s:property value='cid'/>">&nbsp;</a>
				<a class="previousPage" href="${pageContext.request.contextPath }/product_findByCid.action?page=<s:property value='pageBean.page - 1'/>&cid=<s:property value='cid'/>">&nbsp;</a>
			</s:if>
			<s:iterator begin="1" end="pageBean.totalPage" var="i">
				<s:if test="#i == pageBean.page">
					<span class="currentPage"><s:property value="#i" /></span>
				</s:if>
				<s:else>
					<a href="${pageContext.request.contextPath }/product_findByCid.action?page=<s:property value="#i"/>&cid=<s:property value='cid'/>"><s:property value="#i"/></a>
				</s:else>
			</s:iterator>
			<s:if test="pageBean.page != pageBean.totalPage">
				<a class="nextPage" href="${pageContext.request.contextPath }/product_findByCid.action?page=<s:property value='pageBean.page + 1'/>&cid=<s:property value='cid'/>">&nbsp;</a>
				<a class="lastPage" href="${pageContext.request.contextPath }/product_findByCid.action?page=<s:property value='pageBean.totalPage'/>&cid=<s:property value='cid'/>">&nbsp;</a>
			</s:if>
		</s:if>
		<s:else>
			<span>第<s:property value="pageBean.page" />/<s:property value="pageBean.totalPage" />页</span>
		<s:if test="pageBean.page != 1">
			<a class="firstPage" href="${pageContext.request.contextPath }/product_findByCsid.action?page=1&csid=<s:property value='csid'/>">&nbsp;</a>
			<a class="previousPage" href="${pageContext.request.contextPath }/product_findByCsid.action?page=<s:property value='pageBean.page - 1'/>&csid=<s:property value='csid'/>">&nbsp;</a>
		</s:if>
		<s:iterator begin="1" end="pageBean.totalPage" var="i">
			<s:if test="#i == pageBean.page">
				<span class="currentPage"><s:property value="#i" /></span>
			</s:if>
			<s:else>
				<a href="${pageContext.request.contextPath }/product_findByCsid.action?page=<s:property value="#i"/>&csid=<s:property value='cid'/>"><s:property value="#i"/></a>
			</s:else>
		</s:iterator>
		<s:if test="pageBean.page != pageBean.totalPage">
			<a class="nextPage" href="${pageContext.request.contextPath }/product_findByCsid.action?page=<s:property value='pageBean.page + 1'/>&csid=<s:property value='csid'/>">&nbsp;</a>
			<a class="lastPage" href="${pageContext.request.contextPath }/product_findByCsid.action?page=<s:property value='pageBean.totalPage'/>&csid=<s:property value='csid'/>">&nbsp;</a>
		</s:if>
		</s:else>
		
	</div>
	
		</div>
	</div>
<div class="container footer">
	<div class="span24">
		<div class="footerAd">
					<img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势" title="我们的优势"/>
</div>	</div>
	<div class="span24">
		<ul class="bottomNav">
					<li>
						<a >关于我们</a>
						|
					</li>
					<li>
						<a>联系我们</a>
						|
					</li>
					<li>
						<a >诚聘英才</a>
						|
					</li>
					<li>
						<a >法律声明</a>
						|
					</li>
					<li>
						<a>友情链接</a>
						|
					</li>
					<li>
						<a target="_blank">支付方式</a>
						|
					</li>
					<li>
						<a  target="_blank">配送方式</a>
						|
					</li>
					<li>
						<a >官网</a>
						|
					</li>
					<li>
						<a >论坛</a>
						
					</li>
		</ul>
	</div>
	<div class="span24">
		<div class="copyright">Copyright©2005-2015 网上商城 版权所有</div>
	</div>
</div>
</body></html>