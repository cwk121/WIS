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

    <h1>Inventory</h1>
    <hr />

    <c:choose>
        <c:when test="${status == true}">
            <div class="ui positive message">
                <i class="close icon"></i>
                <div class="header">
                    Transfer successful.
                </div>
            </div>
        </c:when>
        <c:when test="${status == false}">
            <div class="ui negative message">
                <i class="close icon"></i>
                <div class="header">
                    Transfer failed.
                </div>
            </div>
        </c:when>
    </c:choose>

    <h2>Transfer</h2>
    <form class="ui form" action="${pageContext.request.contextPath}/inventory/transfer" method="POST">
        <div class="inline fields">
            <div class="from field">
                <select id="from" name="from" class="ui fluid dropdown">
                    <option value="">From</option>
                    <option value="CWB">CWB</option>
                    <option value="TKO">TKO</option>
                </select>
            </div>
            <div class="to field">
                <select id="to" name="to" class="ui fluid dropdown">
                    <option value="">To</option>
                    <option value="CWB">CWB</option>
                    <option value="TKO">TKO</option>
                </select>
            </div>
            <div class="field">
                <label for="pCode">Product Code</label>
                <input type="text" id="pCode" name="pCode" required />
            </div>
            <div class="field">
                <label for="quantity">Quantity</label>
                <input type="number" id="quantity" name="quantity" required />
            </div>
            <button class="ui button" type="submit">Submit</button>
        </div>
    </form>

    <hr />

    <h2>Search</h2>
    <form class="ui action input" method="get" action="${pageContext.request.contextPath}/inventory">
        <input type="text" name="q" placeholder="Search...">
        <select name="search" class="ui compact selection dropdown">
            <option value="productCode">ProductCode</option>
            <option value="location">Location</option>
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
    <c:when test="${inventories.isEmpty()}">
        <div>No inventory found</div>
    </c:when>
    <c:otherwise>
    <table class="ui striped celled table">
        <thead>
        <tr>
            <th>location</th>
            <th>productCode</th>
            <th>quantity</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${inventories}" var="inventory">
            <tr>
                <td>${inventory.location}</td>
                <td>${inventory.productCode}</td>
                <td>${inventory.quantity}</td>
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
    $(document).ready( function () {
        $('select.dropdown').dropdown();

        $('.message .close').on('click', function() {
            $(this).closest('.message').transition('fade');
        });

        $('#from, #to').each(function() {
            $(this).on('change', () => {
                if($('#from').val() === $('#to').val()){
                    $('.from.field').addClass('error');
                    $('.to.field').addClass('error');
                } else {
                    $('.from.field').removeClass('error');
                    $('.to.field').removeClass('error');
                }
            });
        });

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
                $('.item.prev').attr('href', '/inventory?page=' + (page-1));
            }
            if($('tbody').children().length < 100) {
                $('.item.next')
                    .addClass('disabled')
                    .attr('href', '#');
            } else {
                $('.item.next').attr('href', '/inventory?page=' + (page+1));
            }
        }
        navigation();
    });
</script>
</body>
</html>
