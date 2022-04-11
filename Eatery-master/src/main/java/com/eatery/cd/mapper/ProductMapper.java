package com.eatery.cd.mapper;

import com.eatery.cd.pojo.Product;
import com.eatery.cd.pojo.ProductKind;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface ProductMapper {
    public List<Product> findAllProductList();

    public List findAllProductKindNameList();

    public List<Product> findHotProductList();

    public List<Product> findProductByCondition(@Param("product_name") String product_name,@Param("name") String name, @Param("minprice")Double minprice, @Param("maxprice")Double maxprice);








    Product findProductById(String productId);

    List<Product> findProductsById(String[] productIds);


    /**
     * 查询所有的商品列表，然后根据销量排序并截取前三条
     * @return
     */
    List<Product> findThreeProductList();





    //====================================江长洪做=====================================
    //批量删除!
    public void deleteAllProduct(String[] productIds);
    //修改单个对象
    public  void updateOne(Product p);
    //添加对象
    public  void addOne(Product p);

    //根据id删除对象
    public void deleteById(String Id);

    //查找所有的product_kind_id--为了获取菜品名称
    public List<ProductKind> findAllKind();

    //模糊查询
    public  List<Product> findAllProductCondition(String Pname);

    /**
     * 根据条件查询商品
     * @param name
     * @return
     */
    List<Product> findProductByName(String name);
    /**
     * 根据类别查询所有
     * @param productKindId
     * @return
     */
    List<Product> findProductByCategory(String productKindId);

    /**
     * 根据条件全文检索
     * @param keyword
     * @return
     */
    List<Product> searchByConditition(String keyword);

    List<Product> findCardProductsById();
}
