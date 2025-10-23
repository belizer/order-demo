package com.belizer.order.service;

import com.belizer.order.entity.Order;
import com.belizer.order.mapper.OrderMapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final OrderMapper orderMapper;
    private final KafkaProducer kafkaProducer;

    /**
     * 保存订单
     *
     * @param order 订单对象
     * @return 影响行数
     */
    public int insert(Order order) {
        return orderMapper.insert(order);
    }

    /**
     * 更新订单
     *
     * @param order 订单对象
     * @return 影响行数
     */
    public int update(Order order) {
        return orderMapper.updateById(order);
    }

    /**
     * 删除订单
     *
     * @param id 订单ID
     * @return 影响行数
     */
    public int delete(Long id) {
        return orderMapper.deleteById(id);
    }

    /**
     * 根据ID查询订单
     *
     * @param id 订单ID
     * @return 订单对象
     */
    public Order selectById(Long id) {
        return orderMapper.selectById(id);
    }

    /**
     * 查询所有订单
     *
     * @return 订单列表
     */
    public List<Order> selectAll() {
        return orderMapper.selectAll();
    }

    /**
     * 根据订单编号查询订单
     *
     * @param orderNum 订单编号
     * @return 订单对象
     */
    public Order selectByOrderNum(String orderNum) {
        return orderMapper.selectByOrderNum(orderNum);
    }

    public int creatOrder(Order order) throws JsonProcessingException {
        order.setOrderNum(UUID.randomUUID().toString().replace("-", ""));//生成订单编号 先使用uuid代替
        order.setOrderStatus("1");//订单状态 1-已下单
        order.setOrderTime(new Date());

        ObjectMapper objectMapper = new ObjectMapper();
        kafkaProducer.sendMessage("order-topic", objectMapper.writeValueAsString(order));

        return 1;
    }
}