<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table>
  <tr>
    <th>商品图片</th>
    <th>商品名称</th>
    <th>商品价格</th>
    <th>数量</th>
    <th>小计</th>
  </tr>
  <tr>
  <s:iterator value="model.orderItems" var="orderItem">
  	<tr>
	    <td><img width="40" height="40" alt="商品图片" src="${pageContext.request.contextPath }/<s:property value='#orderItem.product.image'/>"></td>
	    <td><s:property value="#orderItem.product.pname"/></td>
	    <td><s:property value="#orderItem.product.shop_price"/></td>
	    <td><s:property value="#orderItem.count"/></td>
  		<td><s:property value="#orderItem.subtotal"/></td>
  	</tr>
   </s:iterator>
  </tr>
</table>
