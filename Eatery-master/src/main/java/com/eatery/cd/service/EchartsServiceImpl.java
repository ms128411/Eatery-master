package com.eatery.cd.service;

import com.eatery.cd.mapper.EchartsMapper;
import com.eatery.cd.pojo.OrderAddCount;
import com.eatery.cd.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EchartsServiceImpl implements EchartsService {
    @Autowired
    private EchartsMapper echartsMapper;
    @Override
    public List<Product> findAll() {
        return echartsMapper.findAll();
    }

    @Override
    public List<OrderAddCount> findAllAddr() {
        return echartsMapper.findAllAddr();
    }
}
