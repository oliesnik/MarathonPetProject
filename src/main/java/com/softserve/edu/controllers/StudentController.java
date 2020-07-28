package com.softserve.edu.controllers;


import com.softserve.edu.model.User;

import com.softserve.edu.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@Data
@AllArgsConstructor
public class StudentController {
    private UserService userService;
    
}
