package myservlet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mybean.data.ShoppingCart;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Servlet extends HttpServlet{
    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        HttpSession session = request.getSession();
        //1.获取请求参数，id, price
        String bookName = request.getParameter("id");
        int price = Integer.parseInt(request.getParameter("price"));
        //2.获取购物车对象
        ShoppingCart sc = (ShoppingCart)session.getAttribute("sc");
        if (sc == null) {
            sc = new ShoppingCart();
            session.setAttribute("sc", sc);
        }
        //3.把点击的选项加入到购物车中
        sc.addToCart(bookName, price);

        //4.准备响应的 JSON 对象 {"bookName" : bookName, "totalBookNumber":1, "totalMoney":1}
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(sc);
        System.out.println(result);
        //5.响应 JSON 对象
        response.setContentType("text/javascript");
        response.getWriter().print(result);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        doPost(request, response);
    }
}
