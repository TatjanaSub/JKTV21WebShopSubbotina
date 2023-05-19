/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Customer;
import entity.User;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CustomerFacade;
import session.ProductFacade;
import session.UserFacade;
import tools.EncryptPassword;
import tools.PropertyLoader;

/**
 *
 * @author Kasutaja
 */
@WebServlet(name = "LoginServlet", loadOnStartup = 1, urlPatterns = {
    "/about",
    "/loginForm",
    "/login",
    "/listProducts",
    "/newCustomer",
    "/createCustomer",
})

public class LoginServlet extends HttpServlet {

    @EJB private UserFacade userFacade;
    @EJB private CustomerFacade customerFacade;
    @EJB private ProductFacade productFacade;
    
    static enum Roles {ADMINISTRATOR,MANAGER,USER};
    
    @Override
    public void init() throws ServletException {
        super.init();
        if(userFacade.count()>0) return;
        Customer customer = new Customer();
        customer.setFirstname("Tatjana");
        customer.setLastname("Subbotina");
        customer.setPhone("545454545");
        customer.setMoney(Integer.parseInt("100"));
        customerFacade.create(customer);
        User user = new User();
        user.setLogin("Administrator");
        EncryptPassword ep = new EncryptPassword();
        user.setSalt(ep.getSalt());
        user.setPassword(ep.getProtectedPassword("12345", user.getSalt()));
        user.setCustomer(customer);
        user.getRoles().add(LoginServlet.Roles.ADMINISTRATOR.toString());
        user.getRoles().add(LoginServlet.Roles.MANAGER.toString());
        user.getRoles().add(LoginServlet.Roles.USER.toString());
        userFacade.create(user);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
            case "/about":
                request.getRequestDispatcher(PropertyLoader.getPath("about")).forward(request, response);
                break;
            case "/loginForm":
                request.getRequestDispatcher(PropertyLoader.getPath("loginForm")).forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                User user = userFacade.findByLogin(login);
                if(user == null){
                    request.setAttribute("info", "Нет такого пользователя или неправильный пароль.");
                    request.getRequestDispatcher(PropertyLoader.getPath("loginForm")).forward(request, response);
                    break;
                }
                EncryptPassword ep = new EncryptPassword();
                password = ep.getProtectedPassword(password, user.getSalt());
                if(!password.equals(user.getPassword())){
                    request.setAttribute("info", "Нет такого пользователя или неправильный пароль.");
                    request.getRequestDispatcher(PropertyLoader.getPath("loginForm")).forward(request, response);
                    break;
                }
                HttpSession session = request.getSession(true);
                session.setAttribute("authUser", user);
                 request.setAttribute("info", "Привет, "+user.getLogin()+"!");   
                request.getRequestDispatcher(PropertyLoader.getPath("index")).forward(request, response);
                break;
            
            case "/listProducts":
                request.setAttribute("listProducts", productFacade.findAll());
                request.getRequestDispatcher(PropertyLoader.getPath("listProducts")).forward(request, response);
                break;
            case "/newCustomer":
                request.getRequestDispatcher(PropertyLoader.getPath("createCustomer")).forward(request, response);
                break;
            case "/createCustomer":
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String phone = request.getParameter("phone");
                String money = request.getParameter("money");
                login = request.getParameter("login");
                password = request.getParameter("password");
                if(firstname == null || firstname.isEmpty() || lastname == null || lastname.isEmpty()
                        || phone == null || phone.isEmpty() || money == null || money.isEmpty()|| login == null || login.isEmpty() || password == null || password.isEmpty()){
                    request.setAttribute("info", "Не все поля заполнены");
                    request.getRequestDispatcher("/newCustomer.jsp").forward(request, response);
                    break;
                }
                Customer customer = new Customer();
                
                customer.setFirstname(firstname);
                customer.setLastname(lastname);
                customer.setPhone(phone);
                customer.setMoney(Integer.parseInt(money));
                customerFacade.create(customer);
                user = new User();
                user.setLogin(login);
                ep = new EncryptPassword();
                user.setSalt(ep.getSalt());
                user.setPassword(ep.getProtectedPassword(password, user.getSalt()));
                user.setCustomer(customer);
                user.getRoles().add(LoginServlet.Roles.USER.toString());
                try {
                    userFacade.create(user);
                } catch (Exception e) {
                    request.setAttribute("info", "Такой пользователь уже существует");
                    request.getRequestDispatcher("/newCustomer.jsp").forward(request, response);
                    break;
                }
                
                request.setAttribute("info", "Покупатель зарегистрирован");
                request.getRequestDispatcher(PropertyLoader.getPath("index")).forward(request, response);
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
