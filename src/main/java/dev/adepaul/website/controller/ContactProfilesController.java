/*
 * Copyright © 2021 Anthony DePaul
 */
package dev.adepaul.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("contact-profiles")
public class ContactProfilesController {

    @GetMapping
    public String contactProfiles() {
        return "contact-profiles";
    }
}
