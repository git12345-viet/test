<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
s   
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Ogani Template">
        <meta name="keywords" content="Ogani, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>FastFood</title>

        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <style>
            .button-container {
                display: flex;
                justify-content: center; /* Căn giữa theo chiều ngang */
                gap: 10px; /* Khoảng cách giữa các nút, nếu cần */
            }

            img {
                max-width: 100px; /* Thiết lập chiều rộng tối đa */
                max-height: 100px; /* Thiết lập chiều cao tối đa */
            }
        </style>
    </head>

    <body>
        <jsp:include page="panner.jsp"></jsp:include>

            <!-- Breadcrumb Section Begin -->
            <section class="breadcrumb-section set-bg" data-setbg="img/breadcrumb.jpg">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 text-center">
                            <div class="breadcrumb__text">
                                <h2>Shopping Cart</h2>
                                <div class="breadcrumb__option">
                                    <a href="content">Home</a>
                                    <span>Shopping Cart</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Breadcrumb Section End -->

            <!-- Shoping Cart Section Begin -->
            <section class="shoping-cart spad">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="shoping__cart__table">
                                <table>
                                    <thead>
                                        <tr>
                                            <th class="shoping__product">Products</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                            <th>Total</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <tbody>
                                    <form action="cart" > 
                                        <input type="hidden" name="service" value="updateCart">
                                    <c:forEach items="${cart.cartItems}" var="entry">

                                        <tr>
                                            <td class="shoping__cart__item">
                                                <img src="${entry.key.image}" alt="ProductImage">
                                                <h5>${entry.key.getName()}</h5>
                                            </td>
                                            <td class="shoping__cart__price">
                                                ${entry.key.getPrice()}_VND
                                            </td>
                                            <td class="shoping__cart__quantity">
                                                <div class="quantity">
                                                    <input type="number" name="product${entry.key.getID()}" value="${entry.value}" min="0">
                                                </div>
                                            </td>
                                            <td class="shoping__cart__total">
                                                ${entry.key.getPrice() * entry.value}_VND
                                            </td>

                                        </tr>
                                    </c:forEach>





                                    </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="shoping__cart__btns">
                            <a href="customer" class="btn btn-primary cart-btn">Back to home</a>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="shoping__checkout">
                            <h5>Cart Total</h5>
                            <ul>
                                <li>Total <span>${total}_VND</span></li>
                            </ul>
                            <div class="button-container">
                                <a href="cart?service=clearCart" class="primary-btn">Clear Cart</a>
                                <input class="primary-btn" type="submit" value="Update Cart">
                                <c:if test="${sessionScope.user.getRole() == null}" >
                                    <a href="login.jsp" class="primary-btn">Go to checkout</a>
                                </c:if>
                                <c:if test="${sessionScope.user.getRole() != null}" >
                                    <a href="cart?service=displayCheckout" class="primary-btn">Go to checkout</a>
                                </c:if>
                            </div>

                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Shoping Cart Section End -->

        <jsp:include page="footer.jsp"></jsp:include>


        <!-- Js Plugins -->
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
        <!-- Thêm mã JavaScript -->








    </body>

</html>