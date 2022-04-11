package com.eatery.cd.service;

import com.eatery.cd.mapper.ProductMapper;
import com.eatery.cd.pojo.Product;
import com.eatery.cd.pojo.ProductKind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 实现相关的用户功能：
 * 1.查询商品，获取所有的商品数据(findAllProductList)                          
 * 2.查询商品种类名称信息,获取所有商品种类名(findAllProductKindName)           
 * 3.查询热门商品信息信息(findHotProductList)                                  
 * 4.根据条件查询商品(findProductByCondition)                                  
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findAllProductList() {
        return productMapper.findAllProductList();
    }

    @Override
    public List findAllProductKindNameList() {
        return productMapper.findAllProductKindNameList();
    }

    @Override
    public List<Product> findHotProductList() {
        return productMapper.findHotProductList();
    }

    @Override
    public List<Product> findProductByCondition(String product_name,String name,Double minprice,Double maxprice) {

        return productMapper.findProductByCondition(product_name,name,minprice,maxprice);
    }

    @Override
    public Product findProductById(String productId) {
        return productMapper.findProductById(productId);
    }

    @Override
    public List<Product> findProductsById(String[] productIds) {
        return productMapper.findProductsById(productIds);
    }

    @Override
    public List<Product> findThreeProductList() {
        return productMapper.findThreeProductList();
    }
    @Override
    public  void deleteAllProduct(String[] productIds){
        productMapper.deleteAllProduct(productIds);
    }

    @Override
    public  void updateOne(Product p){
        productMapper.updateOne(p);
    }
    @Override
    public  void deleteById(String id){
        productMapper.deleteById(id);
    }
    @Override
    public  void addOne(Product p){
        String id= UUID.randomUUID().toString();
        p.setProductId(id);
        productMapper.addOne(p);

    }

    public  List<ProductKind> findAllKind(){
        return  productMapper.findAllKind();
    }

    public  List<Product> findAllProductCondition(String Pname){
        return  productMapper.findAllProductCondition(Pname);
    }

    @Override
    public List<Product> findProductByName(String name) {
        return productMapper.findProductByName(name);
    }

    @Override
    public List<Product> findProductByCategory(String productKindId) {
        return productMapper.findProductByCategory(productKindId);
    }

    @Override
    public List<Product> searchByConditition(String keyword) {
        return productMapper.searchByConditition(keyword);
    }

    @Override
    public List<Product> findProductsById() {

        return productMapper.findCardProductsById();
    }
}
