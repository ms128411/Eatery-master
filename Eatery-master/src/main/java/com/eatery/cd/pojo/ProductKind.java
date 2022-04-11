package com.eatery.cd.pojo;

/**
 * 商品种类类
 */
public class ProductKind {
    private Integer productKindId;              //商品种类id
    private String productId;                   //商品id
    private String name;                         //商品种类名

    public Integer getProductKindId() {
        return productKindId;
    }

    public void setProductKindId(Integer productKindId) {
        this.productKindId = productKindId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
