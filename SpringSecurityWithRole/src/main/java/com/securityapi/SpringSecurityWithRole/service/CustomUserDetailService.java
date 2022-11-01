package com.securityapi.SpringSecurityWithRole.service;

import com.securityapi.SpringSecurityWithRole.model.Appuser;
import com.securityapi.SpringSecurityWithRole.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Appuser user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user is not found");
        }

        return new CustomUserDetail(user);
    }
}
