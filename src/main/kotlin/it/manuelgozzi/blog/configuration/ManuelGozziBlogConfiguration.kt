package it.manuelgozzi.blog.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.stereotype.Component

/**
 * @author Manuel Gozzi
 */
@Component
@ConfigurationProperties(prefix = "app")
data class ManuelGozziBlogConfiguration(val auth: Auth = Auth()) {

    data class Auth(var basic: BasicAuthorization = BasicAuthorization())

    data class BasicAuthorization(var username: String? = null, var password: String? = null)
}