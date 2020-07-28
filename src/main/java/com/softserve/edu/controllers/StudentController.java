package com.softserve.edu.controllers;


import com.softserve.edu.model.Marathon;
import com.softserve.edu.model.User;

import com.softserve.edu.service.MarathonService;
import com.softserve.edu.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Data
@AllArgsConstructor
public class StudentController {
    private UserService userService;
    private MarathonService marathonService;

    @GetMapping("/students")
    public String getAllStudens(Model model) {
        List<User> students = userService.getAllByRole("Trainee");
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/student/add")
    public String addStudent(Model model) {
        return "student";
    }

    @PostMapping("/student/add")
    public String addStudent(@RequestParam String firstName, @RequestParam String lastName,
                             @RequestParam String email, @RequestParam String password, Model model) {
        User student = new User();
        student.setRole(User.Role.TRAINEE);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setPassword(password);
        userService.createOrUpdateUser(student);
        return "redirect:/students";
    }

    @GetMapping("/student/edit/{id}")
    public String editStudent(@PathVariable(value = "id") Long id, Model model) {
        if (userService.getUserById(id) == null) {
            return "redirect:/students";
        }
        User student = userService.getUserById(id);
        model.addAttribute("student", student);
        return "student";
    }

    @PostMapping("/student/edit/{id}")
    public String updateStudent(@PathVariable(value = "id") Long id, @RequestParam String firstName, @RequestParam String lastName,
                                @RequestParam String password, Model model) {
        User student = userService.getUserById(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setPassword(password);
        userService.createOrUpdateUser(student);
        return "redirect:/students";
    }

}
