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

    /**
     * Classic status API prepared to integrate the microservice inside PAAS architecture.
     * @return <code>OK</code> if the system is up and running
     */
    @GetMapping
    public String status() {

        return "OK";
    }
}
