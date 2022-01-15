package it.manuelgozzi.blog.repository;

import it.manuelgozzi.blog.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Manuel Gozzi
 */
@Repository
public interface PostMongoRepository extends MongoRepository<Post, String> {
}
