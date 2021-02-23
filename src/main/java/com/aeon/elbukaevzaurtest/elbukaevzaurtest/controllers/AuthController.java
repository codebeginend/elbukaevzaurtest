package com.aeon.elbukaevzaurtest.elbukaevzaurtest.controllers;

import com.aeon.elbukaevzaurtest.elbukaevzaurtest.services.LoginAttemptService;
import com.aeon.elbukaevzaurtest.elbukaevzaurtest.entity.Login;
import com.aeon.elbukaevzaurtest.elbukaevzaurtest.security.JwtTokenProvider;
import com.aeon.elbukaevzaurtest.elbukaevzaurtest.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserDetailsServiceImpl usersService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private LoginAttemptService loginAttemptService = new LoginAttemptService();

    @RequestMapping(value = "signin", method = RequestMethod.POST)
    public ResponseEntity signin(ServletRequest req, @RequestBody Login login) {
        String ip = req.getRemoteAddr();
        if(!loginAttemptService.isBlocked(ip)){
                UserDetails users = this.usersService.loadUserByUsername(login.getUsername());
                if(users != null && passwordEncoder.matches(login.getPassword(), users.getPassword())){
                    loginAttemptService.loginSucceeded(ip);
                    String token = jwtTokenProvider.createToken(users.getUsername());
                    Map<Object, Object> model = new HashMap<>();
                    model.put("username", users.getUsername());
                    model.put("token", token);
                    return new ResponseEntity(model, HttpStatus.OK);
                }else{
                    loginAttemptService.loginFailed(ip);
                    return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
                }
        }else{
            return new ResponseEntity<String>("Превыщен лимит неудачных попыток", HttpStatus.BAD_REQUEST);
        }

    }
}
