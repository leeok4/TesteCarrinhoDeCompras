<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Confirmar carrinho de compras</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
</head>
<body>
  <jsp:include page="_header.jsp" />
 
  <jsp:include page="_menu.jsp" />
 
  <fmt:setLocale value="en_US" scope="session"/>
 
  <div class="page-title">Confirmacao</div>
 
 
 
  <div class="customer-info-container">
      <h3>Customer Information:</h3>
      <ul>
          <li>Nome: ${myCart.customerInfo.name}</li>
          <li>Email: ${myCart.customerInfo.email}</li>
          <li>Telefone: ${myCart.customerInfo.phone}</li>
          <li>Endereco: ${myCart.customerInfo.address}</li>
      </ul>
      <h3>Resumo do carrinho:</h3>
      <ul>
          <li>Quantidade: ${myCart.quantityTotal}</li>
          <li>Total:
          <span class="total">
            <fmt:formatNumber value="${myCart.amountTotal}" type="currency"/>
          </span></li>
      </ul>
  </div>
 
  <form method="POST"
      action="${pageContext.request.contextPath}/shoppingCartConfirmation">
 
      <!-- Edit Cart -->
      <a class="navi-item"
          href="${pageContext.request.contextPath}/shoppingCart">Editar carrinho</a>
 
      <!-- Edit Customer Info -->
      <a class="navi-item"
          href="${pageContext.request.contextPath}/shoppingCartCustomer">Editar 
          informacoes do comprador</a>
 
      <!-- Send/Save -->
      <input type="submit" value="Confirmar" class="button-send-sc" />
  </form>
 
  <div class="container">
 
      <c:forEach items="${myCart.cartLines}" var="cartLineInfo">
          <div class="product-preview-container">
              <ul>
                  <li><img class="product-image"
                      src="${pageContext.request.contextPath}/productImage?code=${cartLineInfo.productInfo.code}" /></li>
                  <li>Code: ${cartLineInfo.productInfo.code} <input
                      type="hidden" name="code" value="${cartLineInfo.productInfo.code}" />
                  </li>
                  <li>Nome: ${cartLineInfo.productInfo.name}</li>
                  <li>Preco: <span class="price">
                     <fmt:formatNumber value="${cartLineInfo.productInfo.price}" type="currency"/>
                  </span>
                  </li>
                  <li>Quantidade: ${cartLineInfo.quantity}</li>
                  <li>Subtotal:
                    <span class="subtotal">
                       <fmt:formatNumber value="${cartLineInfo.amount}" type="currency"/>
                    </span>
                  </li>
              </ul>
          </div>
      </c:forEach>
 
  </div>
 
  <jsp:include page="_footer.jsp" />
 
</body>
</html>