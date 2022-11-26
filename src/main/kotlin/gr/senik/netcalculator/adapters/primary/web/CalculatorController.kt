package gr.senik.netcalculator.adapters.primary.web

import gr.senik.netcalculator.application.ports.`in`.web.CalculateIncomeUseCase
import gr.senik.netcalculator.application.ports.`in`.web.LoadCalculatorDataUseCase
import gr.senik.netcalculator.application.ports.`in`.web.dto.CalculationCommand
import org.springframework.web.bind.annotation.*
import jakarta.validation.Valid

@RestController
@RequestMapping
class CalculatorController(
    private val loadCalculatorDataUseCase: LoadCalculatorDataUseCase,
    private val calculateIncomeUseCase: CalculateIncomeUseCase
) {
    @GetMapping("/reference-data")
    fun getReferenceData() = loadCalculatorDataUseCase.getReferenceData()

    @PostMapping("/calculate-income")
    fun calculateNetIncome(@Valid @RequestBody command: CalculationCommand) = calculateIncomeUseCase.calculate(command)
}
