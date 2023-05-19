/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Customer;
import entity.Product;
import entity.Purchase;
import entity.User;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CustomerFacade;
import session.ProductFacade;
import session.PurchaseFacade;
import session.PurchaseFacade.PurchaseListResult;
import tools.PropertyLoader;

/**
 *
 * @author Kasutaja
 */
@WebServlet(name = "UserServlet", urlPatterns = {
    "/newPurchase",
    "/createPurchase",
    "/newAddMoney",
    "/addMoney",
    "/logout",
})

public class UserServlet extends HttpServlet {

    @EJB private PurchaseFacade purchaseFacade;
    @EJB private CustomerFacade customerFacade;
    @EJB private ProductFacade productFacade;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        if(session == null){
            request.setAttribute("info", "У вас нет прав. Авторизируйтесь");
            request.getRequestDispatcher(PropertyLoader.getPath("loginForm")).forward(request, response);
            return;
        }
        User authUser = (User) session.getAttribute("authUser");
        if(authUser == null){
            request.setAttribute("info", "У вас нет прав. Авторизируйтесь");
            request.getRequestDispatcher(PropertyLoader.getPath("loginForm")).forward(request, response);
            return;
        }
        if(!authUser.getRoles().contains(LoginServlet.Roles.USER.toString())){
            request.setAttribute("info", "У вас нет прав. Авторизируйтесь");
            request.getRequestDispatcher(PropertyLoader.getPath("loginForm")).forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            case "/newPurchase":
                request.setAttribute("listCustomers", customerFacade.findAll());
                request.setAttribute("listProducts", productFacade.findAll());
                request.getRequestDispatcher(PropertyLoader.getPath("createPurchase")).forward(request, response);
                break;
            case "/createPurchase":
                String customerId = request.getParameter("customerId");
                String productId = request.getParameter("productId");
                Customer customer = customerFacade.find(Long.parseLong(customerId));
                Product product = productFacade.find(Long.parseLong(productId));
                String amountCustomer = request.getParameter("amountCustomer");
                Integer priceCustomer = product.getPrice();
                Purchase purchase = new Purchase();
                purchase.setCustomer(customer);
                purchase.setProduct(product);
                purchase.setAmountCustomer(Integer.parseInt(amountCustomer));
                purchase.setPriceCustomer(priceCustomer);
                purchase.setTakeOfProduct(new GregorianCalendar().getTime());
                purchaseFacade.create(purchase);
                
                product.setAmountShop(product.getAmountShop()- purchase.getAmountCustomer());
                productFacade.edit(product);
                
                customer.setMoney(customer.getMoney() - purchase.getAmountCustomer()*purchase.getPriceCustomer());
                customerFacade.edit(customer);
                
                request.setAttribute("info", "Вы купили '"+product.getTitle()+"'на сумму: "+purchase.getAmountCustomer()*purchase.getPriceCustomer()+" &#x20AC, у Вас осталось: "+customer.getMoney()+" &#x20AC");
                request.getRequestDispatcher(PropertyLoader.getPath("index")).forward(request, response);
                break;
            case "/newAddMoney":
                request.setAttribute("listCustomers", customerFacade.findAll());
                request.getRequestDispatcher(PropertyLoader.getPath("addMoney")).forward(request, response);
                break;
            case "/addMoney":
                customerId = request.getParameter("customerId");
                Customer customer2 = customerFacade.find(Long.parseLong(customerId));
                if (customer2 != null) {
                   String money2 = request.getParameter("amountCustomer");
                   customer2.setMoney(Integer.parseInt(money2)+ customer2.getMoney());
                   customerFacade.edit(customer2);
                   request.setAttribute("info", "Деньги покупателю добавлены");
                } else {
                    request.setAttribute("info", "Покупатель с ID " + customerId + " не найден");
                }
                request.getRequestDispatcher(PropertyLoader.getPath("index")).forward(request, response);
                break;

            
            case "/logout":
                session = request.getSession(false);
                if(session != null){
                    session.invalidate();
                    request.setAttribute("info", "Вы вышли");
                }
//                request.getRequestDispatcher("/index.jsp").forward(request, response);
                response.sendRedirect(request.getContextPath()+"/index.jsp");
                break;
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
