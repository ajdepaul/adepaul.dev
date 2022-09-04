/*
 * Copyright © 2021 Anthony DePaul
 * Licensed under the MIT License https://adepaul.mit-license.org/
 */
package dev.adepaul.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("contact-profiles")
@Controller
public class ContactProfilesController {

    @GetMapping
    public String contactProfiles() {
        return "contact-profiles";
    }
}
