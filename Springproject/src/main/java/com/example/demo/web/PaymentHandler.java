package com.example.demo.web;

import com.example.demo.entity.Payment;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PaymentHandler {

    @RequestMapping("/payment")
    public String payment(){
        ArrayList<Payment> payments = new ArrayList<>();
        payments.add(new Payment(1,"tansixu"));
        payments.add(new Payment(2,"wangbiao"));
        Gson gson = new Gson();
        String string = gson.toJson(payments);
        return string;
    }


}
