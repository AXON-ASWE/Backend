package com.swe.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import io.swagger.v3.oas.annotations.Hidden;

@Controller
public class HomeController {

    /**
     * Redirect root path to Swagger UI
     * This endpoint is hidden from Swagger documentation
     */
    @Hidden
    @GetMapping("/")
    public RedirectView redirectToSwagger() {
        return new RedirectView("/swagger-ui.html");
    }

    /**
     * Alternative home endpoint that also redirects to Swagger UI
     * This endpoint is hidden from Swagger documentation
     */
    @Hidden
    @GetMapping("/home")
    public RedirectView homeToSwagger() {
        return new RedirectView("/swagger-ui.html");
    }
}
