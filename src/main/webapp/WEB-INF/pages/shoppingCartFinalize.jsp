<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Finalizando a compra</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
</head>
<body>
   <jsp:include page="_header.jsp" />
 
   <jsp:include page="_menu.jsp" />
 
   <div class="page-title">Finalizar</div>
  
   <div class="container">
       <h3>Obrigado pelo seu pedido</h3>
       O numero do seu pedido e : ${lastOrderedCart.orderNum}
   </div>
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>