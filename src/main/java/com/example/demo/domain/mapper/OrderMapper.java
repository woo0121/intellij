package com.example.demo.domain.mapper;


import com.example.demo.domain.daeguyo.OrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper{

    @Select("select * from tbl_order ")
    List<OrderDto> selectByUserId();

    @Query("delete from tbl_order where order_id = #{order_id}")
    int deleteOrder(String order_id);

    @Select("SELECT c.res_id AS res_id, u.nickname AS nickname, u.phone AS phone FROM tbl_cart c INNER JOIN tbl_user u ON c.u_email = u.u_email WHERE c.cart_id = #{cart_id}")
    Map<String, Object> selectUserOrderDetails(String cart_id);









}
