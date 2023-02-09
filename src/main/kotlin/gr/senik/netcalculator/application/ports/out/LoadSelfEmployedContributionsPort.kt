package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.SelfEmployedContribution
import org.springframework.cache.annotation.Cacheable

sealed interface LoadSelfEmployedContributionsPort {
    @Cacheable("selfEmployedContributions")
    fun loadSelfEmployedContributions(): List<SelfEmployedContribution>
}
