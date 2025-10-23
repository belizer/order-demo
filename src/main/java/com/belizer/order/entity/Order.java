package com.belizer.order.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Order {
    /**
     * 主键
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNum;

    /**
     * 用户编号
     */
    private String userNum;

    /**
     * 产品编号
     */
    private String prodNum;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 订单状态 1-已下单 2-已付款 3-已发货 4-已完结
     */
    private String orderStatus;
}