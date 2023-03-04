package gr.senik.netcalculator.adapters.primary.messaging

import gr.senik.netcalculator.application.ports.`in`.CalculateIncomeUseCase
import gr.senik.netcalculator.application.ports.`in`.dto.CalculationCommand
import jakarta.validation.Valid
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

/**
 * Primary adapter that listens to kafka topic for [CalculationCommand]s.
 *
 * Note that validation works exactly as in REST controller adapters!
 */
@Component
class IncomeCalculationListener(
    private val calculateIncomeUseCase: CalculateIncomeUseCase,
) {

    @KafkaListener(topics = ["calculation.commands"], groupId = "income-calculation-consumer-group", errorHandler = "validationErrorHandler")
    fun calculateIncome(@Payload @Valid command: CalculationCommand) {
        calculateIncomeUseCase.calculate(command)
    }
}
