package it.manuelgozzi.blog.repository

import it.manuelgozzi.blog.model.Post
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

/**
 * @author Manuel Gozzi
 */
@Repository
interface PostMongoReactiveRepository : ReactiveCrudRepository<Post, String>