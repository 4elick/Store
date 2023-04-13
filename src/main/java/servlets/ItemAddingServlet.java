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

@WebServlet("/add-item")
@MultipartConfig(maxFileSize = 16177215)
public class ItemAddingServlet extends HttpServlet {

    private ItemService itemService;

    @Override
    public void init() {
        itemService = new ItemService(new ItemRepository(JPAUtility.getEntityManagerFactory()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/pages/add_item.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Item item = new Item();

        item.setName(req.getParameter("item_name"));
        item.setDescription(req.getParameter("item_description"));
        item.setPrice(Double.parseDouble(req.getParameter("item_price")));
        InputStream inputStream;
        Part filePart = req.getPart("item_picture");

        if(filePart != null){
            inputStream = filePart.getInputStream();
            item.setPicture(inputStream.readAllBytes());
        }

        itemService.add(item);
        resp.sendRedirect(getServletContext().getContextPath());
    }



    @Override
    public void destroy() {
        JPAUtility.close();
    }
}
