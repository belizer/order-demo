package com.belizer.order.controller;

import com.belizer.order.entity.Order;
import com.belizer.order.service.KafkaProducer;
import com.belizer.order.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {


    private final OrderService orderService;
    private final KafkaProducer kafkaProducer;

    /**
     * 创建订单
     *
     * @param order 订单对象
     * @return 影响行数
     */
    @PostMapping("/create")
    public int createOrder(@RequestBody Order order) throws JsonProcessingException {
        return orderService.creatOrder(order);
    }

    /**
     * 更新订单
     *
     * @param order 订单对象
     * @return 影响行数
     */
    @PutMapping("/update")
    public int updateOrder(@RequestBody Order order) {
        return orderService.update(order);
    }

    /**
     * 删除订单
     *
     * @param id 订单ID
     * @return 影响行数
     */
    @DeleteMapping("/delete/{id}")
    public int deleteOrder(@PathVariable Long id) {
        return orderService.delete(id);
    }

    /**
     * 根据ID查询订单
     *
     * @param id 订单ID
     * @return 订单对象
     */
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.selectById(id);
    }

    /**
     * 查询所有订单
     *
     * @return 订单列表
     */
    @GetMapping("/list")
    public List<Order> getAllOrders() {
        return orderService.selectAll();
    }

    /**
     * 根据订单编号查询订单
     *
     * @param orderNum 订单编号
     * @return 订单对象
     */
    @GetMapping("/num/{orderNum}")
    public Order getOrderByNum(@PathVariable String orderNum) {
        return orderService.selectByOrderNum(orderNum);
    }
}