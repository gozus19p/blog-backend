package it.manuelgozzi.blog.service

import it.manuelgozzi.blog.model.Post
import it.manuelgozzi.blog.repository.PostMongoReactiveRepository
import it.manuelgozzi.blog.repository.PostMongoRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

/**
 * @author manu
 */
@Service
class PostService(val postMongoReactiveRepository: PostMongoReactiveRepository, val postMongoRepository: PostMongoRepository) {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * It searches for a {@link Post} instance based on its id.
     *
     * @param id is the MongoDB identifier associated to the blog post
     * @return the given blog post
     */
    fun singlePostById(id: String): Mono<Post> {

        return this.postMongoReactiveRepository.findById(id)
    }

    /**
     * It saves a new {@link Post} inside MongoDB instance.
     * @param newPost is the new {@link Post} instance to save
     * @return the state after saving process
     */
    fun writePost(post: Post): Mono<Post> {

        if (post.cannotBeWritten()) {

            logger.error("Post cannot be written")
            throw IllegalArgumentException()
        }

        post.header?.createdDate = Date()
        return this.postMongoReactiveRepository.save(post)
    }

    /**
     * It updates a single existing {@link Post} instance.
     * @param existingPost is the {@link Post} that needs to be updated
     * @return the {@link Mono<Post>} resulting
     */
    fun updatePost(post: Post): Mono<Post> {

        if (post.cannotBeWritten()) {

            logger.error("Post cannot be written")
            throw IllegalArgumentException()
        }

        if (post.id == null) {

            logger.error("Post [${post.header?.title}] has no id, it can't be updated")
            throw IllegalArgumentException()
        }

        post.header?.updateDate = Date()
        return this.postMongoReactiveRepository.save(post)
    }

    /**
     * It searches using an example.
     * @param example is the {@link Post} to use as an example
     * @return the {@link Flux<Post>}
     */
    fun search(example: Post): Flux<Post> {

        return Flux.fromIterable(this.postMongoRepository.findAll(Example.of(example)))
    }
}