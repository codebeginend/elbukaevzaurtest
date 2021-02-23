package com.aeon.elbukaevzaurtest.elbukaevzaurtest;

import com.aeon.elbukaevzaurtest.elbukaevzaurtest.dao.ILoginDao;
import com.aeon.elbukaevzaurtest.elbukaevzaurtest.dao.PaymentsRepository;
import com.aeon.elbukaevzaurtest.elbukaevzaurtest.entity.CurrencyTypeEnum;
import com.aeon.elbukaevzaurtest.elbukaevzaurtest.entity.Login;
import com.aeon.elbukaevzaurtest.elbukaevzaurtest.entity.Payments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ILoginDao userRepository;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        // Create initial users
        createUserIfNotFound("test");
        alreadySetup = true;
    }

    @Transactional
    void createUserIfNotFound(final String name) {
        Login user = userRepository.findWithRoleByUsername(name);
        if (user == null) {
            user = new Login(name, passwordEncoder.encode("test"));
            user = userRepository.save(user);
            Payments payments = new Payments(8, 0, user.getId(), CurrencyTypeEnum.USD, 0L);
            paymentsRepository.save(payments);
        }
    }
}
