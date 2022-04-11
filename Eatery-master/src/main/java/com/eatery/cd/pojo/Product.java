package com.eatery.cd.pojo;

/**
 * 商品类
 */
public class Product {


    private String productId;
    private String productName;
    private double price;
    private double sprice;
    private String pic;
    private String description;
    private double special;
    private Integer productKindId;
    private Integer buyCount;               //商品销售量
    private ProductKind productKind;        //商品种类表对象

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSprice() {
        return sprice;
    }

    public void setSprice(double sprice) {
        this.sprice = sprice;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSpecial() {
        return special;
    }

    public void setSpecial(double special) {
        this.special = special;
    }

    public Integer getProductKindId() {
        return productKindId;
    }

    public void setProductKindId(Integer productKindId) {
        this.productKindId = productKindId;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buy_count) {
        this.buyCount = buy_count;
    }

    public ProductKind getProductKind() {
        return productKind;
    }

    public void setProductKind(ProductKind productKind) {
        this.productKind = productKind;
    }

    @Override
    public int hashCode() {
        return productId== null ? 0 : productId.hashCode();
    }
    /* 重写equals方法 */
    @Override
    public boolean equals(Object obj) {
        //1.如果obj为null, 直接返回false
        if(obj == null){
            return false;
        }
        //2.如果obj == this, 直接返回true
        if(obj == this){
            return true;
        }

        //3.obj指向的对象的类型不是Product类型
        if(!(obj instanceof Product)){
            return false;
        }

        //4.比较两个对象的id值是否相等
        Product other = (Product) obj;
        if(productId != null && productId.equals(other.getProductId())){
            return true;
        }
        return false;
    }











}


