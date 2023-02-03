package gr.senik.netcalculator.application.ports.out

import gr.senik.netcalculator.domain.model.v2.SelfEmployedContribution
import org.springframework.cache.annotation.Cacheable

sealed interface LoadSelfEmployedContributionsPort {
    @Cacheable("selfEmployedContributions")
    fun loadSelfEmployedContributions(): List<SelfEmployedContribution>
}
