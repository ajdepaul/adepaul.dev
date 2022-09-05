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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

@Controller
@RequestMapping("projects")
public class ProjectsController {

    private final ProjectsService projectsService;

    @Autowired
    public ProjectsController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @GetMapping
    public String projects(Model model) throws IOException {
        final var projects = projectsService.getAllProjectDetails().stream()
                .sorted((p1, p2) -> p2.getPublicationDate().compareTo(p1.getPublicationDate())) // reversed order
                .toList();

        model.addAttribute("projects", projects);

        final var technologies = new HashSet<String>();
        projects.forEach(proj -> technologies.addAll(proj.getTechnologies()));
        model.addAttribute("technologies", technologies.stream().sorted().toList());

        final var languages = new HashSet<String>();
        projects.forEach(proj -> languages.addAll(proj.getLanguages()));
        model.addAttribute("languages", languages.stream().sorted().toList());

        return "projects";
    }

    @GetMapping("{project}")
    public String project(Model model, @PathVariable("project") String project, HttpServletResponse response) throws IOException {

        final var projectDetails = projectsService.getAllProjectDetails().stream()
                .filter(proj -> proj.getArticleLink().equals("/projects/" + project))
                .findFirst();

        if (projectDetails.isPresent()) {
            model.addAttribute("title", projectDetails.get().getTitle());
            model.addAttribute("publicationDateStr", projectDetails.get().getPublicationDateStr());
            model.addAttribute("completionDateStr", projectDetails.get().getCompletionDateStr());
            model.addAttribute("article", projectDetails.get().getArticleLink() + "/article");

            final var repositoryLink = projectDetails.get().getRepositoryLink();

            if (repositoryLink != null) {

                model.addAttribute("repositoryLink", repositoryLink);

                if (repositoryLink.contains("github.com")) {
                    model.addAttribute("repositoryIcon", "fa-brands fa-github");
                } else if (repositoryLink.contains("gitlab.com")) {
                    model.addAttribute("repositoryIcon", "fa-brands fa-gitlab");
                }
            }

            return "project";
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            model.addAttribute("error", ": 404");
            return "error";
        }
    }
}
