package com.eatery.cd.service;

import com.eatery.cd.pojo.OrderAddCount;
import com.eatery.cd.pojo.Product;

import java.util.List;

public interface EchartsService {
    List<Product> findAll();
    List<OrderAddCount> findAllAddr();
}
