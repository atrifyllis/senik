package gr.fnik

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FnikApplication

fun main(args: Array<String>) {
	runApplication<FnikApplication>(*args)
}
