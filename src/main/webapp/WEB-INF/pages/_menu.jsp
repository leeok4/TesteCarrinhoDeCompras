<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>   
 
 
<div class="menu-container">
  
   <a href="${pageContext.request.contextPath}/">Inicio</a>
   |
   <a href="${pageContext.request.contextPath}/productList">
      Lista de produtos
   </a>
   |
   <a href="${pageContext.request.contextPath}/shoppingCart">
      Meu Carrinho
   </a>
   |
   <security:authorize  access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
     <a href="${pageContext.request.contextPath}/orderList">
         Lista de pedidos
     </a>
     |
   </security:authorize>
   
   <security:authorize  access="hasRole('ROLE_MANAGER')">
         <a href="${pageContext.request.contextPath}/product">
                        Criar novo produto
         </a>
         |
   </security:authorize>
  
</div>