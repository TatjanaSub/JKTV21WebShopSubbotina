
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="w-100 text-center my-5 b-r">Новый продукт</h1>

<div class="row d-flex justify-content-center ">
    <a class="w-100 text-center my-1 " href="uploadForm">Загрузить фото продукта</a>
    <br/><br/>
    <div class="card m-2 border-0" style="width: 30rem;">
        
        <form action="createProduct" method="POST">
            
            <div class="mb-3 row">
                <label for="inputTitle" class="col-sm-4 col-form-label">Название продукта:</label>
                <div class="col-sm-8">
                  <!--<input type="text" class="w-100" id="inputTitle" name="title" value="">-->
                  <textarea class="w-100" id="inputTitle" name="title" rows="2"></textarea>

                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputManufacturer" class="col-sm-4 col-form-label">Производитель:</label>
                <div class="col-sm-8">
                  <input type="text" class="w-100" id="inputManufacturer" name="manufacturer" value="">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputAmountShop" class="col-sm-4 col-form-label">Количество:</label>
                <div class="col-sm-8">
                  <input type="number" class="w-100  t-r" id="inputAmountShop" name="amountShop" value="1">
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputPrice" class="col-sm-4 col-form-label">Цена:</label>
                <div class="col-sm-8">
                  <input type="number" class="w-100 t-r" id="inputPrice" name="price" value="0">
                </div>
            </div>
             <div class="mb-3 row">
                <label for="selectCover" class="col-sm-4 col-form-label">Фото продукта</label>
                <div class="col-sm-8">
                    <select class="form-select" name="coverId" rows="5" cols="20" id="selectCover">
                        <c:forEach var="cover" items="${listCovers}">
                            <option value="${cover.id}">${cover.description}</option>
                        </c:forEach>
                    </select>
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
