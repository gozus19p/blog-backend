package it.manuelgozzi.blog.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Manuel Gozzi
 */
@RestController
@RequestMapping("/api/v1/status")
public class StatusController {

    @GetMapping
    public String status() {

        return "OK";
    }
}
