package Servlets;

import Model.User;
import Service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class UsersServlets extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("users.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            if (req.getParameter("name") != null){
                String name = req.getParameter("name");
                Long age = Long.parseLong(req.getParameter("age"));
                User user = new User.Builder().withName(name).withAge(age).build();
                UsersService.getInstance().insertUser(user);
            }
            if (req.getParameter("name_new") != null) {
                String name_new = req.getParameter("name_new");
                Long age_new = Long.parseLong(req.getParameter("age_new"));
                User newUser = new User.Builder().withName(name_new).withAge(age_new).build();
                UsersService.getInstance().updateUser(newUser);
            }
            if (req.getParameter("nameDelete") != null){
                String nameDelete = req.getParameter("nameDelete");
                Long ageDelete = Long.parseLong(req.getParameter("ageDelete"));
                User userDelete = new User.Builder().withName(nameDelete).withAge(ageDelete).build();
                UsersService.getInstance().deleteUser(userDelete);
            }
        req.getRequestDispatcher("users.jsp").forward(req,resp);
    }
}
