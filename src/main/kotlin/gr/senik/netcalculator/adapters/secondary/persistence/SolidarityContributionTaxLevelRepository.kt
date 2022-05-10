package gr.senik.netcalculator.adapters.secondary.persistence

import gr.senik.netcalculator.domain.model.tax.SolidarityContributionTaxLevel
import gr.senik.netcalculator.domain.model.tax.TaxLevelId
import org.springframework.data.jpa.repository.JpaRepository

interface SolidarityContributionTaxLevelRepository : JpaRepository<SolidarityContributionTaxLevel, TaxLevelId>
