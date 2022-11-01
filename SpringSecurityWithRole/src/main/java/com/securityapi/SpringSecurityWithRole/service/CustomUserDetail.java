package com.securityapi.SpringSecurityWithRole.service;

import com.securityapi.SpringSecurityWithRole.model.Appuser;
import com.securityapi.SpringSecurityWithRole.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomUserDetail implements UserDetails {

    private Appuser user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roleSet=user.getRoles();
        List<SimpleGrantedAuthority> authority=new ArrayList<>();
        for (Role role:roleSet){
            authority.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authority;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
