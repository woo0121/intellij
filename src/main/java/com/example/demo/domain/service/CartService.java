package com.example.demo.domain.service;

import com.example.demo.domain.daeguyo.CartDto;
import com.example.demo.domain.daeguyo.OrderDto;
import com.example.demo.domain.daeguyo.PaymentDto;
import com.example.demo.domain.daeguyo.UserDto;
import com.example.demo.domain.mapper.CartMapper;
import com.example.demo.domain.mapper.OrderMapper;
import com.example.demo.domain.mapper.PaymentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CartService {


    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderMapper mapper;

    @Autowired
    private PaymentMapper paymapper;

    public List<CartDto> SearchOption( ){

//        CartDto cart = new CartDto();
//        cart.setCart_id(UUID.randomUUID().toString());
//        cart.setMenu_id(cart.getMenu_id());
//        cart.setCount(cart.getCount());
//        cart.setSelected_option(cart.getSelected_option());
        return cartMapper.CartList();

    }
    public void updateOrderAmount(CartDto dto)  {


        cartMapper.updateOrder(dto);
    }


    public int createOrder(CartDto cartDto) {
        // Convert CartDto to OrderDto



        OrderDto orderData = new OrderDto();
        orderData.setOrder_id(UUID.randomUUID().toString());
        orderData.setU_email(cartDto.getU_email());
        orderData.setMenu_id(cartDto.getMenu_id());
        orderData.setRes_id(cartDto.getRes_id());
        orderData.setSelected_option(cartDto.getSelected_option());
        orderData.setOrder_amount(cartDto.getCount());
        orderData.setTotal_price(cartDto.getTotal_price());

        java.util.Date date=new java.util.Date();
        java.sql.Date sqlDate=new java.sql.Date(date.getTime());

        orderData.setOrder_date(sqlDate);
        orderData.setOrder_status("1");
        // ... set other fields ...

        return cartMapper.insertOrder(orderData);


    }







    public Map<String, Object> getUserAndOrderDetails(String cart_id) {
        Map<String, Object> details = mapper.selectUserOrderDetails(cart_id);

        if (details != null && !details.isEmpty()) {
            CartDto orders = new CartDto();
            UserDto users = new UserDto();

            // 데이터베이스에서 조회한 결과를 기반으로 DTO 객체의 필드 설정
            orders.setRes_id((String) details.get("res_id"));
            users.setNickname((String) details.get("nickname"));
            users.setPhone((String) details.get("phone"));

            // DTO 객체의 값들을 다시 맵에 넣음
            details.put("orderName", orders.getRes_id());
            details.put("customerId", users.getNickname());
            details.put("phoneNumber", users.getPhone());

            System.out.println(details.size());
            System.out.println("null?2 ="+details);
        } else {
            // 주문 정보가 없는 경우 메시지 설정
            details = new HashMap<>();
            details.put("message", "Order not found");
        }

        return details;
    }

    public List<OrderDto> getCartItems() {
        List<OrderDto> asd = mapper.selectByUserId();

        if (asd == null) {
            // 데이터베이스에서 주문 데이터를 가져올 수 없는 경우, 빈 리스트 또는 다른 처리를 수행
            System.out.println("null? ="+asd);
            System.out.println(asd.size());
            return Collections.emptyList(); // 빈 리스트를 반환하거나 다른 적절한 처리 수행
        }
        System.out.println("null? ="+asd);
        System.out.println(asd.size());
        return asd;
    }

    public Map<String, Object> paymentInsert(PaymentDto paymentData){
        System.out.println("pay? ="+paymentData);

        int result = paymapper.insertPayment(paymentData);
        System.out.println("pay2? ="+paymentData);

        // 결과 값을 포함하는 Map 객체 생성
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
    }

    public int cartDelete(CartDto dto) {
        return cartMapper.deleteOrder(dto.getCart_id());
    }


}
