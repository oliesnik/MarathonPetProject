package com.softserve.edu.controllers;

import com.softserve.edu.model.Marathon;
import com.softserve.edu.service.MarathonService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@Data
@AllArgsConstructor
public class MarathonController {
    private MarathonService marathonService;

    @GetMapping("/marathons")
    public String getAllMarathons(Model model) {
        List<Marathon> marathons = marathonService.getAll();
        model.addAttribute("marathons", marathons);
        return "marathons";
    }

    @GetMapping("/marathon")
    public String getMarathon(Model model) {
        return "marathon";
    }

    @GetMapping("/marathon/add")
    public String addMarathon(Model model) {
        return "marathon";
    }

    @PostMapping("/marathon/add")
    public String addMarathon(@RequestParam String title, Model model) {
        Marathon marathon = new Marathon(title);
        marathonService.createOrUpdate(marathon);
        return "redirect:/marathons";
    }

    @GetMapping("/marathon/edit/{id}")
    public String editMarathon(@PathVariable(value = "id") Long id, Model model) {
        if (marathonService.getMarathonById(id) == null) {
            return "redirect:/marathons";
        }
        Marathon marathon = marathonService.getMarathonById(id);
        model.addAttribute("marathon", marathon);
        return "marathon";
    }

    @PostMapping("/marathon/edit/{id}")
    public String updateMarathon(@PathVariable(value = "id") Long id, @RequestParam String title, Model model) {
        Marathon marathon = marathonService.getMarathonById(id);
        marathon.setTitle(title);
        marathonService.createOrUpdate(marathon);
        return "redirect:/marathons";
    }

    @GetMapping("/marathon/delete/{id}")
    public String deleteMarathon(@PathVariable(value = "id") Long id) {
        marathonService.deleteMarathonById(id);
        return "redirect:/marathons";
    }
}

