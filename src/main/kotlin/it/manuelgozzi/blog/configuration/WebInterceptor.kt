package it.manuelgozzi.blog.configuration

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import java.nio.charset.StandardCharsets
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author Manuel Gozzi
 */
@Component
class WebInterceptor(var configuration: ManuelGozziBlogConfiguration) : HandlerInterceptor {

    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        val auth: String? = request.getHeader("Authorization")
        if (auth == null || auth.replace("\\s".toRegex(), "").isEmpty() || !auth.contains("Basic ")) {

            logger.warn("Received unauthorized request [${request.requestURI}]")
            response.status = 401
            return false
        }

        try {

            val decoded = String(Base64.getDecoder().decode(auth.removePrefix("Basic ")), StandardCharsets.UTF_8)
            if (!decoded.contains(":")) {

                logger.warn("Received unauthorized request[${request.requestURI}], malformed authorization header")
                response.status = 401
                return false
            }

            val userAndPassword = decoded.split(":".toRegex())
            if (userAndPassword.size != 2) {

                logger.warn("Received unauthorized request[${request.requestURI}], format is invalid")
                response.status = 401
                return false
            }

            return userAndPassword[0] == this.configuration.auth?.basic?.username
                    && userAndPassword[1] == this.configuration.auth?.basic?.password
        } catch (e: Exception) {

            logger.error("Something went wrong during base64 decodification [${e.message}], request [${request.requestURI}] is unauthorized")
            response.status = 401
            return false
        }
    }
}