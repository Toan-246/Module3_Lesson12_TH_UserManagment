package com.codegym.controller;

import com.codegym.dao.UserDao;
import com.codegym.model.User;
import com.codegym.service.IUserService;
import com.codegym.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {
    private IUserService userService;

    public UserServlet() {
        this.userService = new UserService(new UserDao());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/user/create.jsp");
                dispatcher.forward(request, response);
            }
            case "view": {
                int id = Integer.parseInt(request.getParameter("id"));
                User user = userService.findById(id);
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/user/view.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "edit": {
                int id = Integer.parseInt(request.getParameter("id"));
                User user = userService.findById(id);
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/user/edit.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                User user = userService.findById(id);
                request.setAttribute("user", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/user/delete.jsp");
                dispatcher.forward(request, response);
                break;
            }
            default: {
                List<User> users = userService.findAll();
                request.setAttribute("listUser", users);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/user/list.jsp");
                dispatcher.forward(request, response);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String country = request.getParameter("country");
                User user = new User(name, email, country);
                userService.create(user);
                response.sendRedirect("/users");
                break;
            }
            case "edit": {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String country = request.getParameter("country");
                User user = new User(name, email, country);
                userService.updateById(id, user);
                response.sendRedirect("/users");
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                userService.deleteById(id);
                response.sendRedirect("/users");
                break;
            }
        }
    }
}
