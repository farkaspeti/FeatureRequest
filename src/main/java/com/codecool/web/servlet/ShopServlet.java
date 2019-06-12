package com.codecool.web.servlet;

import com.codecool.web.dao.CouponDao;
import com.codecool.web.dao.ShopDao;
import com.codecool.web.dao.database.DatabaseCouponDao;
import com.codecool.web.dao.database.DatabaseShopDao;
import com.codecool.web.model.Coupon;
import com.codecool.web.model.Shop;
import com.codecool.web.model.User;
import com.codecool.web.service.CouponService;
import com.codecool.web.service.ShopService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleCouponService;
import com.codecool.web.service.simple.SimpleShopService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/protected/shop")
public final class ShopServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try (Connection connection = getConnection(req.getServletContext())) {
            ShopDao shopDao = new DatabaseShopDao(connection);
            CouponDao couponDao = new DatabaseCouponDao(connection);
            ShopService shopService = new SimpleShopService(shopDao);
            CouponService couponService = new SimpleCouponService(couponDao,shopDao);
            
            User user = (User) req.getSession().getAttribute("user");
            String userId = String.valueOf(user.getId());
            String id = req.getParameter("id");
            Shop shop = shopService.getShop(id);
            String shopId = String.valueOf(shop.getId());
            List<Coupon> couponList = couponService.getByUserIdAndShopId(userId,shopId);
            req.setAttribute("shop", shop);
            req.setAttribute("couponList",couponList);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (ServiceException ex) {
            req.setAttribute("error", ex.getMessage());
        }
        req.getRequestDispatcher("shop.jsp").forward(req, resp);
    }
}
