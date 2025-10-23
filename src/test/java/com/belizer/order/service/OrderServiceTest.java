package com.belizer.order.service;

import com.belizer.order.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testCreateOrder() {
        Order order = new Order();
        order.setOrderNum("ORD20251022002");
        order.setUserNum("USER002");
        order.setProdNum("PROD002");
        order.setProdName("测试服务产品");
        order.setOrderTime(new Date());
        order.setOrderStatus("1");

        int result = orderService.insert(order);
        System.out.println("插入结果：" + result);
    }

    @Test
    public void testGetOrderById() {
        Order order = orderService.selectById(1L);
        if (order != null) {
            System.out.println("查询结果：" + order.getOrderNum());
        } else {
            System.out.println("未找到对应订单");
        }
    }

    @Test
    public void testGetAllOrders() {
        List<Order> orders = orderService.selectAll();
        System.out.println("订单总数：" + orders.size());
    }

    @Test
    public void testUpdateOrder() {
        Order order = new Order();
        order.setId(1L);
        order.setOrderStatus("3");

        int result = orderService.update(order);
        System.out.println("更新结果：" + result);
    }

    @Test
    public void testDeleteOrder() {
        int result = orderService.delete(1L);
        System.out.println("删除结果：" + result);
    }
}