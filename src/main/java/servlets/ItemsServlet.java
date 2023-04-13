package servlets;

import entities.Item;
import org.apache.commons.codec.binary.Base64;
import repositories.ItemRepository;
import services.ItemService;
import utility.JPAUtility;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet("/items")
@MultipartConfig(maxFileSize = 16177215)
public class ItemsServlet extends HttpServlet {
    private ItemService itemService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        itemService = new ItemService(new ItemRepository(JPAUtility.getEntityManagerFactory()));
    }

//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (null == req.getParameter("id")) {
            List<Item> items = itemService.getAll();
            req.setAttribute("items", items);
            getServletContext().getRequestDispatcher("/pages/allItems.jsp").forward(req, resp);
            return;
        }
        if (req.getParameter("id").isEmpty()) {
            resp.sendRedirect("/servlets/pages/add-item");
            return;
        }

        showUserById(req, resp);

    }

    private void showUserById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Item item = itemService.getById(id);
        convertTo64Encoded(req, item);
        req.setAttribute("item", item);
        getServletContext().getRequestDispatcher("/pages/item.jsp").forward(req, resp);
    }

    private void convertTo64Encoded(HttpServletRequest req, Item item) throws UnsupportedEncodingException {
        String base64Encoded = new String(Base64.encodeBase64(item.getPicture()), "UTF-8");
        req.setAttribute("item_picture", base64Encoded);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        itemService.removeById(Long.parseLong(req.getParameter("id")));
    }

    @Override
    public void destroy() {
        JPAUtility.close();
    }

}
