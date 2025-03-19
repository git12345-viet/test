<%-- 
    Document   : menu
    Created on : Oct 31, 2023, 10:53:21 PM
    Author     : havie
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user.role != 1}">

    <div class="col-lg-3">
        <div class="hero__categories">
            <div class="hero__categories__all">
                <i class="fa fa-bars"></i>
                <span>Categories</span>
            </div>
            <ul>
                <li onmouseover="this.style.backgroundColor = '#3990ad'" onmouseout="this.style.backgroundColor = ''">
                    <a href="customer?service=getProductsByCate&cateId=0">All</a>
                </li>
                <c:forEach items="${cates}" var="c">
                    <li onmouseover="this.style.backgroundColor = '#3990ad'" onmouseout="this.style.backgroundColor = ''">
                        <a href="customer?service=getProductsByCate&cateId=${c.getID()}">${c.getName()}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</c:if>

<c:if test="${sessionScope.user.role == 1}">

    <div class="col-lg-3">
        <div class="hero__categories">
            <div class="hero__categories__all">
                <i class="fa fa-bars"></i>
                <span>Manager</span>
            </div>
            <ul>

                <li onmouseover="this.style.backgroundColor = '#3990ad'" onmouseout="this.style.backgroundColor = ''">
                    <a href="accountmanager">Customer Manager</a>
                </li>
                <li onmouseover="this.style.backgroundColor = '#3990ad'" onmouseout="this.style.backgroundColor = ''">
                    <a href="productmanager">Product Manager</a>
                </li>
                <li onmouseover="this.style.backgroundColor = '#3990ad'" onmouseout="this.style.backgroundColor = ''">
                    <a href="billmanager">Bill Manager</a>
                </li>
            </ul>
        </div>
    </div>
</c:if>

