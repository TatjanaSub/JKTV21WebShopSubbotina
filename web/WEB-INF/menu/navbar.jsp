<%-- 
    Document   : navbar
    Created on : Apr 26, 2023, 8:33:17 PM
    Author     : Kasutaja
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var="USER" scope="page" value="false" />
<c:set var="MANAGER" scope="page" value="false" />
<c:set var="ADMINISTRATOR" scope="page" value="false" />
<c:forEach var="role" items="${authUser.roles}">
    <c:if test="${role eq 'ADMINISTRATOR'}">
        <c:set var="ADMINISTRATOR" value="true" scope="page" />
    </c:if>
    <c:if test="${role eq 'MANAGER'}">
        <c:set var="MANAGER" value="true" scope="page" />
    </c:if>
    <c:if test="${role eq 'USER'}">
        <c:set var="USER" value="true" scope="page" />
    </c:if>
</c:forEach>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="index.jsp">JKTV21Shop</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Продукты
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <c:if test="${MANAGER}">
                    <li><a class="dropdown-item" href="newProduct">Добавить продукт</a></li>
                </c:if>
                <li><a class="dropdown-item" href="listProducts">Список продаваемых продуктов</a></li>
          </ul>
        </li>
        
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Покупатели
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item" href="newCustomer">Добавить покупателя</a></li>
            <c:if test="${ADMINISTRATOR}">
                <li><a class="dropdown-item" href="listCustomers">Список покупателей</a></li>
            </c:if>
            <c:if test="${USER}">
                <li><a class="dropdown-item" href="newAddMoney">Добавить денег покупателю</a></li>
            </c:if>
          </ul>
        </li>
        
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Покупки
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <c:if test="${USER}">
                <li><a class="dropdown-item" href="newPurchase">Добавить покупку</a></li>
            </c:if>
            <c:if test="${MANAGER}">
                <li><a class="dropdown-item" href="listPurchases">Список покупок</a></li>
                <li><a class="dropdown-item" href="selectionPurchaseStatistics">Статистика покупок</a></li>
            </c:if>
          </ul>
        </li>
        <c:if test="${ADMINISTRATOR}">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Администратор
              </a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="changeRole">Изменить роль</a></li>
              </ul>
            </li>
        </c:if>
            
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="about">О нас</a>
        </li>
        
      </ul>
      <c:if test="${USER}">
        <span class="text-light mx-3">Логин: ${authUser.login}</span>
      </c:if>
      <ul class="navbar-nav mb-2 mb-lg-0">
        <li class="nav-item">
            <c:if test="${USER eq false}">
                <a class="nav-link " aria-current="page" href="loginForm">Войти</a>
            </c:if>
        </li>
      </ul>
      <ul class="navbar-nav mb-2 mb-lg-0">
        <li class="nav-item">
             <c:if test="${USER}">
                <a class="nav-link" aria-current="page" href="logout">Выйти</a>
            </c:if>
        </li>
      </ul>
    </div>
  </div>
</nav>
