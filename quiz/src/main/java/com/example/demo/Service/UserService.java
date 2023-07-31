package com.example.demo.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.UserDAO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final AuthenticationManager authenticationManager;

    private final JwtUtilService jwtService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserDAO userDAO;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserDTO getUser(String username, String password) {
        return UserDTO.toDTO(userDAO.findByUsernameAndPassword(username, password));
    }
    
    public String addUser(User userAdded) {
        try {
            userAdded.setPassword(passwordEncoder.encode(userAdded.getPassword()));
            userDAO.save(userAdded);
            return jwtService.generateToken(userAdded);
        }        
        catch(Exception e)
        {
            logger.error("An error occurred", e);
        }
        return null;
    }

    public String authenticateUser(User userAuth) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuth.getUsername(), userAuth.getPassword())
            );
            User user = userDAO.findByUsername(userAuth.getUsername());
            return jwtService.generateToken(user);
        }        
        catch(Exception e)
        {
            logger.error("An error occurred", e);
        }
        return null;
    }

}
