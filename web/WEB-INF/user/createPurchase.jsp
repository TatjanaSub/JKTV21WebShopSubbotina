<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 text-center my-5">Новая покупка</h1>
<div class="row d-flex justify-content-center">
    <div class="card m-2 border-0" style="width: 50rem;">
        <form action="createPurchase" method="POST">
            <h4>Список покупателей</h4>
            <div class="mb-3 row">
                    <label for="selectCustomers" class="col-sm-3 col-form-label"> Выберите покупателя:</label>
                    <div class="col-sm-9">
                        <select class="form-select" name="customerId" id="selectCustomers">
                            <c:forEach var="customer" items="${listCustomers}">
                                <option value="${customer.id}">
                                    ${customer.firstname} ${customer.lastname}; в наличии: ${customer.money} &#x20AC;
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            <h4>Список продуктов</h4>
            <div class="mb-3 row">
                <label for="productSelect" class="col-sm-3 col-form-label">Выберите продукт:</label>
                <div class="col-sm-9">
                    <select class="form-select" name="productId" id="productSelect">
                        <c:forEach var="product" items="${listProducts}">
                            <option value="${product.id}">
                                ${product.title}, производитель: ${product.manufacturer}, цена: ${product.price} &#x20AC; , в наличии: ${product.amountShop}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputAmountCustomer" class="col-sm-3 col-form-label">Выберите количество:</label>
                <div class="col-sm-9">
                  <input type="number" class="w-100  t-r" id="inputAmountCustomer" name="amountCustomer" value="1">
                </div>
            </div>
            <div class="mb-3 row">
                <div class="col-sm-12 d-flex justify-content-end">
                    <input class="btn btn-primary" type="submit" value="Совершить покупку">
                </div>
            </div>
        </form>
    </div>
</div>
