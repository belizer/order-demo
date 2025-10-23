package com.belizer.order.mapper;

import com.belizer.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 插入订单记录
     * @param order 订单对象
     * @return 影响行数
     */
    int insert(Order order);

    /**
     * 根据ID更新订单记录
     * @param order 订单对象
     * @return 影响行数
     */
    int updateById(Order order);

    /**
     * 根据ID删除订单记录
     * @param id 订单ID
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 根据ID查询订单记录
     * @param id 订单ID
     * @return 订单对象
     */
    Order selectById(Long id);

    /**
     * 查询所有订单记录
     * @return 订单列表
     */
    List<Order> selectAll();

    /**
     * 根据订单编号查询订单记录
     * @param orderNum 订单编号
     * @return 订单对象
     */
    Order selectByOrderNum(String orderNum);
}