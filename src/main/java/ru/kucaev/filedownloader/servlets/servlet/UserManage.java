package ru.kucaev.filedownloader.servlets.servlet;

import ru.kucaev.filedownloader.dao.UserDAO;
import ru.kucaev.filedownloader.model.ROLE;
import ru.kucaev.filedownloader.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet(value = "/user_manage")
public class UserManage extends HttpServlet {

    @Override
    public void init(){

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final HttpSession session = req.getSession();
        final ROLE role = (ROLE) session.getAttribute("role");
        if(role.equals(ROLE.ADMIN)) {
            @SuppressWarnings("unchecked")
            final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("dao");
            try {
                req.setAttribute("users", dao.get().getAllUsers());
                req.getRequestDispatcher("/WEB-INF/view/role_manager.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException("Не удалось получить пользователя", e);
            }
        }
        resp.sendRedirect(super.getServletContext().getContextPath());
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        final HttpSession session = req.getSession();
        if(session.getAttribute("role").equals(ROLE.ADMIN)) {
            @SuppressWarnings("unchecked") final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("dao");
            User user = dao.get().getById(Integer.parseInt(req.getParameter("user_id")));
            if (user.getRole().equals(ROLE.ADMIN)) {
                user.setRole(ROLE.USER);
                if(session.getAttribute("login").equals(user.getLogin())) session.setAttribute("role", ROLE.USER);
            }
            else {
                user.setRole(ROLE.ADMIN);
                if(session.getAttribute("login").equals(user.getLogin())) session.setAttribute("role", ROLE.ADMIN);
            }
            req.setAttribute("users", dao.get().getAllUsers());
            req.getRequestDispatcher("/WEB-INF/view/role_manager.jsp").forward(req, resp);
        } else {
            throw new ServletException("Не достаточно прав для выполнения данного десйтвия");
        }
    }

}
