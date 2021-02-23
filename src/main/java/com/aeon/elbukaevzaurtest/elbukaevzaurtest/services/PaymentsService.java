package com.aeon.elbukaevzaurtest.elbukaevzaurtest.services;

import com.aeon.elbukaevzaurtest.elbukaevzaurtest.dao.PaymentsRepository;
import com.aeon.elbukaevzaurtest.elbukaevzaurtest.entity.CurrencyTypeEnum;
import com.aeon.elbukaevzaurtest.elbukaevzaurtest.entity.Payments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentsService {

    @Autowired
    PaymentsRepository paymentsRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Payments minusPayment(Long login_id){
        Payments payments = this.paymentsRepository.findByLoginIdAndMaxDate(login_id);
        Payments newPayment = new Payments(payments.getPlus_price(), payments.getMinus_price() + 1.1, login_id, CurrencyTypeEnum.USD, payments.getId());
        paymentsRepository.save(newPayment);
        return newPayment;
    }
}
