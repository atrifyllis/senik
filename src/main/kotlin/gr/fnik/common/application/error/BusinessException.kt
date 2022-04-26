package gr.fnik.common.application.error

/**
 * Generic Business Exception for whole project.
 * All business exceptions should extend from this one.
 * For exception handling see [GlobalExceptionHandler]
 */
open class BusinessException(message: String) : RuntimeException(message)
