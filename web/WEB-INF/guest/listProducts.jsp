
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="w-100 text-center my-5 b-r">Список продуктов</h1>
<div class="row d-flex justify-content-center ">
    <div class="card m-2 border-0" style="width: 50rem;">
        <table class="table">
            <thead>
              <tr>
                <th class="col col-sm-1">№</th>
                <th class="col col-lg-2 t-c">Фото</th>
                <th class="col col-lg-5">Продукт</th>
                <th class="col col-lg-2">Производитель</th>
                <th class="col col-lg-2">Количество</th>
                <th class="col col-lg-3 t-c">Цена</th>
              </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${listProducts}" varStatus="status">
                    <tr>
                      <th scope="row">${status.index+1}</th>
                      <!--<td><img src="insertFile/${product.cover.url}" class="card-img-top" alt="..." width="50"></td>-->
                      <td><img src="insertFile/${product.cover.url}" class="card-img-top" alt="..."  height="100"></td>

                      <td>${product.title}</td>
                      <td>${product.manufacturer}</td>
                      <td class="t-r">${product.amountShop} </td>
                      <td class="t-r">${product.price} &#x20AC;</td>
                    </tr>
                </c:forEach>
            </tbody>
          </table>
    </div>
</div>