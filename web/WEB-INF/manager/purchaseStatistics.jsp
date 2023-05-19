<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h3 class="w-100 d-flex justify-content-center mt-5">Статистика покупок</h3>
<div class="w-50 p-3 container">
     <div class="row">
         <div class="m-2 w-100">
             <form action="purchaseStatistics" method="POST">
                <div class="">
                    <div class="m-3 row w-75">
                        <h4>Начало периода:</h4>
                        <div class="row">
                            <div class="col ">День:</div>
                            <div class="col ms-4">Месяц:</div>
                            <div class="col ms-4">Год:</div>
                        </div>
                        <div class="col">
                            <select name="selectDayN" class="form-select form-select-sm" aria-label=".form-select-sm example" required >
                                <option value=""  selected>Выберите день</option>
                                <c:forEach var="dayN" begin="1" end="31">
                                    <option value="${dayN}"<c:if test="${selectDayN==dayN}">selected</c:if>>${dayN}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col">
                            <select name="selectMonthN" class="form-select form-select-sm" aria-label=".form-select-sm example" required>
                                <option value=""  >Выберите месяц</option>
                                <c:forEach var="monthN" begin="1" end="12">
                                    <option value="${monthN}" <c:if test="${selectMonthN==monthN}">selected</c:if>>${monthN}</option>
                                </c:forEach>
                            </select>
                        </div>    
                        <div class="col">
                            <select name="selectYearN" class="form-select form-select-sm" aria-label=".form-select-sm example" required>
                                <option value="" >Выберите год</option>
                                <c:forEach var="yearN" begin="${year-5}" end="${year}">
                                    <option value="${yearN}" <c:if test="${selectYearN==yearN}">selected</c:if>>${yearN}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="m-3 row w-75 ">
                        <h4>Конец периода:</h4>
                        <div class="row">
                            <div class="col ">День:</div>
                            <div class="col ms-4">Месяц:</div>
                            <div class="col ms-4">Год:</div>
                        </div>
                        <div class="col">
                            <select name="selectDayL" class="form-select form-select-sm" aria-label=".form-select-sm example" required>
                                <option value=""  selected>Выберите день</option>
                                <c:forEach var="dayL" begin="1" end="31">
                                    <option value="${dayL}"<c:if test="${selectDayL==dayL}">selected</c:if>>${dayL}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col">
                            <select name="selectMonthL" class="form-select form-select-sm" aria-label=".form-select-sm example" required>
                                <option value=""  >Выберите месяц</option>
                                <c:forEach var="monthL" begin="1" end="12">
                                    <option value="${monthL}" <c:if test="${selectMonthL==monthL}">selected</c:if>>${monthL}</option>
                                </c:forEach>
                            </select>
                        </div>    
                        <div class="col">
                            <select name="selectYearL" class="form-select form-select-sm" aria-label=".form-select-sm example" required>
                                <option value="" >Выберите год</option>
                                <c:forEach var="yearL" begin="${year-5}" end="${year}">
                                    <option value="${yearL}" <c:if test="${selectYearL==yearL}">selected</c:if>>${yearL}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <br>

                     <div class="mb-3 row d-flex justify-content-center">    
                         <button type="submit" class="btn btn-secondary w-50">Вычислить оборот магазина</button>
                     </div>
                 </div>
             </form>
             <div class="w-100  mx-auto d-flex  mt-5">
                <h4 >${statistics}</h4>
            </div>
         </div>
     </div>
    

</div>
        
   