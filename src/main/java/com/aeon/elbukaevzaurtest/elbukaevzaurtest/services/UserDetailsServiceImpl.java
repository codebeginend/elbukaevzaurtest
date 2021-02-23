package com.aeon.elbukaevzaurtest.elbukaevzaurtest.services;

import com.aeon.elbukaevzaurtest.elbukaevzaurtest.dao.ILoginDao;
import com.aeon.elbukaevzaurtest.elbukaevzaurtest.entity.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ILoginDao loginDao;



    @Autowired
    private LoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Login login = this.loginDao.findWithRoleByUsername(username);
        return login;
    }
}
