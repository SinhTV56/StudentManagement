package Sinh.Controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Sinh.Database.UserDB;
import Sinh.Model.User;

public class UserListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String contextURL = request.getContextPath();
        if ("add".equals(action)) {
            try {
                String email = request.getParameter("email");
                String firstName = request.getParameter("firstname");
                String lastName = request.getParameter("lastname");
                User user = new User(email, "123", firstName, lastName);
                UserDB.insertUser(user);
                request.setAttribute("user", user);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserListServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if ("edit".equals(action)) {
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            User user = new User(email, "123", firstName, lastName);
            UserDB.editUser(user);
            
        } else if ("get-input".equals(action)) {
            getServletContext().getRequestDispatcher("/add.jsp")
                    .forward(request, response);
         
        } else if ("get-edit".equals(action)) {
            String email = request.getParameter("email");
            User user = UserDB.getUser(email);
            request.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/add.jsp")
                    .forward(request, response);

        } else if ("delete".equals(action)) {
            String email = request.getParameter("email");
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
            User user = new User(email, "123", firstName, lastName);
            UserDB.removeUser(user);
        }

        // Goi database de lay list user
        List<User> users = UserDB.getUsers();

        request.setAttribute(
                "userList", users);
        getServletContext()
                .getRequestDispatcher("/userList.jsp")
                .forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
