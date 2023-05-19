/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Product;
import entity.User;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CoverFacade;
import session.ProductFacade;
import session.PurchaseFacade;
import tools.PropertyLoader;

/**
 *
 * @author Kasutaja
 */
@WebServlet(name = "ManageServlet", urlPatterns = {
    "/newProduct",
    "/createProduct",
    "/listPurchases",
    "/selectionPurchaseStatistics",
    "/purchaseStatistics",
    
})

public class ManageServlet extends HttpServlet {

    @EJB private ProductFacade productFacade;
    @EJB private PurchaseFacade purchaseFacade;
    @EJB private CoverFacade coverFacade;
    
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
        if(!authUser.getRoles().contains(LoginServlet.Roles.MANAGER.toString())){
            request.setAttribute("info", "У вас нет прав. Авторизируйтесь");
            request.getRequestDispatcher(PropertyLoader.getPath("loginForm")).forward(request, response);
            return;
        }
        String path = request.getServletPath();
        switch (path) {
            case "/newProduct":
                request.setAttribute("listCovers", coverFacade.findAll());
                request.getRequestDispatcher(PropertyLoader.getPath("createProduct")).forward(request, response);
                break;
            case "/createProduct":
                String title = request.getParameter("title");
                String coverId = request.getParameter("coverId");
                String manufacturer = request.getParameter("manufacturer");
                String amountShop = request.getParameter("amountShop");
                String price = request.getParameter("price");
                Product product = new Product();
                product.setTitle(title);
                product.setCover(coverFacade.find(Long.parseLong(coverId)));
                product.setManufacturer(manufacturer);
                product.setAmountShop(Integer.parseInt(amountShop));
                product.setPrice(Integer.parseInt(price));
                productFacade.create(product);
                request.setAttribute("info", "Продукт добавлен");
                request.getRequestDispatcher(PropertyLoader.getPath("index")).forward(request, response);
                break;
            case "/listPurchases":
                request.setAttribute("listPurchases", (purchaseFacade.findAll()));
                request.getRequestDispatcher(PropertyLoader.getPath("listPurchases")).forward(request, response);
                break;
            case "/selectionPurchaseStatistics":
                LocalDateTime localDateTime = LocalDateTime.now();
                int year = localDateTime.getYear();
                request.setAttribute("year", year);
                int yearN = localDateTime.getYear();
                request.setAttribute("yearN", yearN);
                int yearL = localDateTime.getYear();
                request.setAttribute("yearL", yearL);
                request.getRequestDispatcher(PropertyLoader.getPath("purchaseStatistics")).forward(request, response);
                break;
            case "/purchaseStatistics":
                localDateTime = LocalDateTime.now();
                year = localDateTime.getYear();
                request.setAttribute("year", year);
                
                String selectDayN = request.getParameter("selectDayN");
                String selectMonthN = request.getParameter("selectMonthN");
                String selectYearN = request.getParameter("selectYearN");
               
                
                String selectDayL = request.getParameter("selectDayL");
                String selectMonthL = request.getParameter("selectMonthL");
                String selectYearL = request.getParameter("selectYearL");
                
                PurchaseFacade.PurchaseListResult result = purchaseFacade.getListPurchase(selectYearN, selectMonthN, selectDayN, selectYearL, selectMonthL, selectDayL);
                int sumPurchases = 0;
                if (result.isError()) {
                    request.setAttribute("statistics","Ошибка в заданном периоде или дате");
                } else {
                    
                    for (int i = 0; i < result.getPurchases().size(); i++) {
                        sumPurchases = sumPurchases + result.getPurchases().get(i).getAmountCustomer() * result.getPurchases().get(i).getPriceCustomer();
                    }
                    
                    request.setAttribute("statistics", String.format("Оборот за период с %02d.%02d.%04d по %02d.%02d.%04d : <span style=\"color:red\">%d &#x20AC;</span>",
                        Integer.parseInt(selectDayN), Integer.parseInt(selectMonthN), Integer.parseInt(selectYearN),
                        Integer.parseInt(selectDayL), Integer.parseInt(selectMonthL), Integer.parseInt(selectYearL), sumPurchases));
                }
                
                request.setAttribute("selectDayN", selectDayN);
                request.setAttribute("selectMonthN", selectMonthN);
                request.setAttribute("selectYearN", selectYearN);
                request.setAttribute("selectDayL", selectDayL);
                request.setAttribute("selectMonthL", selectMonthL);
                request.setAttribute("selectYearL", selectYearL);
                
                request.getRequestDispatcher(PropertyLoader.getPath("purchaseStatistics")).forward(request, response);
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
