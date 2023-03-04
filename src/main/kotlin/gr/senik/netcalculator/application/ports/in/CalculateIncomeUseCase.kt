package gr.senik.netcalculator.application.ports.`in`

import gr.senik.netcalculator.application.ports.`in`.dto.CalculationCommand
import gr.senik.netcalculator.application.ports.`in`.dto.CalculationResultDto

interface CalculateIncomeUseCase {
    fun calculate(command: CalculationCommand): CalculationResultDto
}
