package it.manuelgozzi.blog.api;

import it.manuelgozzi.blog.model.Post;
import it.manuelgozzi.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Manuel Gozzi
 */
@RestController
@RequestMapping(value = "/api/v1/post", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * It retrieves a single {@link Post} instance.
     * @param id is the single {@link String} identifier to search for
     * @return the {@link Mono<Post>} found
     */
    @GetMapping("/{id}")
    public Mono<Post> singlePost(@PathVariable("id") String id) {

        return this.postService.singlePostById(id);
    }

    /**
     * It executes a search giving a {@link Post} as an example.
     * @param example is the {@link Post} instance that will be used as an example to search on the database
     * @return the {@link Flux<Post>} produced from the search operation
     */
    @PostMapping("/search")
    public Flux<Post> search(@RequestBody Post example) {

        return this.postService.search(example);
    }

    /**
     * It saves a single {@link Post} inside the MongoDB.
     * @param post is the {@link Post} that needs to be saved
     * @return the saved {@link Post}
     */
    @PutMapping
    public Mono<Post> savePost(@RequestBody Post post) {

        return this.postService.writePost(post);
    }

    /**
     * It updates a single {@link Post} inside the MongoDB.
     * @param post is the existing {@link Post} that needs to be saved
     * @return the saved {@link Post}
     */
    @PostMapping
    public Mono<Post> updateExistingPost(@RequestBody Post post) {

        return this.postService.updatePost(post);
    }
}
