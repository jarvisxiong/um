<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>
    自动跳转
    <%--
    403 - 缺少权限
    --%>
    </title>
</head>

<body>
<div>
    <%--
    <div><h1>你没有访问该页面的权限.</h1></div>
    <div><a href="<c:url value="/"/>">返回首页</a> <a href="<c:url value="/j_spring_security_logout"/>">退出登录</a></div>
    --%>
    <div style="display:none">如果系统没有自动跳转，请点击<a href="<c:url value="/"/>">这里进入</a> </div>
    
    <div style="display:none">
            
        <div>系统执行发生错误，信息描述如下：</div>  

        <div>错误状态代码是：${pageContext.errorData.statusCode}</div>  

        <div>错误发生页面是：${pageContext.errorData.requestURI}</div>  

        <div>错误信息：${pageContext.exception}</div>  

        <div>  

        错误堆栈信息：<br/>  

        <c:forEach var="trace" items="${pageContext.exception.stackTrace}">  

        <p>${trace}</p>  

        </c:forEach>  

        </div>  
    </div>
</div>
<script type="text/javascript">
    window.location.href='<c:url value="/"/>';
</script>
</body>
</html>