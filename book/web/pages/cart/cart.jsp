<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%-- 静态包含 base标签，css样式，jQuery文件 --%>
<%@include file="/pages/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%-- 静态包含，登录成功之后的菜单 --%>
			<%@ include file="/pages/common/login_success_menu.jsp"%>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="cartItem">
					<tr>
						<td>${cartItem.value.name}</td>
						<td>${cartItem.value.count}</td>
						<td>${cartItem.value.price}</td>
						<td>${cartItem.value.totalPrice}</td>
						<td><a href="#">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty sessionScope.cart.items}">

				<tr>
					<td colspan="5"><a href="index.jsp">亲，当前购物车为空！快买</a></td>
				</tr>

			</c:if>
			
		</table>
		<%--如果购物车非空才输出页面的内容--%>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a href="#">清空购物车</a></span>
				<span class="cart_span"><a href="pages/cart/checkout.jsp">去结账</a></span>
			</div>
		</c:if>
	</div>

	<%-- 静态包含 页脚内容 --%>
	<%@include file="/pages/common/foot.jsp"%>
</body>
</html>