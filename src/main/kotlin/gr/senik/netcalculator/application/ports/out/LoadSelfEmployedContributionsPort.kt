package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.SelfEmployedContribution

sealed interface LoadSelfEmployedContributionsPort {
    fun loadSelfEmployedContributions(): List<SelfEmployedContribution>
}
