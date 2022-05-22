package gr.senik.netcalculator.application.ports.`in`.web

import gr.senik.netcalculator.application.ports.`in`.web.dto.CalculationCommand
import gr.senik.netcalculator.application.ports.`in`.web.dto.CalculationResultDto

interface CalculateIncomeUseCase {
    fun  calculate(command: CalculationCommand): CalculationResultDto
}
