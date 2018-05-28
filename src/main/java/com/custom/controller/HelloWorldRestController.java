package com.custom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.custom.entity.User;
import com.custom.service.UserService;

@Controller
public class HelloWorldRestController {
  
    @Autowired
    UserService userService; 
  

    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    @ResponseBody
    public List<User> listAllUsers() {
        return Optional.ofNullable(userService.findAll()).orElse(new ArrayList<>());
    }
  
}