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
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService(new UserRepository(JPAUtility.getEntityManagerFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req,resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getByPasswordAndEmail(req.getParameter("user_email"),Encrypter.toHexString(Encrypter.getSHA(req.getParameter("user_password"))));
        if (user!=null){
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user",user.getEmail());
            httpSession.setMaxInactiveInterval(30*60);
            Cookie userName = new Cookie("user", user.getName());
            userName.setMaxAge(30*60);
            resp.addCookie(userName);
            resp.sendRedirect(getServletContext().getContextPath());
        } else {
            getServletContext().getRequestDispatcher("/pages/login.jsp").forward(req,resp);
        }

    }

    @Override
    public void destroy() {
        JPAUtility.close();
    }
}
