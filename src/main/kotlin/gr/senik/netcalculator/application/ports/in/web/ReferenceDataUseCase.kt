package gr.senik.netcalculator.application.ports.`in`.web

import gr.senik.netcalculator.application.ports.`in`.web.dto.ReferenceDataDto

interface ReferenceDataUseCase {
    fun getReferenceData(): ReferenceDataDto
}
