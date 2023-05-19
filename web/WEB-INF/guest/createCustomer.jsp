
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="w-100 text-center my-5 b-r">Новый покупатель</h1>
<div class="row d-flex justify-content-center ">
    <div class="card m-2 border-0" style="width: 30rem;">
        <form action="createCustomer" method="POST">
            <div class="mb-3 row">
                <label for="inputFirstname" class="col-sm-4 col-form-label">Имя:</label>
                <div class="col-sm-8">
                  <input type="text" class="w-100" id="inputFirstname" name="firstname" value="">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputLastname" class="col-sm-4 col-form-label">Фамилия:</label>
                <div class="col-sm-8">
                  <input type="text" class="w-100" id="inputLastname" name="lastname" value="">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputPhone" class="col-sm-4 col-form-label">Телефон:</label>
                <div class="col-sm-8">
                  <input type="text" class="w-100" id="inputPhone" name="phone" value="">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputMoney" class="col-sm-4 col-form-label">Kоличество денег:</label>
                <div class="col-sm-8">
                  <input type="number" class="w-100 t-r" id="inputMoney" name="money" value="0">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputLogin" class="col-sm-4 col-form-label">Логин: </label>
                <div class="col-sm-8">
                  <input type="text" class="w-100" id="inputLogin" name="login" value="">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputPassword" class="col-sm-4 col-form-label">Пароль: </label>
                <div class="col-sm-8">
                  <input type="text" class="w-100" id="inputPassword" name="password" value="">
                </div>
            </div>
            <div class="mb-3 row">
                <div class="col-sm-12 d-flex justify-content-end">
                    <input class="btn btn-primary" type="submit" value="Добавить">
                </div>
            </div>
        </form>
    </div>

</div>   
