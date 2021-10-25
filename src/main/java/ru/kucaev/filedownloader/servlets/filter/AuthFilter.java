package ru.kucaev.filedownloader.servlets.filter;

import ru.kucaev.filedownloader.dao.UserDAO;
import ru.kucaev.filedownloader.model.ROLE;
import ru.kucaev.filedownloader.servlets.service.FileListUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

@WebFilter(value = "/main/*")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final String manageReq = req.getParameter("manage_req");

        @SuppressWarnings("unchecked")
        final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>) req.getServletContext().getAttribute("dao");
        final HttpSession session = req.getSession();

        if (nonNull(session) &&
                nonNull(session.getAttribute("login")) &&
                nonNull(session.getAttribute("password"))) {
            final ROLE role = (ROLE) session.getAttribute("role");
            moveToMenu(req, res, role);
        } else if (dao.get().userIsExist(login, password)) {
            final ROLE role = dao.get().getRoleByLoginPassword(login, password);
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);
            moveToMenu(req, res, role);
        } else {
            moveToMenu(req, res, ROLE.ANONYMOUS);
        }
    }

    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final ROLE role)
            throws ServletException, IOException {

        List<String> fileNameList = FileListUtil.getFileNameList();
        req.setAttribute("files", fileNameList);
        if (role.equals(ROLE.ADMIN)) {
            req.getRequestDispatcher("/WEB-INF/view/admin_page.jsp").forward(req, res);
        } else if (role.equals(ROLE.USER)) {
            req.getRequestDispatcher("/WEB-INF/view/user_page.jsp").forward(req, res);
        } else {
            if (req.getRequestURI().contains("/auth")) req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);
            else req.getRequestDispatcher("/WEB-INF/view/anonymous_page.jsp").forward(req, res);
        }
    }

    @Override
    public void destroy() {
    }
}
