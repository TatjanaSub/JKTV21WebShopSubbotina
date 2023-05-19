
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="w-100 text-center my-5 b-r">Список покупателей</h1>
<div class="row d-flex justify-content-center ">
    <div class="card m-2 border-0" style="width: 40rem;">
        <table class="table">
            <thead>
              <tr>
                <th class="col col-sm-1">№</th>
                <th class="col col-lg-8">Покупатель</th>
                <th class="col col-lg-3">Телефон</th>
                <th class="col col-lg-3">Деньги</th>
              </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items="${listCustomers}" varStatus="status">
                    <tr>
                      <th scope="row">${status.index+1}</th>
                      <td>${customer.firstname} ${customer.lastname}</td>
                      <td>${customer.phone}</td>
                      <td class="t-r">${customer.money} &#x20AC;</td>
                    </tr>
                </c:forEach>
            </tbody>
          </table>
    </div>
</div>
