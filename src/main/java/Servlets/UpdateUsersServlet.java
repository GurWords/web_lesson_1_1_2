package Servlets;

import Model.User;
import Service.UsersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/updateusers")
public class UpdateUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("name_old",req.getParameter("name_old"));
        httpSession.setAttribute("age_old",req.getParameter("age_old"));
        req.getRequestDispatcher("updateusers.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name_old = (String) req.getSession().getAttribute("name_old");
        Long age_old = Long.parseLong((String) req.getSession().getAttribute("age_old"));
        String name_new = req.getParameter("name_new");
        Long age_new = Long.parseLong(req.getParameter("age_new"));
        User odlUser = new User.Builder().withName(name_old).withAge(age_old).build();
        User newUser = new User.Builder().withName(name_new).withAge(age_new).build();
        UsersService.getInstance().updateUser(odlUser, newUser);
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }
}
