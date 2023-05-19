
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%
  SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy hh:mm:ss");
  getServletContext().setAttribute("dateFormat", dateFormat);
%>

<h1 class="w-100 text-center my-5">Список покупок:</h1>
<div class="row d-flex justify-content-center ">
    <div class="card m-2 border-0" style="width: 60rem;">
        <table class="table">
            <thead>
              <tr>
                <th class="col col-sm-1">Id</th>
                <th class="col col-lg-2">Покупатель</th>
                <th class="col col-lg-2">Продукт</th>
                <th class="col col-lg-1 t-r">Цена</th>
                <th class="col col-lg-1 t-r">Количество</th>
                <th class="col col-lg-2 t-r">Сумма</th>
                <th class="col col-lg-3 t-r">Дата продажи</th>
              </tr>
            </thead>
            <tbody>
                <c:forEach var="purchase" items="${listPurchases}" varStatus="status">
                    <tr>
                      <th scope="row">${status.index+1}</th>
                      <td>${purchase.customer.firstname} ${purchase.customer.lastname}</td>
                      <td>${purchase.product.title}, ${purchase.product.manufacturer}</td>
                      <td class="t-r">${purchase.priceCustomer} &#x20AC;</td>
                      <td class="t-r">${purchase.amountCustomer}</td>
                      <td class="t-r">${purchase.priceCustomer*purchase.amountCustomer} &#x20AC;</td>
                      <td class="t-r">${dateFormat.format(purchase.takeOfProduct)}</td>

                    </tr>
                </c:forEach>
            </tbody>
          </table>
    </div>
</div>