package tn.iit.user.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tn.iit.user.bean.Authorization;
import tn.iit.user.bean.User;
import tn.iit.user.dao.AuthorizationDAO;
import tn.iit.user.dao.UserDao;

@WebServlet("/AuthorizationServlet")
public class AuthorizationServlet extends HttpServlet {
    private UserDao userDao;
    private AuthorizationDAO authorizationDao;

    public void init() {
        userDao = new UserDao();
        authorizationDao = new AuthorizationDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateAuthorization(request, response);
                break;
            default:
                listAuthorizations(request, response);
                break;
        }
    }

    private void listAuthorizations(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = userDao.selectAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/edit-authorizations.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        User user = userDao.selectUser(userId);
        request.setAttribute("user", user);
        String fullName = user.getNom() + " " + user.getPrenom();
        request.setAttribute("fullName", fullName);
        request.getRequestDispatcher("/edit-authorization.jsp").forward(request, response);
    }

    private void updateAuthorization(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int maximumHours = Integer.parseInt(request.getParameter("maximumHours"));

        List<Authorization> authorizations = authorizationDao.getAuthorizationsByUser(userId);
        if (authorizations.isEmpty()) {
            User user = userDao.selectUser(userId);
            Authorization authorization = new Authorization(0, user, new Date(), new Date(), maximumHours);
            authorizationDao.addAuthorization(authorization);
        } else {
            Authorization authorization = authorizations.get(0);
            authorization.setMaximumHours(maximumHours);
            authorizationDao.updateMaximumHours(authorization.getId(), maximumHours);
        }

        response.sendRedirect(request.getContextPath() + "/edit-authorization.jsp");
    }
}
