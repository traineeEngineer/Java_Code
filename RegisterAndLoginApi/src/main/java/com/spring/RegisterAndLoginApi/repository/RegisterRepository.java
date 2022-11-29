package com.spring.RegisterAndLoginApi.repository;

import com.spring.RegisterAndLoginApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<User,Integer> {
    User findUserByEmail(String email);

    User findByEmailAndPassword(String email, String password);
}
