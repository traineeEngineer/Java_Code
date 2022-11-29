package com.spring.RegisterAndLoginApi.service;

import com.spring.RegisterAndLoginApi.model.User;
import com.spring.RegisterAndLoginApi.repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository repository;

    public User saveUser(User user){
       return repository.save(user);
    }

    public User fetchUserByEmail(String email){
        return  repository.findUserByEmail(email);
    }

    public User fetchUserEmailAndPassword(String email, String password) {
        return repository.findByEmailAndPassword(email,password);
    }
}
