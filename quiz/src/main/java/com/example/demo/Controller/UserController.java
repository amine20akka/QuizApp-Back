package com.example.demo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.UserDTO;
import com.example.demo.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    UserService userService;

    @GetMapping("/get/{username}/{password}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username, @PathVariable String password) {
        try {
            return new ResponseEntity<>(userService.getUser(username, password), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred : ", e);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.ok(userService.addUser(userDTO));
        } catch (Exception e) {
            logger.error("An error occurred : ", e);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    
    @PostMapping("/authenticate")
    public ResponseEntity<UserDTO> authenticateUser(@RequestBody UserDTO userDTO) {
        try {
            return ResponseEntity.ok(userService.authenticateUser(userDTO));
        } catch (Exception e) {
            logger.error("An error occurred : ", e);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

}