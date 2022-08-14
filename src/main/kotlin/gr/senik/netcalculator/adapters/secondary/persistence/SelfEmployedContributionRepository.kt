package gr.senik.netcalculator.adapters.secondary.persistence

import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SelfEmployedContribution
import gr.senik.netcalculator.domain.model.tax.selfemployedcontribution.SelfEmployedContributionId
import org.springframework.data.jpa.repository.JpaRepository

interface SelfEmployedContributionRepository : JpaRepository<SelfEmployedContribution, SelfEmployedContributionId>
