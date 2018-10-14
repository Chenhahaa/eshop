package com.doriss.admin;

import com.doriss.dao.ProductDao;
import com.doriss.daoimpl.ProductDaoImpl;
import com.doriss.entity.Product;
import com.doriss.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenhaha on 2018/9/29.
 */
@WebServlet("/admin/product")
public class productSevelet extends BaseServlet {

    ProductDao productDao = new ProductDaoImpl();
    public void  listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> list= productDao.queryAll();
        request.setAttribute("plist",list);
        request.getRequestDispatcher("/admin/product/list.jsp").forward(request,response);
    }
}
