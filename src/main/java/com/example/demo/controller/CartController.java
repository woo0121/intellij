package com.example.demo.controller;

import com.example.demo.domain.daeguyo.CartDto;
import com.example.demo.domain.daeguyo.PaymentDto;
import com.example.demo.domain.service.CartService;
import com.example.demo.domain.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping()
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/cart")
    public String getCart(Model model){
        List<CartDto> options = cartService.SearchOption();

        log.info(options.toString());
        int total = 0;
        for (CartDto option : options) {
            total += option.getPrice() * option.getCount();
        }
        model.addAttribute("total", total);

        model.addAttribute("options",options);
        return "cart";



    }
    @PostMapping("/user/details")
    @ResponseBody
    public Map<String,Object> getUserAndOrderDetails(@RequestBody CartDto dto, Model model) {
        System.out.println("getUserAndOrderDetails");
        System.out.println((dto.getCart_id()));
        System.out.println((dto.getRes_id()));
        Map<String,Object> details = cartService.getUserAndOrderDetails(dto.getCart_id());
        System.out.println("getUserAndOrderDetails2");



        System.out.println("123123"+details.get("res_id"));

        System.out.println("123123"+details.get("nickname"));
        System.out.println("123123"+details.get("phone"));

        return details;
    }
    @PostMapping("/payment/save")
    @ResponseBody
    public Map<String,Object> paymentsave(@RequestBody PaymentDto payment){
        System.out.println("Payment saved");
        Map<String,Object> detail = cartService.paymentInsert(payment);
        System.out.println(payment);
        return detail; // HTTP 상태 코드 200(OK)을 반환합니다.
    }

    @PostMapping("/create")
    @ResponseBody
    public int create(@RequestBody CartDto dto) {

        int make = cartService.createOrder(dto);
        return make;
    }

//    @PostMapping("/create")
//    @ResponseBody
//    public int create(@RequestBody List<CartDto> dtos) {
//        int make = 1;
//        for (CartDto dto: dtos) {
//            make *= cartService.createOrder(dto);
//        }
//        return make;
//    }

    @PostMapping("/cart/delete")
    @ResponseBody
    public int paymentsave(@RequestBody CartDto dto){
        System.out.println(dto.getCart_id());
        System.out.println("Payment saved");
        int detail = cartService.cartDelete(dto);

        return detail; // HTTP 상태 코드 200(OK)을 반환합니다.
    }


}
