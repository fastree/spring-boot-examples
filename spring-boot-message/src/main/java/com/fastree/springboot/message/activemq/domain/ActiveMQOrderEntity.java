package com.fastree.springboot.message.activemq.domain;

import java.io.Serializable;

public class ActiveMQOrderEntity implements Serializable {
    private static final long serialVersionUID = 8435598103359241794L;
    private Long orderNo;
    private String orderName;
    private String orderOwner;

    public ActiveMQOrderEntity() {
    }

    public ActiveMQOrderEntity(Long orderNo, String orderName, String orderOwner) {
        this.orderNo = orderNo;
        this.orderName = orderName;
        this.orderOwner = orderOwner;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderOwner() {
        return orderOwner;
    }

    public void setOrderOwner(String orderOwner) {
        this.orderOwner = orderOwner;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo=" + orderNo +
                ", orderName='" + orderName + '\'' +
                ", orderOwner='" + orderOwner + '\'' +
                '}';
    }
}
