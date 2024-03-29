<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<jsp:include page="../snippets/head.jsp">
    <jsp:param name="title" value="Profile"/>
</jsp:include>
<body>
<h1>Profile</h1>
<p>Email: <c:out value="${user.email}"/></p>
<p>Password: <c:out value="${user.password}"/></p>
<h2>Links</h2>
<ul>
    <li><a href="shops">Shops</a></li>
    <li><a href="coupons">Coupons</a></li>
</ul>

<h2>My coupons</h2>
<table>
    <thead>
    <tr>
        <th>| ID |</th>
        <th>Name |</th>
        <th>Discount |</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="item" items="${coupons}">
        <tr>
            <td>| ${item.id} |</td>
            <td>${item.name} |</td>
            <td>${item.percentage} %|</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="../snippets/logout.jsp"/>
</body>
</html>
