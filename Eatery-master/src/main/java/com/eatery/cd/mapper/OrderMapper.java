package com.eatery.cd.mapper;

import com.eatery.cd.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {

    /**
     * 根据用户id查询出来的订单列表
     * @return
     */
    List<Order> findOrderListByUserId(String userId);

    /**
     *查询order_p表和product_order_p中的全部数据
     * @return
     */
    List<Order> findOrderAll();

    /*
    * 查询订单总数
    * */
    Integer findOrderTotal();

    /**
     * 根据订单id和订单时间查询对应的order
     * @return
     * @param orderId 订单id
     */
    Order findListByOrderId(String orderId);

    //保存到中间表Product_order_p
    void saveToProductOrder(@Param("orderId") String orderId, @Param("productId") String productId, @Param("buyNum") Integer buyNum);

    String findPrdouctByOrderId(String orderId);

    //保存订单到订单表
    void saveOrder(Order order);
    //修改订单状态
    void updateOrderStatus(@Param("status") String status,@Param("orderId")String orderId);

    /***
     * 删除订到表
     * @param orderId
     */
    void deleteOrder(String orderId);

    /**
     * 删除商品订单表
     * @param orderId
     */
    void deleteOrderAndProduct(String orderId);

    /**
     * 支付成功修改库存数量
     * @param pId
     * @param buyCount
     */
    void updateProductNum(@Param("pId") String pId, @Param("buyCount") int buyCount);
}
