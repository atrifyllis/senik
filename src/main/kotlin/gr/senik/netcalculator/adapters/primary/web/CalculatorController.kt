package gr.senik.netcalculator.adapters.primary.web

import gr.senik.netcalculator.application.ports.`in`.CalculateIncomeUseCase
import gr.senik.netcalculator.application.ports.`in`.LoadCalculatorDataUseCase
import gr.senik.netcalculator.application.ports.`in`.dto.CalculationCommand
import gr.senik.netcalculator.application.ports.`in`.dto.CalculationResultDto
import gr.senik.netcalculator.application.ports.`in`.dto.ReferenceDataDto
import jakarta.validation.Valid
import mu.KotlinLogging
import org.springframework.web.bind.annotation.*

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping
class CalculatorController(
    private val loadCalculatorDataUseCase: LoadCalculatorDataUseCase,
    private val calculateIncomeUseCase: CalculateIncomeUseCase,
) {
    @GetMapping("/reference-data")
    fun getReferenceData(): ReferenceDataDto {
        log.info { "Retrieving all reference data" }
        return loadCalculatorDataUseCase.getReferenceData()
    }

    @PostMapping("/calculate-income")
    fun calculateNetIncome(@Valid @RequestBody command: CalculationCommand): CalculationResultDto =
        calculateIncomeUseCase.calculate(command)
}
