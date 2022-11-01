package com.securityapi.SpringSecurityWithRole.controller;

import com.securityapi.SpringSecurityWithRole.model.Appuser;
import com.securityapi.SpringSecurityWithRole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    private UserService service;

    @GetMapping("/alluser")
    @PreAuthorize("hasAnyAuthority('user','ADMIN')")
    List<Appuser> findAllUser(){
        return  service.findAllUser();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<Appuser> getUserById(@PathVariable("id") Long id) throws Exception {
        return  service.findByUserId(id);
    }

    @PostMapping("/users")
    ResponseEntity<Appuser> createUser(@RequestBody Appuser user){
        return  service.createUser(user);
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasAuthority('user','EDITOR')")
    ResponseEntity<Appuser> updateUser(@RequestBody Appuser user, Long id) throws Exception {
       return service.updateUser(user, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<String> deleteUserById(@PathVariable Long id) throws Exception {
        return  service.deleteUserById(id);
    }

}
