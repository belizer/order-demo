package com.belizer.order.mapper;

import com.belizer.order.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testInsert() {
        Order order = new Order();
        order.setOrderNum("ORD20251022001");
        order.setUserNum("USER001");
        order.setProdNum("PROD001");
        order.setProdName("测试产品");
        order.setOrderTime(new Date());
        order.setOrderStatus("1");

        int result = orderMapper.insert(order);
        System.out.println("插入结果：" + result);
        System.out.println("生成的ID：" + order.getId());
    }

    @Test
    public void testSelectById() {
        Order order = orderMapper.selectById(1L);
        if (order != null) {
            System.out.println("查询结果：" + order.getOrderNum());
        } else {
            System.out.println("未找到对应订单");
        }
    }

    @Test
    public void testSelectAll() {
        List<Order> orders = orderMapper.selectAll();
        System.out.println("订单总数：" + orders.size());
        orders.forEach(order -> System.out.println("订单号：" + order.getOrderNum()));
    }

    @Test
    public void testUpdate() {
        Order order = new Order();
        order.setId(1L);
        order.setOrderStatus("2");
        order.setProdName("更新后的商品名");

        int result = orderMapper.updateById(order);
        System.out.println("更新结果：" + result);
    }

    @Test
    public void testDelete() {
        int result = orderMapper.deleteById(1L);
        System.out.println("删除结果：" + result);
    }
}