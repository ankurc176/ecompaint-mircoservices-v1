package com.coder.login.repository;

import com.coder.login.DTORequest.LoginRequest;
import com.coder.login.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepo extends JpaRepository<Login, String> {

    Optional<Login> findByUsername(String Username);

    //LoginRequest findByUsernameAndPassword(String username, String Password);
}
