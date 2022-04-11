package com.eatery.cd.mapper;

import com.eatery.cd.pojo.OrderAddCount;
import com.eatery.cd.pojo.Product;

import java.util.List;

public interface EchartsMapper {
    List<Product> findAll();
    List<OrderAddCount> findAllAddr();
}
