package com.doriss.servlet;

import com.doriss.dao.CategoryDao;
import com.doriss.dao.UserDao;
import com.doriss.daoimpl.CategoryDaoImpl;
import com.doriss.daoimpl.UserDaoImpl;
import com.doriss.entity.Category;
import com.doriss.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenhaha on 2018/9/17.
 */
@WebServlet( "/toindex")
public class IndexServlet extends BaseServlet{
    CategoryDao categoryDao = new CategoryDaoImpl();
    UserDao userDao =  new UserDaoImpl();

    public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//       通过web.xml进入首页
        List<Category> categoryList = categoryDao.queryAll();
        request.getSession().setAttribute("categoryList",categoryList);
//        Cookie[] cookies = request.getCookies();
//        String username = this.getCookieByName(cookies,"username");
//        String password  = this.getCookieByName(cookies,"password");
//         if(username != null && password != null){
//             User user = userDao.login(username,password);
//             if(user == null){
//                 request.getRequestDispatcher("/login.jsp").forward(request,response);
//                 return;
//             }else {
//                 request.getSession().setAttribute("user",user);
//             }
//         }
        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
//        public String getCookieByName(Cookie[] cookies,String name) {
//            if (cookies != null) {
//                for (Cookie cookie : cookies) {
//                    if (cookie.getName().equals(name)) {
//                        return cookie.getValue();
//                    }
//                }
//            }return null;
//        }
       }
