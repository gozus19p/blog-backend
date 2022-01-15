package it.manuelgozzi.blog.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Manuel Gozzi
 */
@Data
@Document(collection = "blog_post")
public class Post {

    @Id
    private String id;
    private Author author;
    private Header header;
    private Content content;

    /**
     * It checks if the current post has some issues that does not allow to save it on database.
     * @return <code>true</code> if the {@link Post} cannot be persisted in database
     */
    public boolean cannotBeWritten() {

        if (this.author == null) {

            return true;
        }

        if (this.header == null) {

            return true;
        }

        if (this.content == null) {

            return true;
        }

        return this.content.getBody() == null || this.content.getBody().trim().isEmpty();
    }

    /**
     * It represents the author of the blog post.
     *
     * @author Manuel Gozzi
     */
    @Data
    public static class Author {

        private String name;
        private String surname;
        private String username;
    }

    /**
     * It represents the header of the blog post.
     *
     * @author Manuel Gozzi
     */
    @Data
    public static class Header {

        private String title;
        private Date createDate;
        private Date updateDate;
        private List<String> tags = new ArrayList<>();
        private String theme;
    }

    /**
     * It represents the content of the blog post.
     *
     * @author Manuel Gozzi
     */
    @Data
    public static class Content {

        private boolean html;
        private String body;
    }
}
