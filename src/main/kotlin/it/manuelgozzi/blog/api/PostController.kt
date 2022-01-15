package it.manuelgozzi.blog.api

import it.manuelgozzi.blog.model.Post
import it.manuelgozzi.blog.service.PostService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * @author Manuel Gozzi
 */
@RestController
@RequestMapping(value = ["/api/v1/post"], produces = [MediaType.APPLICATION_JSON_VALUE], consumes = [MediaType.APPLICATION_JSON_VALUE])
class PostController(var postService: PostService) {

    /**
     * It retrieves a single {@link Post} instance.
     * @param id is the single {@link String} identifier to search for
     * @return the {@link Mono<Post>} found
     */
    @GetMapping(value = ["/{id}"])
    fun singlePost(@PathVariable(value = "id") id: String): Mono<Post> {

        return this.postService.singlePostById(id)
    }

    /**
     * It executes a search giving a {@link Post} as an example.
     * @param example is the {@link Post} instance that will be used as an example to search on the database
     * @return the {@link Flux<Post>} produced from the search operation
     */
    @PostMapping(value = ["/search"])
    fun search(@RequestBody post: Post): Flux<Post> {

        return this.postService.search(post)
    }

    /**
     * It saves a single {@link Post} inside the MongoDB.
     * @param post is the {@link Post} that needs to be saved
     * @return the saved {@link Post}
     */
    @PutMapping
    fun savePost(@RequestBody post: Post): Mono<Post> {

        return this.postService.writePost(post)
    }

    /**
     * It updates a single {@link Post} inside the MongoDB.
     * @param post is the existing {@link Post} that needs to be saved
     * @return the saved {@link Post}
     */
    @PostMapping
    fun updateExistingPost(@RequestBody post: Post): Mono<Post> {

        return this.postService.updatePost(post)
    }
}