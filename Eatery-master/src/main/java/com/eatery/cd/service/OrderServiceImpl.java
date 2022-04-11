package com.eatery.cd.service;

import com.eatery.cd.mapper.OrderMapper;
import com.eatery.cd.mapper.ProductMapper;
import com.eatery.cd.mapper.UserMapper;
import com.eatery.cd.pojo.Order;
import com.eatery.cd.pojo.Product;
import com.eatery.cd.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<Order> findOrderListByUserId(String userId) {
        return orderMapper.findOrderListByUserId(userId);
    }

    @Override
    public List<Order> findOrderAll() {
        return orderMapper.findOrderAll();
    }

    @Override
    public Order findListByOrderId(String orderId) {
        return orderMapper.findListByOrderId(orderId);
    }


    @Override
    public void saveOrder(User user, String[] productIds, String addresinfo, Map<Product, Integer> cartMap, String oid) {
        Order order = new Order();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        order.setOrderDate(sdf.format(new Date()));
        order.setAddResInfo(addresinfo);
        order.setOrderStatus("0");//待付款
        order.setUserId(user.getUserId());//用户IdS
        for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
            order.setOrderId(oid);
            orderMapper.saveToProductOrder(oid, entry.getKey().getProductId(), entry.getValue());
            order.setMoney(entry.getKey().getSprice() * entry.getValue());
            order.setPrice(entry.getKey().getSprice());
            orderMapper.saveOrder(order);
        }
    }

    @Override
    public void updateOrderStatus(String status, String orderId) {
        orderMapper.updateOrderStatus(status, orderId);
    }

    @Override
    public void deleteOrder(String orderId) {
        orderMapper.deleteOrder(orderId);
        orderMapper.deleteOrderAndProduct(orderId);
    }

    @Override
    public void updateProductNum(String pId, int buyCount) {
        orderMapper.updateProductNum(pId,buyCount);
    }


}

