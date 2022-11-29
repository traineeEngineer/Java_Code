package com.spring.RegisterAndLoginApi.controller;

import com.spring.RegisterAndLoginApi.model.User;
import com.spring.RegisterAndLoginApi.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) throws Exception {
        String tempEmail = user.getEmail();
        if(tempEmail!=null && !"".equals(tempEmail)){
            User userEmail = registerService.fetchUserByEmail(tempEmail);
            if(userEmail!=null){
                throw new Exception("User already exists");
            }
        }
        return registerService.saveUser(user);
    }

    @GetMapping("/login")
    public User loginUser(@RequestBody User user) throws Exception {
        String tempEmail = user.getEmail();
        String tempPassword = user.getPassword();
        User newUser=null;
        if (tempEmail!=null && tempPassword!=null){
             newUser = registerService.fetchUserEmailAndPassword(tempEmail, tempPassword);
        }
        if (newUser==null){
            throw new Exception("user not exists");
        }
        return newUser;
    }
}
