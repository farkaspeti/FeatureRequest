<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<jsp:include page="../snippets/head.jsp">
    <jsp:param name="title" value="Shop"/>
</jsp:include>
<body>
<h1>Shop</h1>
<c:if test="${empty error}">
    <p>ID: ${shop.id}</p>
    <p>Name: ${shop.name}</p>
</c:if>

<p>Coupons:
    <c:if test="${empty shopCoupons}">
    <span>No associated coupons</span>
    </c:if>
    <c:if test="${not empty shopCoupons}">
<ul>
    <c:forEach var="coupon" items="${shopCoupons}">
        <li>${coupon.id} - ${coupon.name} - ${coupon.percentage}</li>
    </c:forEach>
</ul>
</c:if>
</p>
<jsp:include page="../snippets/show-error.jsp"/>
<jsp:include page="../snippets/to-profile.jsp"/>
<jsp:include page="../snippets/logout.jsp"/>
</body>
</html>
