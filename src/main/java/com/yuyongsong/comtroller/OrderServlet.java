package com.yuyongsong.comtroller;

import com.yuyongsong.dao.OrderDao;
import com.yuyongsong.model.Item;
import com.yuyongsong.model.Order;
import com.yuyongsong.model.Payment;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private Connection con;

    @Override
    public void init() throws ServletException {
        con = (Connection) getServletContext().getAttribute("conn");
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Payment> paymentTypeList = Payment.findAllPayment(con);
        request.setAttribute("paymentTypeList", paymentTypeList);
        String path = "/WEB-INF/views/order.jsp";
        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int customerId = request.getParameter("customerId")!=null?Integer.parseInt(request.getParameter("customerId")):0;
        int paymentId = request.getParameter("paymentId")!=null?Integer.parseInt(request.getParameter("paymentId")):0;
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String postalCode = request.getParameter("postalCode");
        String country = request.getParameter("county");
        String notes = request.getParameter("notes");
        double orderTotal = request.getParameter("orderTotal")!=null?Double.parseDouble(request.getParameter("orderTotal")):0.0;


        String message = null;
        if (customerId == 0 || paymentId == 0 || firstName == null || firstName.trim().length()==0 || phone == null ||
                phone.trim().length() == 0 || address1 == null || address1.trim().length() == 0 ||
                postalCode == null || postalCode.trim().length() == 0){
            message = "Error!, Enter Required(*) Info.";
            request.setAttribute("message", message);
            List<Payment> paymentTypeList = Payment.findAllPayment(con);
            request.setAttribute("paymentTypeList", paymentTypeList);
            String path = "/WEB-INF/views/order.jsp";
            request.getRequestDispatcher(path).forward(request, response);
            /*转发后会回到之前转发的servlet,继续执行后续代码，所以这里我加一个return来结束servlet执行，以免执行下面代码造成异常*/
            return;
        }

        Order o = new Order();
        o.setCustomerId(customerId);
        o.setPaymentId(paymentId);
        o.setFirstName(firstName);
        o.setLastName(lastName);
        o.setPhone(phone);
        o.setAddress1(address1);
        o.setAddress2(address2);
        o.setCity(city);
        o.setCountry(country);
        o.setState(state);
        o.setNotes(notes);
        o.setPostalCode(postalCode);
        o.setOrderTotal(orderTotal);
        HttpSession session = request.getSession();
        if (session!=null && session.getAttribute("cart")!=null){
            ArrayList<Item> cartItems = (ArrayList<Item>) session.getAttribute("cart");
            o.setOrderDetails(new HashSet<Item>(cartItems));
            //HashSet<Item> cartItems = (HashSet<Item>) session.getAttribute("cart");
            //o.setOrderDetails(cartItems);
        }

        OrderDao dao = new OrderDao();
        int n = 0;
        try {
            n = dao.save(con, o);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (n > 0){
            String path = "/WEB-INF/views/orderSuccess.jsp";
            request.getRequestDispatcher(path).forward(request, response);
        }
    }
}
