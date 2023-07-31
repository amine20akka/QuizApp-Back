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
    
    public UserDTO addUser(UserDTO userAdded) {
        try {
            userAdded.setPassword(passwordEncoder.encode(userAdded.getPassword()));
            userDAO.save(UserDTO.toEntity(userAdded));
            userAdded.setToken(jwtService.generateToken(UserDTO.toEntity(userAdded))); 
            return userAdded;
        }        
        catch(Exception e)
        {
            logger.error("An error occurred : ", e);
        }
        return null;
    }

    public UserDTO authenticateUser(UserDTO userAuth) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userAuth.getUsername(), userAuth.getPassword())
            );
            UserDTO userDTO = UserDTO.toDTO(userDAO.findByUsername(userAuth.getUsername()));
            userDTO.setToken(jwtService.generateToken(UserDTO.toEntity(userDTO))); 
            return userDTO;
        }        
        catch(Exception e)
        {
            logger.error("An error occurred : ", e);
        }
        return null;
    }

}
