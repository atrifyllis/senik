package gr.senik

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SenikApplication

fun main(args: Array<String>) {
    runApplication<SenikApplication>(*args)
}
