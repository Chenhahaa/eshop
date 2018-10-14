package com.doriss.servlet;

import com.alibaba.fastjson.JSON;
import com.doriss.dao.OrderDao;
import com.doriss.dao.OrderItemDao;
import com.doriss.dao.ProductDao;
import com.doriss.daoimpl.OrderDaoImpl;
import com.doriss.daoimpl.OrderItemDaoImpl;
import com.doriss.daoimpl.ProductDaoImpl;
import com.doriss.entity.*;
import com.doriss.utlis.PaymentForOnlineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Created by chenhaha on 2018/9/18.
 */
@WebServlet("/product")
public class ProductServlet extends  BaseServlet {
    ProductDao productDao = new ProductDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();
    OrderDao orderDao = new OrderDaoImpl();
//   @Override
//    public void add(Orderitem orderitem) {
//
//
//    }
//
//    @Override
//    public void delete(String uuid) {
//
//    }
//
//    @Override
//    public void update(Orderitem orderitem) {
//
//    }
//
//    @Override
//    public Orderitem queryById(String id) {
//        return null;
//    }
//
//    @Override
//    public List<Orderitem> queryAll() {
//        return null;
//    }
    public void payment(HttpServletRequest request,HttpServletResponse response)throws Exception{
        request.setCharacterEncoding("UTF-8");
        String orderid = request.getParameter("orderid");
        String name = request.getParameter("name");
        String telephone = request.getParameter("telephone");
        String address = request.getParameter("address");
//        更新收货数据到订单表
        orderDao.updateOrderAddress (orderid,name,address,telephone);
//        根据易宝支付提供的api文档，处理支付流程
        String pd_Frpid = request.getParameter("pd_Frpid");
        String keyValue = formatString("8UPp0KE8sq73zVP370vko7C39403rtK1YwX40Td6irH216036H27Eb12792t");  // 商家密钥
        String nodeAuthorizationURL  = formatString("http://tech.yeepay.com:8080/robot/debug.action");// 交易请求地址
        String    p0_Cmd = "Buy";                               									// 在线支付请求，固定值 ”Buy”
        String    p1_MerId = formatString("10000432521"); 		// 商户编号
        String    p2_Order  =orderid;           					// 商户订单号
        String	  p3_Amt = formatString(request.getParameter("p3_Amt"));      	   							// 支付金额
        String	  p4_Cur = "CNY";	   		   							// 交易币种
        String	  p5_Pid="一分钱测试按钮";	       	   						// 商品名称
        String	  p6_Pcat = "";	       	   					// 商品种类
        String 	  p7_Pdesc = "";		   								// 商品描述
        String 	  p8_Url = "http://192.168.0.25:8080/callback"; 		       						// 商户接收支付成功数据的地址
        String 	  p9_SAF ="0"; 			   							// 需要填写送货信息 0：不需要  1:需要
        String 	  pa_MP ="";          	   						// 商户扩展信息
        String    pd_FrpId = formatString(request.getParameter("pd_FrpId"));       	   					// 银行编码
        // 银行编号必须大写
        pd_FrpId = pd_FrpId.toUpperCase();
        String 	  pr_NeedResponse = formatString(request.getParameter("pr_NeedResponse"));    // 是否需要应答机制
        String 	  hmac = formatString("");							               							// 交易签名串

        // 获得MD5-HMAC签名
        hmac = PaymentForOnlineService.getReqMd5HmacForOnlinePayment(p0_Cmd,
                p1_MerId,p2_Order,p3_Amt,p4_Cur,p5_Pid,p6_Pcat,p7_Pdesc,
                p8_Url,p9_SAF,pa_MP,pd_FrpId,pr_NeedResponse,keyValue);
        String requestRul = nodeAuthorizationURL+"?pd_FrpId="+ pd_FrpId+"&p0_Cmd ="+p0_Cmd+"&p1_MerId ="+p1_MerId+"&p3_Amt="+p3_Amt+"&p4_Cur="+p4_Cur+
                "& p5_Pid="+ p5_Pid+"&p6_Pcat ="+p6_Pcat +"&p7_Pdesc="+p7_Pdesc+"&p8_Url="+p8_Url+"&p9_SAF"+p9_SAF+
                "& pa_MP="+ pa_MP+"&pr_NeedResponse="+pr_NeedResponse+"&hmac="+hmac;
        response.sendRedirect(requestRul);
        // 商家设置用户购买商品的支付信息
//        boolean isOk=false;
//        isOk = PaymentForOnlineService.verifyCallback(hmac,p1_MerId,)
    }
    public String formatString(String str){
        if(str == null){
            str="";
        }
        return str;
    }
    public void  listJsonAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String cid = request.getParameter("cid");
        List<Product> productslist= productDao.queryAllByCid2(cid);
        String jso =JSON.toJSONString(productslist);
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(jso);
    }
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//    }
    //    查询栏目对应的商品
    public void listAll(HttpServletRequest request, HttpServletResponse response){
        String cid = request.getParameter("cid");
        List<Map<String, Object>> productlist = productDao.queryAllByCid(cid);
        request.setAttribute("productlist",productlist);
        try {
            request.getRequestDispatcher("/product_list.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void  queryDetails(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        Product product = productDao.queryById(pid);
        request.setAttribute("product",product);
        request.getRequestDispatcher("product_info.jsp").forward(request,response);
    }
//    购物车
    public void addCart(HttpServletRequest request , HttpServletResponse response){
        String pid = request.getParameter("pid");
        int count = Integer.parseInt(request.getParameter("quantity"));
//        根据id获得商品详情
        Product product = productDao.queryById(pid);
        Map<String ,Cart>cartMap = (Map<String,Cart>)request.getSession().getAttribute("cart");
        Cart cart =(Cart) request.getSession().getAttribute("cart=="+pid);
        if(cartMap!=null){
//            购物车不为空则加上之前的数量
            int oldCount = cart.getCount();
            cart.setCount(oldCount+count);
            request.getSession().setAttribute("catr=="+pid,cart);
        }else {
//            商品第一次加入购物车，则新增一条信息
            Cart cart1 = new Cart();
            cart1.setPid(pid);
            cart1.setCount(count);
            cart1.setPimage(product.getPimage());
            cart1.setPname(product.getPname());
            cart1.setShop_price(product.getShop_price());
            request.getSession().setAttribute("cart=="+pid,cart1);
            cartMap.put(pid,cart1);
            request.getSession().setAttribute("cart",cartMap);
        }
        try {
            request.getRequestDispatcher("/cart.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    从购物车中删除某项
    public void  deleteCartById(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        Map<String,Cart>cartMap = (Map<String,Cart>)request.getSession().getAttribute("cart");
        cartMap.remove("pid");
        request.getRequestDispatcher("/cart.jsp").forward(request,response);
    }
//   提交订单，将session中的信息持久化到数据库
    public void  commitOrder(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
//        如果用户还没有登陆，那么就调到登陆界面
        if(user==null){
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        Map<String,Cart>cartMap = (Map<String,Cart>)request.getSession().getAttribute("cart");
        Set<String> productsIds = cartMap.keySet();
        Orders orders = new Orders();
        orders.setOid(UUID.randomUUID().toString());
        orders.setState(0l);
    // TODO
        orderDao.add(orders);
        orders.setOrdertime(null);
        orders.setUid(user.getUid());
//创建订单包含商品所有的Ordertime
        double totalPrice = 0;
        for(String productId:productsIds){
            Cart cart = cartMap.get(productId);
            Orderitem orderitem  = new Orderitem();
            orderitem.setOid(orders.getOid());
            orderitem.setPid(productId);
            orderitem.setCount((long) cart.getCount());
            double subtotal = cart.getCount()*cart.getShop_price();
//            把每种商品的价格相加  就是总价
            totalPrice = totalPrice+subtotal;
            orderitem.setSubtotal(subtotal);
            orderitem.setItemid(UUID.randomUUID().toString());
            orderItemDao.add(orderitem);
            }
            orders.setTotal(totalPrice);
            orderDao.updateTotalprice(orders);
            request.setAttribute("totalPrice",totalPrice);
            request.setAttribute("orderid",orders.getOid());
            request.getRequestDispatcher("order_info.jsp").forward(request,response);
        }
//        支付订单
//    public void payment(HttpServletRequest request,HttpServletResponse response){
//
//    }
    }

