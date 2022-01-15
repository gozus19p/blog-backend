package it.manuelgozzi.blog.api;

import it.manuelgozzi.blog.model.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Manuel Gozzi
 */
@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @GetMapping("/{id}")
    public Post singlePost(@PathVariable("id") String id) {

        return Post.builder()
                .id(id)
                .title("Title")
                .body("Sample body")
                .date(new Date())
                .html(true)
                .build();
    }
}
