package com.eatery.cd.service;

import com.eatery.cd.pojo.Order;
import com.eatery.cd.pojo.Product;
import com.eatery.cd.pojo.User;

import java.util.List;
import java.util.Map;

public interface OrderService {
    /**
     * 根据用户id查询订单列表
     * @return
     */
    List<Order> findOrderListByUserId(String userId);

    /**
     *查询order_p表和product_order_p中的全部数据
     * @return
     */
    List<Order> findOrderAll();

    /**
     * 根据订单id和订单时间查询对应的orderList
     * @return
     * @param orderId 订单id
     */
    Order findListByOrderId(String orderId);



    //保存订单
    void saveOrder(User user, String[]productIds, String addresinfo, Map<Product,Integer> cartMap ,String oid);


    void updateOrderStatus(String status,String orderId);

    /***
     * 删除订单
     * @param orderId
     */
    void deleteOrder(String orderId);

    void updateProductNum(String pId, int buyCount);
}
