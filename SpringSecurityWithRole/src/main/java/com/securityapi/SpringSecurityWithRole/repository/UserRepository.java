package com.securityapi.SpringSecurityWithRole.repository;

import com.securityapi.SpringSecurityWithRole.model.Appuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Appuser,Long> {

    Appuser findByUsername(String username);
}
