package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SelfEmployedContribution

sealed interface LoadSelfEmployedContributionsPort {
    fun loadSelfEmployedContributions(): List<SelfEmployedContribution>
}
