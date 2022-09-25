package com.CustomizeAuth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.CustomizeAuth.entity.Userinfo;

@Repository
public interface UserInfoRepository extends JpaRepository<Userinfo, Long> {
	
	Optional<Userinfo> findByEmail(String email);
}
