/*
 * Copyright © 2021 Anthony DePaul
 * Licensed under the MIT License https://adepaul.mit-license.org/
 */
package dev.adepaul.website.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SiteErrorController implements ErrorController {

    @RequestMapping("error")
    public String handleError(Model model, HttpServletRequest request) {

        var status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            model.addAttribute("error", ": " + status);
        } else {
            model.addAttribute("error", "");
        }

        return "error";
    }
}
