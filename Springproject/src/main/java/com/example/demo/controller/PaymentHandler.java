package com.example.demo.controller;

import com.example.demo.entity.Payment;

import org.springframework.web.bind.annotation.RequestMapping;



import java.util.*;

public class PaymentHandler {

    @RequestMapping("/payment")
    public ArrayList<Payment> findAll(){
        ArrayList<Payment> payments = new ArrayList<>();
        payments.add(new Payment(1,"tansixu"));
        payments.add(new Payment(2,"wangbiao"));
        return payments;
    }


}
