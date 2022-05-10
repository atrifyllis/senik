package gr.senik.netcalculator.adapters.primary.web

import gr.senik.netcalculator.application.ports.`in`.web.ReferenceDataUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reference-data")
class ReferenceDataController(
    private val referenceDataUseCase: ReferenceDataUseCase
) {
    @GetMapping
    fun getReferenceData() = referenceDataUseCase.getReferenceData()
}
