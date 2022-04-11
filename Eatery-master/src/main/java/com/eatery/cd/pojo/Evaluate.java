package com.eatery.cd.pojo;


/**
 * 用户评论
 */
public class Evaluate {
    private String orderId;//订单编号
    private String productName;//商品名额
    private String evaContent;//评价内容evaContent
    private String evaDate;//评价时间
    private String ansContent;//回复评论内容
    private String ansDate;//回复评论时间

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getEvaContent() {
        return evaContent;
    }

    public void setEvaContent(String evaContent) {
        this.evaContent = evaContent;
    }

    public String getEvaDate() {
        return evaDate;
    }

    public void setEvaDate(String evaDate) {
        this.evaDate = evaDate;
    }

    public String getAnsContent() {
        return ansContent;
    }

    public void setAnsContent(String ansContent) {
        this.ansContent = ansContent;
    }

    public String getAnsDate() {
        return ansDate;
    }

    public void setAnsDate(String ansDate) {
        this.ansDate = ansDate;
    }
}
