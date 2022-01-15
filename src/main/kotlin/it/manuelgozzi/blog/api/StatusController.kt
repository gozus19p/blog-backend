package it.manuelgozzi.blog.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Manuel Gozzi
 */
@RestController
@RequestMapping("/api/v1/status")
class StatusController {

    @GetMapping
    fun status(): String {

        return "OK"
    }
}