<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<body>
	hello app1.
	<br />

	<shiro:guest>
		<a
			href="${pageContext.request.contextPath}/login?backUrl=${pageContext.request.contextPath}/hello">点击登录</a>
	</shiro:guest>
	<shiro:user>
		<shiro:hasPermission name="app1:exit:view">
			<a
				href="${pageContext.request.contextPath}/getpermission?key=645ba612-370a-43a8-a8e0-993e7a590cf0&username=<shiro:principal/>">获取权限标识</a>
		</shiro:hasPermission>
		<shiro:hasPermission name="app1:exit:view">
			<a
				href="${pageContext.request.contextPath}/loginout?key=645ba612-370a-43a8-a8e0-993e7a590cf0">退出</a>
		</shiro:hasPermission>
	</shiro:user>
	<shiro:user>
	  	你拥有的权限:      ${permissions}
		</shiro:user>
	<shiro:user>
		<br />
	功能菜单
	<br />
		<c:forEach items="${menus}" var="m">
			<c:if test="${m.type eq  moduleType}">
				<a href="${pageContext.request.contextPath}${m.url}" target="content">${m.name}</a><br />
			</c:if>
			
		</c:forEach>
	</shiro:user>
	<br />
	<shiro:authenticated>
        欢迎<shiro:principal />登录<br />
		<shiro:hasRole name="role1">
            您拥有role1角色<br />
		</shiro:hasRole>


		<shiro:lacksRole name="role1">
            您没有role1角色<br />
		</shiro:lacksRole>
		<shiro:lacksRole name="role2">
            您没有role2角色<br />
		</shiro:lacksRole>
		<shiro:hasPermission name="app1:ok:view">
			<button name="btnConfirm">确认</button>
		</shiro:hasPermission>

		<h2>设置会话属性</h2>
		<form action="${pageContext.request.contextPath}/attr" method="post">
			键：<input type="text" name="key"> 值：<input type="text"
				name="value"> <input type="submit" value="设置会话属性">
		</form>
		<h2>获取会话属性</h2>
		<form action="${pageContext.request.contextPath}/attr" method="get">
			键：<input type="text" name="key"> 值：<input type="text"
				value="${value}"> <input type="submit" value="获取会话属性">
		</form>
	</shiro:authenticated>
</body>
</html>