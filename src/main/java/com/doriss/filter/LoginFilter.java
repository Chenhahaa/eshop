package com.doriss.filter;

import com.doriss.dao.UserDao;
import com.doriss.daoimpl.UserDaoImpl;
import com.doriss.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by chenhaha on 2018/9/28.
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        UserDao userDao = new UserDaoImpl();
        HttpServletRequest request  = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        Cookie[] cookies = request.getCookies();
        String username = this.getCookieByName(cookies,"username");
        String password  = this.getCookieByName(cookies,"password");
        if(username != null && password != null){
            User user = userDao.login(username,password);
            if(user == null){
                request.getRequestDispatcher("/login.jsp").forward(request,response);
                return;
            }else {
                request.getSession().setAttribute("user",user);
            }
        }
        chain.doFilter(req, resp);
    }

    public String getCookieByName(Cookie[] cookies,String name) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }return null;
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
