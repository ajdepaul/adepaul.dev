/*
 * Copyright © 2021 Anthony DePaul
 * Licensed under the MIT License https://adepaul.mit-license.org/
 */
package dev.adepaul.website.controller;

import dev.adepaul.website.model.ProjectDetails;
import dev.adepaul.website.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.LinkedList;

@Controller
@RequestMapping("home")
public class HomeController {

    private final ProjectsService projectsService;

    @Autowired
    public HomeController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @GetMapping
    public String home(Model model) throws IOException {
        final var featuredProjects = projectsService.getFeaturedProjectDetails();

        var featuredProjectsOdds = new LinkedList<ProjectDetails>();
        var featuredProjectsEvens = new LinkedList<ProjectDetails>();

        for (int i = 0; i < featuredProjects.size(); i++) {
            if ((i + 1) % 2 == 1) featuredProjectsOdds.add(featuredProjects.get(i));
            else featuredProjectsEvens.add(featuredProjects.get(i));
        }

        model.addAttribute("featuredProjectsOdds", featuredProjectsOdds);
        model.addAttribute("featuredProjectsEvens", featuredProjectsEvens);
        return "home";
    }
}
