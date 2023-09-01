package com.coder.login.service;

import com.coder.login.DTORequest.LoginRequest;
import com.coder.login.DTOResponse.LoginResponse;
import com.coder.login.model.Login;
import com.coder.login.repository.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    public LoginRepo loginRepo;

    @Autowired
    public PasswordEncoder passwordEncoder;


    public Login createUserService(LoginRequest loginRequest) {

        Login login = new Login();
        login.setUsername(loginRequest.getUsername());
        login.setDepartment(loginRequest.getDepartment());
        login.setPassword(passwordEncoder.encode(loginRequest.getPassword()));

        return loginRepo.save(login);

    }

    public LoginResponse updateUserService(LoginRequest loginRequest) {
        Login login = new Login();

        Optional<Login> loginOptional = loginRepo.findByUsername(loginRequest.getUsername());
        if (loginOptional.isPresent()) {
            login.setUsername(loginRequest.getUsername());
            login.setDepartment(loginRequest.getDepartment());
            login.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
        }
        else {
            return new LoginResponse("User Does not Exist");
        }
        return new LoginResponse("Updated Successfully");

    }

    public LoginResponse CheckLoginCredentials(LoginRequest loginRequest) {

        //String message = "";
        Optional<Login> loginOptional= loginRepo.findByUsername(loginRequest.getUsername());
        if(loginOptional.isPresent()) {
            Login user = loginOptional.get();
            String password = user.getPassword();
            String password1 = loginRequest.getPassword();

            boolean matches = passwordEncoder.matches(password1, password);
            if (matches) {
                navigateToRespectivePage(loginRequest);
                return new LoginResponse("Login Success");
            }
            else {
                return new LoginResponse("Password Incorrect");
            }

        }
        else {
            return new LoginResponse("Username does not exist");
        }
    }

    public String navigateToRespectivePage(LoginRequest loginRequest) {

        Optional<Login> loginOptional = loginRepo.findByUsername(loginRequest.getUsername());
        if (loginOptional.isPresent()) {

            Login login = loginOptional.get();
            String Department = login.getDepartment();

            switch (Department) {
                case "ADMIN":
                    System.out.println("ADMIN PAGE");
                    return "ADMIN PAGE";
                case "STUDENT":
                    System.out.println("STUDENT PAGE");
                    return "STUDENT PAGE";
            }
        }
        System.out.println("NO PAGE");
        return "NO PAGE";
    }

}
