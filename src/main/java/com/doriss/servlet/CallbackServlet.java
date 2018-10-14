package com.doriss.servlet;

import com.doriss.dao.OrderDao;
import com.doriss.daoimpl.OrderDaoImpl;
import com.doriss.utlis.PaymentForOnlineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by chenhaha on 2018/10/9.
 */
@WebServlet("/callback")
public class CallbackServlet extends HttpServlet {
    OrderDao orderDao = new OrderDaoImpl();
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String keyValue   = formatString("8UPp0KE8sq73zVP370vko7C39403rtK1YwX40Td6irH216036H27Eb12792t");   // 商家密钥
       String r0_Cmd 	  = formatString(request.getParameter("r0_Cmd"));										  // 业务类型
       String p1_MerId   = formatString("10000432521");   // 商户编号
       String r1_Code    = formatString(request.getParameter("r1_Code"));										// 支付结果
       String r2_TrxId   = formatString(request.getParameter("r2_TrxId"));										// 易宝支付交易流水号
       String r3_Amt     = formatString(request.getParameter("r3_Amt"));											// 支付金额
       String r4_Cur     = formatString(request.getParameter("r4_Cur"));											// 交易币种
       String r5_Pid     = formatString(request.getParameter("r5_Pid"));											// 商品名称
       String r6_Order   = formatString(request.getParameter("r6_Order"));										// 商户订单号
       String r7_Uid     = formatString(request.getParameter("r7_Uid"));											// 易宝支付会员ID
       String r8_MP      = formatString(request.getParameter("r8_MP"));											// 商户扩展信息
       String r9_BType   = formatString(request.getParameter("r9_BType"));										// 交易结果返回类型
       String hmac       = formatString(request.getParameter("hmac"));												// 签名数据
       boolean isOK = false;
       // 校验返回数据包
       isOK = PaymentForOnlineService.verifyCallback(hmac,p1_MerId,r0_Cmd,r1_Code,
               r2_TrxId,r3_Amt,r4_Cur,r5_Pid,r6_Order,r7_Uid,r8_MP,r9_BType,keyValue);
       if(isOK) {
           if(r1_Code.equals("1")) {
               // 产品通用接口支付成功返回-浏览器重定向
               if(r9_BType.equals("1")){
                   orderDao.updateStatus(1);
                       request.getRequestDispatcher("/pay_success.jsp").forward(request,response);
                   // 产品通用接口支付成功返回-服务器点对点通讯
               } else if(r9_BType.equals("2")) {
                   // 如果在发起交易请求时	设置使用应答机制时，必须应答以"success"开头的字符串，大小写不敏感
                   response.getOutputStream().println("SUCCESS");
                   // 产品通用接口支付成功返回-电话支付返回
               } else if(r9_BType.equals("3")) {

               }
               // 下面页面输出是测试时观察结果使用
//               out.println("<br>交易成功!<br>商家订单号:" + r6_Order + "<br>支付金额:" +
//                       r3_Amt + "<br>易宝支付交易流水号:" + r2_TrxId);
           }
       } else {
           request.setCharacterEncoding("UTF-8");
           response.getWriter().println("交易签名被篡改!");
       }
   }
   public String formatString(String str){
       if(str == null){
           str="";
       }
       return str;
   }
   protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
       doPost(request,response);
   }
}
