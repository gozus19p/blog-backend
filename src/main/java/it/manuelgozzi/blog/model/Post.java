package it.manuelgozzi.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Manuel Gozzi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private String id;
    private Author author;
    private String title;
    private String body;
    private boolean html;
    private Date date;
}
