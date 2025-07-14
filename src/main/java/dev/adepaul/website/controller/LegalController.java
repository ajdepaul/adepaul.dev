/*
 * Copyright © 2021 Anthony DePaul
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
@RequestMapping("/legal")
public class LegalController {

    @GetMapping
    public String home(Model model) throws IOException {
        return "legal";
    }
}
