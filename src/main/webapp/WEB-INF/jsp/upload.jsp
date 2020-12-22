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

    <h1>Upload Product and Inventory</h1>
    <hr />

    <c:choose>
        <c:when test="${status == true}">
            <div class="ui positive message">
                <i class="close icon"></i>
                <div class="header">
                    Upload successful.
                </div>
            </div>
        </c:when>
        <c:when test="${status == false}">
            <div class="ui negative message">
                <i class="close icon"></i>
                <div class="header">
                    Upload failed.
                </div>
            </div>
        </c:when>
    </c:choose>

    <h2>Product</h2>
    <form action="${pageContext.request.contextPath}/product/upload" method="POST" enctype="multipart/form-data">
        <div class="ui placeholder segment">
            <div class="ui icon header">
                <i class="file alternate outline icon"></i>
                Select Product csv file to upload.
            </div>
            <div class="inline">
                <input type="file" accept=".csv" name="file" class="ui secondary button" required />
                <input type="submit" class="ui primary button" value="Upload" />
            </div>
        </div>
    </form>

    <h2>Inventory</h2>
    <form action="${pageContext.request.contextPath}/inventory/upload" method="POST" enctype="multipart/form-data">
        <div class="ui placeholder segment">
            <div class="ui icon header">
                <i class="file alternate outline icon"></i>
                Select Inventory csv file to upload.
            </div>
            <div class="inline">
                <input type="file" accept=".csv" name="file" class="ui secondary button" required />
                <input type="submit" class="ui primary button" value="Upload" />
            </div>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/js/jquery-3.5.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/semantic.min.js"></script>
<script>
    $('.message .close').on('click', function() {
        $(this).closest('.message').transition('fade');
    });
</script>
</body>
</html>
