<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 text-center my-5">Пополнение кошелька покупателя</h1>
<div class="row d-flex justify-content-center">
    <div class="card m-2 border-0" style="width: 30rem;">
        <form action="addMoney" method="POST">
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
            
            <div class="mb-3 row">
                <label for="inputAmountCustomer" class="col-sm-3 col-form-label">Введите сумму:</label>
                <div class="col-sm-9">
                  <input type="number" class="w-100  t-r" id="inputAmountCustomer" name="amountCustomer" list="amountCustomerList" 
                    step="10" min="10" max="1000" value="0">
                </div>
                
            </div>
            <div class="mb-3 row">
                <div class="col-sm-12 d-flex justify-content-end">
                    <input class="btn btn-primary" type="submit" value="Пополнить">
                </div>
            </div>
        </form>
        
        <datalist id="amountCustomerList">
            <option value="50" />
            <option value="100" />
            <option value="150" />
            <option value="200" />
            <option value="250" />
            <option value="300" />
            <option value="500" />
        </datalist>
    </div>
</div>