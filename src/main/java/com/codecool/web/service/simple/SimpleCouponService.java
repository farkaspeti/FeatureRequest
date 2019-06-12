package com.codecool.web.service.simple;

import com.codecool.web.dao.CouponDao;
import com.codecool.web.dao.ShopDao;
import com.codecool.web.model.Coupon;
import com.codecool.web.model.Shop;
import com.codecool.web.service.CouponService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public final class SimpleCouponService implements CouponService {
    
    private final CouponDao couponDao;
    private final ShopDao shopDao;
    
    public SimpleCouponService(CouponDao couponDao, ShopDao shopDao) {
        this.couponDao = couponDao;
        this.shopDao = shopDao;
    }
    
    @Override
    public List<Coupon> getCoupons() throws SQLException {
        return couponDao.findAll();
    }
    
    @Override
    public Coupon getCoupon(String id) throws SQLException, ServiceException {
        try {
            return couponDao.findById(Integer.parseInt(id));
        } catch (NumberFormatException ex) {
            throw new ServiceException("Coupon id must be an integer");
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public Coupon addCoupon(String name, String percentage, String userId) throws SQLException, ServiceException {
        try {
            return couponDao.add(name, Integer.parseInt(percentage), Integer.parseInt(userId));
        } catch (NumberFormatException ex) {
            throw new ServiceException("Percentage must be an integer");
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public void addCouponToShops(String couponId, String... shopIds) throws SQLException, ServiceException {
        if (shopIds == null || shopIds.length == 0) {
            throw new ServiceException("Shop ids cannot be null or empty");
        }
        try {
            couponDao.add(Integer.parseInt(couponId), parseShopIds(shopIds));
        } catch (NumberFormatException ex) {
            throw new ServiceException("Coupon id must be an integer");
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public List<Shop> getCouponShops(String couponId) throws SQLException, ServiceException {
        try {
            return shopDao.findAllByCouponId(Integer.parseInt(couponId));
        } catch (NumberFormatException ex) {
            throw new ServiceException("Coupon id must be an integer");
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public List<Coupon> getByUserId(String userId) throws SQLException, ServiceException {
        try {
            return couponDao.findByUserId(Integer.parseInt(userId));
        } catch (NumberFormatException ex) {
            throw new ServiceException("UserId must be an integer");
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
    
    @Override
    public List<Coupon> getByUserIdAndShopId(String userId, String shopId) throws SQLException, ServiceException {
        try {
            return couponDao.findByUserIdAndShopId(Integer.parseInt(userId),Integer.parseInt(shopId));
        } catch (NumberFormatException ex) {
            throw  new ServiceException("UserId and shopId must be an integer");
        } catch (IllegalArgumentException ex) {
            throw  new ServiceException(ex.getMessage());
        }
    }
    
    private int[] parseShopIds(String[] shopIds) throws ServiceException {
        try {
            int[] ids = new int[shopIds.length];
            for (int i = 0; i < shopIds.length; i++) {
                String shopId = shopIds[i];
                ids[i] = Integer.parseInt(shopId);
            }
            return ids;
        } catch (NumberFormatException ex) {
            throw new ServiceException("Shop ids must be integers");
        }
    }
}
