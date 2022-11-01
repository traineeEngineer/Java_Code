package com.securityapi.SpringSecurityWithRole.service;

import com.securityapi.SpringSecurityWithRole.model.Appuser;
import com.securityapi.SpringSecurityWithRole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    PasswordEncoder encoder;

    public List<Appuser> findAllUser(){
        return repo.findAll();
    }

    public ResponseEntity<Appuser> findByUserId(Long id) throws Exception {
        Appuser appuser= repo.findById(id).orElseThrow(()->new Exception("user id not found"));
        return  ResponseEntity.ok().body(appuser);
    }

    public ResponseEntity<Appuser> createUser(Appuser user){
        String encPwd=encoder.encode(user.getPassword());
        user.setPassword(encPwd);
        return  ResponseEntity.ok(repo.save(user));
    }

    public ResponseEntity<Appuser> updateUser(Appuser userData, Long id) throws Exception {
       Appuser existUser=  repo.findById(id).orElseThrow(()->new Exception("suer id not found"));
       existUser.setId(id);
       return  ResponseEntity.ok(repo.save(userData));
    }

    public ResponseEntity<String> deleteUserById(Long id) throws Exception {
        Appuser existUSer=repo.findById(id).orElseThrow(()->new Exception("user id not found"));
        repo.deleteById(existUSer.getId());
        return ResponseEntity.ok().body("user id deleted"+id);
    }

}
