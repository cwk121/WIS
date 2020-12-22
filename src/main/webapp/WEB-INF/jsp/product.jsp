<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="a light version Warehouse Inventory System">

    <title>Warehouse Inventory System</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/semantic.min.css">
</head>
<body>
<div class="ui container">
    <jsp:include page="menu.jsp" />

    <h1>Product</h1>
    <hr />

    <h2>Search</h2>
    <form class="ui action input" method="get" action="${pageContext.request.contextPath}/product">
        <input type="text" name="q" placeholder="Search...">
        <select name="search" class="ui compact selection dropdown">
            <option value="code">Code</option>
            <option value="name">Name</option>
        </select>
        <button class="ui button">Search</button>
    </form>

    <hr />

    <div class="ui two item menu navigation">
        <a class="item prev">
            Previous
        </a>
        <a class="item next">
            Next
        </a>
    </div>

    <c:choose>
    <c:when test="${products.isEmpty()}">
        <div>No product found</div>
    </c:when>
    <c:otherwise>
    <table class="ui striped celled table">
        <thead>
            <tr>
                <th>code</th>
                <th>name</th>
                <th>weight</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.code}</td>
                <td>${product.name}</td>
                <td>${product.weight}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:otherwise>
    </c:choose>

    <div class="ui two item menu navigation">
        <a class="item prev">
            Previous
        </a>
        <a class="item next">
            Next
        </a>
    </div>

    <br />
</div>

<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/semantic.min.js"></script>
<script>
    $( document ).ready(function() {
        $('.ui.dropdown').dropdown();

        function navigation() {
            let searchParams = new URLSearchParams(window.location.search);
            let page = searchParams.get('page')? parseInt(searchParams.get('page')) : 0;
            let rows = $('tbody').children().length;

            if(searchParams.get('search') || (page <= 0 && rows < 100)) {
                $('.navigation').hide();
            }

            if(page <= 0) {
                $('.item.prev')
                    .addClass('disabled')
                    .attr('href', '#');
            } else {
                $('.item.prev').attr('href', '/product?page=' + (page-1));
            }
            if($('tbody').children().length < 100) {
                $('.item.next')
                    .addClass('disabled')
                    .attr('href', '#');
            } else {
                $('.item.next').attr('href', '/product?page=' + (page+1));
            }
        }
        navigation();
    });
</script>
</body>
</html>
