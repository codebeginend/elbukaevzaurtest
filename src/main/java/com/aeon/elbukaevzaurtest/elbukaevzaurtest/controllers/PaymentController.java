package com.aeon.elbukaevzaurtest.elbukaevzaurtest.controllers;

import com.aeon.elbukaevzaurtest.elbukaevzaurtest.entity.Login;
import com.aeon.elbukaevzaurtest.elbukaevzaurtest.entity.Payments;
import com.aeon.elbukaevzaurtest.elbukaevzaurtest.services.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    private PaymentsService paymentsService;

    @RequestMapping(value = "payment", method = RequestMethod.GET)
    private Payments minusPayment(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return paymentsService.minusPayment(((Login)auth.getPrincipal()).getId());
    }
}
