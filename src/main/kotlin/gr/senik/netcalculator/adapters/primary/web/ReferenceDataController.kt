package gr.senik.netcalculator.adapters.primary.web

import gr.senik.netcalculator.application.ports.`in`.web.LoadCalculatorDataUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/reference-data")
class ReferenceDataController(
    private val loadCalculatorDataUseCase: LoadCalculatorDataUseCase
) {
    @GetMapping
    fun getReferenceData() = loadCalculatorDataUseCase.getReferenceData()
}
