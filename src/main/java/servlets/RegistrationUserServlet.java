package servlets;

import entities.Role;
import entities.User;
import lombok.SneakyThrows;
import repositories.UserRepository;
import services.UserService;
import utility.Encrypter;
import utility.JPAUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationUserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(new UserRepository(JPAUtility.getEntityManagerFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/registration.jsp").forward(req,resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setEmail(req.getParameter("user_email"));
        user.setName(req.getParameter("user_name"));
        user.setPassword(Encrypter.toHexString(Encrypter.getSHA(req.getParameter("user_password"))));
        user.setRole(Role.valueOf(req.getParameter("role")));
        userService.add(user);
        resp.sendRedirect(getServletContext().getContextPath());
    }

    @Override
    public void destroy() {
        JPAUtility.close();
    }
}

