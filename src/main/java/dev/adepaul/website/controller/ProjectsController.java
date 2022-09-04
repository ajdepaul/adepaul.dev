/*
 * Copyright © 2021 Anthony DePaul
 * Licensed under the MIT License https://adepaul.mit-license.org/
 */
package dev.adepaul.website.controller;

import dev.adepaul.website.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@RequestMapping("projects")
@Controller
public class ProjectsController {

    private final ProjectsService projectsService;

    @Autowired
    public ProjectsController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @GetMapping
    public String projects(Model model) throws IOException {
        final var projects = projectsService.getAllProjectDetails().stream()
                .map (proj -> {
                    proj.setTechnologiesStr(String.join(",", proj.getTechnologies()));
                    proj.setLanguagesStr(String.join(",", proj.getLanguages()));
                    return proj;
                })
                .sorted((p1, p2) -> {
                    //sort by "[month] <optional date> [year]" date string
                    final var months = List.of("Jan.", "Feb.", "Mar.", "Apr.", "May.", "Jun.", "Jul.", "Aug.", "Sep.", "Oct.", "Nov.", "Dec.");

                    final var p1DateParts = p1.getPublicationDate().split(" ");
                    var p1DateVal = months.indexOf(p1DateParts[0]) * 31 + Integer.parseInt(p1DateParts[p1DateParts.length - 1]) * 31 * 12;
                    if (p1DateParts.length == 3) p1DateVal += Integer.parseInt(p1DateParts[1].substring(0, p1DateParts[1].length() - 1));

                    final var p2DateParts = p2.getPublicationDate().split(" ");
                    var p2DateVal = months.indexOf(p2DateParts[0]) * 31 + Integer.parseInt(p2DateParts[p2DateParts.length - 1]) * 31 * 12;
                    if (p2DateParts.length == 3) p2DateVal += Integer.parseInt(p2DateParts[1].substring(0, p2DateParts[1].length() - 1));

                    // reversed order
                    return p2DateVal - p1DateVal;
                }).toList();

        model.addAttribute("projects", projects);

        final var technologies = new HashSet<String>();
        projects.forEach(proj -> technologies.addAll(proj.getTechnologies()));
        model.addAttribute("technologies", technologies.stream().sorted().toList());

        final var languages = new HashSet<String>();
        projects.forEach(proj -> languages.addAll(proj.getLanguages()));
        model.addAttribute("languages", languages.stream().sorted().toList());

        return "projects";
    }
}
