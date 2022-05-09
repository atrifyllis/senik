package gr.senik.netcalculator.domain.service

import gr.senik.common.domain.model.Money
import gr.senik.netcalculator.domain.model.income.Individual
import gr.senik.netcalculator.domain.model.insurance.EfkaClass
import gr.senik.netcalculator.domain.model.insurance.EteaepClass
import gr.senik.netcalculator.domain.model.insurance.InsuranceType

class InsuranceCostCalculator(
    private val individual: Individual,
    private val efkaClasses: List<EfkaClass>,
    private val eteaepClasses: List<EteaepClass>
) {
    fun calculateYearlyInsuranceCost(): Money {
        val efkaClass: EfkaClass = efkaClasses.single { it.id == individual.efkaClassId }
        val eteaepClass: EteaepClass = eteaepClasses.single { it.id == individual.eteaepClassId }
        val eteaepCost: Money = when (individual.type) {
            // TODO check if any of the insurance types have no eteaep contributions
            InsuranceType.TSMEDE, InsuranceType.OAEE,
            InsuranceType.OGA, InsuranceType.TSAY -> eteaepClass.calculateTotalContributionAmount()
        }
        return (efkaClass.calculateTotalContributionAmount() + eteaepCost) * 12
    }
}
