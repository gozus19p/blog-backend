package it.manuelgozzi.blog.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author Manuel Gozzi
 */
@Document(collection = "blog_post")
data class Post(@Id var id: String?, var header: Header?, var content: Content?, var author: Author?) {

    fun cannotBeWritten(): Boolean {

        if (this.author == null) {

            return true
        }

        if (this.header == null) {

            return true
        }

        if (this.content == null) {

            return true
        }

        // It returns true if the body is null or if the body of the content is null or empty
        return this.content?.body == null || (this.content?.body?.trim()?.isEmpty() ?: true)
    }

    /**
     * It represents the author of the blog post.
     *
     * @author Manuel Gozzi
     */
    data class Author(var name: String?, var surname: String?, var username: String?)

    /**
     * It represents the header of the blog post.
     *
     * @author Manuel Gozzi
     */
    data class Header(var title: String?, var createdDate: Date?, var updateDate: Date?, var tags: List<String> = ArrayList(),
                      var theme: String?)

    /**
     * It represents the content of the blog post.
     *
     * @author Manuel Gozzi
     */
    data class Content(var html: Boolean = true, var body: String?)
}