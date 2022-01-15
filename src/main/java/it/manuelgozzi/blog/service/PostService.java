package it.manuelgozzi.blog.service;

import it.manuelgozzi.blog.model.Post;
import it.manuelgozzi.blog.repository.PostMongoRepository;
import it.manuelgozzi.blog.repository.PostMongoReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @author Manuel Gozzi
 */
@Slf4j
@Service
public class PostService {

    private final PostMongoReactiveRepository postMongoReactiveRepository;
    private final PostMongoRepository postMongoRepository;

    @Autowired
    public PostService(PostMongoReactiveRepository postMongoReactiveRepository, PostMongoRepository postMongoRepository) {

        this.postMongoReactiveRepository = postMongoReactiveRepository;
        this.postMongoRepository = postMongoRepository;
    }

    /**
     * It searches for a {@link Post} instance based on its id.
     *
     * @param id is the MongoDB identifier associated to the blog post
     * @return the given blog post
     */
    public Mono<Post> singlePostById(final String id) {

        if (id == null || id.replaceAll("\\s", "").isEmpty()) {

            log.error("No {id} given");
            throw new IllegalArgumentException();
        }

        log.debug("Searching for single post by id [{}]", id);
        return this.postMongoReactiveRepository.findById(id);
    }

    /**
     * It saves a new {@link Post} inside MongoDB instance.
     * @param newPost is the new {@link Post} instance to save
     * @return the state after saving process
     */
    public Mono<Post> writePost(Post newPost) {

        if (newPost == null || newPost.cannotBeWritten()) {

            log.error("Post is null, or it cannot be written");
            throw new IllegalArgumentException();
        }

        newPost.getHeader().setCreateDate(new Date());
        return this.postMongoReactiveRepository.save(newPost);
    }

    /**
     * It updates a single existing {@link Post} instance.
     * @param existingPost is the {@link Post} that needs to be updated
     * @return the {@link Mono<Post>} resulting
     */
    public Mono<Post> updatePost(Post existingPost) {

        if (existingPost == null || existingPost.cannotBeWritten() || existingPost.getId() == null || existingPost.getId().replaceAll("\\s", "").isEmpty()) {

            log.error("Post has a state that does not permit to update it");
            throw new IllegalArgumentException();
        }

        existingPost.getHeader().setUpdateDate(new Date());
        return this.postMongoReactiveRepository.save(existingPost);
    }

    /**
     * It searches using an example.
     * @param example is the {@link Post} to use as an example
     * @return the {@link Flux<Post>}
     */
    public Flux<Post> search(Post example) {

        if (example == null) {

            log.warn("[{}] {example} parameter is null", Post.class.getName());
            return Flux.empty();
        }

        return Flux.fromIterable(
                this.postMongoRepository.findAll(
                        Example.of(example)
                )
        );
    }
}
