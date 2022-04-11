package com.eatery.cd.service;

import com.eatery.cd.pojo.Product;
import com.eatery.cd.pojo.ProductKind;

import java.util.List;

public interface ProductService {
    /**
     * 查询商品信息列表
     * @return 商品列表对象列表
     */
    public List<Product> findAllProductList();

    /**
     * 查询商品种类名称信息
     * @return 所有的商品种类名列表
     */
    public List findAllProductKindNameList();

    /**
     * 查询热门商品信息
     * @return 热门商品信息列表
     */
    public List<Product> findHotProductList();

    /**
     * 根据条件查询商品
     * @return 查询到的信息列表
     */
    public List<Product> findProductByCondition(String product_name,String name,Double minprice,Double maxprice);
    //根据商品id查询商品
    Product findProductById(String productId);
    List<Product> findProductsById(String[] productIds);

    /**
     * 查询所有的商品列表，然后根据销量排序并截取前三条
     * @return
     */
    List<Product> findThreeProductList();

    //批量删除
    public  void deleteAllProduct(String[] productIds);
    //单体修改
    public  void updateOne(Product p);
    //单体删除
    public  void deleteById(String id);
    //单体创建
    public  void addOne(Product p);
    //查询所有的kindid
    public  List<ProductKind> findAllKind();
    //模糊查询
    public  List<Product> findAllProductCondition(String Pname);

    /**
     * 根据条件查询商品
     * @param name
     * @return
     */
    List<Product> findProductByName(String name);
    /**
     * 根据分类名查询商品
     * @param productKindId
     * @return
     */
    List<Product> findProductByCategory(String productKindId);

    /**
     * 根据条件全文搜索
     * @param keyword
     * @return
     */
    List<Product> searchByConditition(String keyword);

    /**
     *根据订单id查询商品
     * @return
     */
    List<Product> findProductsById();
}

