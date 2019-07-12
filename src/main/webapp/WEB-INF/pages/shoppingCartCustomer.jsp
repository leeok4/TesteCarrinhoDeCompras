<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Informacoes do comprador</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
</head>
<body>
<jsp:include page="_header.jsp" />
<jsp:include page="_menu.jsp" />
 
<div class="page-title">Insira as informacoes do comprador</div>
 
   <form:form method="POST" modelAttribute="customerForm"
       action="${pageContext.request.contextPath}/shoppingCartCustomer">
 
       <table>
           <tr>
               <td>Nome *</td>
               <td><form:input path="name" /></td>
               <td><form:errors path="name" class="error-message" /></td>
           </tr>
 
           <tr>
               <td>Email *</td>
               <td><form:input path="email" /></td>
               <td><form:errors path="email" class="error-message" /></td>
           </tr>
 
           <tr>
               <td>Telefone *</td>
               <td><form:input path="phone" /></td>
               <td><form:errors path="phone" class="error-message" /></td>
           </tr>
 
           <tr>
               <td>Endereco *</td>
               <td><form:input path="address" /></td>
               <td><form:errors path="address" class="error-message" /></td>
           </tr>
 
           <tr>
               <td>&nbsp;</td>
               <td><input type="submit" value="Confirmar" /> <input type="reset"
                   value="Limpar" /></td>
           </tr>
       </table>
 
   </form:form>
 
 
   <jsp:include page="_footer.jsp" />
 
 
</body>
</html>