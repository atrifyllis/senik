package gr.senik.netcalculator.application.ports.`in`

import gr.senik.netcalculator.application.ports.`in`.dto.ReferenceDataDto

interface LoadCalculatorDataUseCase {
    fun getReferenceData(): ReferenceDataDto
}
