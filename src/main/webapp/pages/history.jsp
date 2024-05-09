<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order History</title>
<link rel="stylesheet" type="text/css" href="/TechnoHub/stylesheets/navigation.css" />
<link rel="stylesheet" type="text/css" href="/TechnoHub/stylesheets/cart.css" />
</head>
<body>
    <div class="top">
        <h1 class="heading">PURCHASE HISTORY <a href="${pageContext.request.contextPath}${StringUtils.SERVLET_URL_CARTDETAILS}">
            <button class="history-btn" style="margin-left: 357px;">Back to Cart</button>
        </a></h1>
    </div>
    <hr>
    	<c:set var="currentOrderID" value=""/>
    <c:forEach var="order" items="${orders}">
    	<c:if test="${currentOrderID != order.orderID}">
        <div class="container">
        	<div class= "inner-heading" style="display: flex; align-items: center; justify-content: space-between;">
        		<div>
    	        	<h1>Order # ${order.orderID}</h1>
	            	<p>${order.orderDate}</p>
        		</div>
        		<p>${order.orderStatus}</p>
        	</div>
            <table>
                <thead>
                    <tr>
                        <th>Product</th>
                        <th>Image</th>
                        <th>Quantity</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="orderProduct" items="${orderProductsList}"> 
                    <c:if test="${order.orderID == orderProduct.orderID}"> 
                    <tr>
                        <td>${orderProduct.productName}</td>
                        <td><img src="${pageContext.request.contextPath}/resources/images/products/${orderProduct.productImageOrderUrlFromPart}" alt="Product Image" class="product-image" style="width: 100px;"></td>
                        <td>${orderProduct.orderQuantity}</td>
                        <td>${orderProduct.lineTotal}</td>
                    </tr>
                    </c:if>
                    </c:forEach>
                </tbody>
            </table>
            <c:forEach var="orderProduct" items="${orderProductsList}"> 
            <c:if test="${order.orderID == orderProduct.orderID && !grandTotalDisplayed}"> 
            	<div class="totalbox" style="padding: 10px; border: 1px solid #111;">
            		<h2>Grand Total:</h2>
            		<p>${orderProduct.orderTotal}</p>
            	</div>
            	<c:set var="grandTotalDisplayed" value="true"/>
             </c:if>
             </c:forEach>
        </div>
        </c:if>
        <c:set var="currentOrderID" value="${order.orderID}"/>
        <c:set var="grandTotalDisplayed" value="false"/>
    </c:forEach>

    <br>

    </body>
</html>
