package it.manuelgozzi.blog.api;

import org.springframework.web.bind.annotation.*;

/**
 * @author Manuel Gozzi
 */
@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    @PostMapping
    public String login() {

        return null;
    }

    @DeleteMapping
    public String logout() {

        return null;
    }
}
