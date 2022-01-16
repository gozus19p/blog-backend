package it.manuelgozzi.blog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

/**
 * @author Manuel Gozzi
 */
@SpringBootApplication
@EnableReactiveMongoRepositories
@EnableMongoRepositories
open class ManuelGozziBlogApplication

fun main(args: Array<String>) {
    runApplication<ManuelGozziBlogApplication>(*args)
}
