package com.doriss.servlet;

import com.doriss.dao.UserDao;
import com.doriss.daoimpl.UserDaoImpl;
import com.doriss.entity.User;
import com.doriss.utlis.DataSourceUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;
import java.util.UUID;

/**
 * Created by chenhaha on 2018/9/12.
 */
@WebServlet(urlPatterns = "/user")
public class UserServlet extends BaseServlet {
    UserDao userDao = new UserDaoImpl();
    public void ajax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String name = req.getParameter("username");
       boolean result = userDao.ajax(name);
        String mag="该用户名已经存在";
        if(result) {
           mag = "这个名字可以用";
        }
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().write(mag);
    } //user?method=exit
    public void exit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");
        String auto = req.getParameter("auto");
        User user = userDao.login(username,password);
        if(user != null) {//登陆成功
            if("remember".equals(remember)) {
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(7 * 24 * 3600);
                cookie.setPath("/");
                resp.addCookie(cookie);
            }
            if("remenber".equals(auto)){
                Cookie cookie = new Cookie("username", username);
                Cookie cookie1 = new Cookie("password",password);
                cookie.setMaxAge(7*24*3600);
                cookie1.setMaxAge((7*24*3600));
                cookie.setPath("/");
                cookie1.setPath("/");
                resp.addCookie(cookie);
                resp.addCookie(cookie1);
            }
            req.getSession().setAttribute("user",user);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);

        }else {//登陆失败
            req.setAttribute("msg","登陆失败");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
        if("remenber".equals(auto)){
//           Cookie cookie = new Cookie("",)
        }
    }
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String ,String[]> properties = req.getParameterMap();
        User user = new User();
        try{
            BeanUtils.populate(user,properties);
            user.setUid(UUID.randomUUID().toString());
            userDao.add(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("注册出错");
        }
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }

}
