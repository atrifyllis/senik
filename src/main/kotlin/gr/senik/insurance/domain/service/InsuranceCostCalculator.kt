package gr.senik.insurance.domain.service

import gr.senik.common.domain.model.Money
import gr.senik.insurance.domain.model.EfkaClass
import gr.senik.insurance.domain.model.EteaepClass
import gr.senik.insurance.domain.model.InsuranceType
import gr.senik.insurance.domain.model.InsuredPerson

class InsuranceCostCalculator(
    private val insuredPerson: InsuredPerson,
    private val efkaClasses: List<EfkaClass>,
    private val eteaepClasses: List<EteaepClass>
) {
    fun calculateYearlyInsuranceCost(): Money {
        val efkaClass: EfkaClass = efkaClasses.single { it.id == insuredPerson.efkaClassId }
        val eteaepClass: EteaepClass = eteaepClasses.single { it.id == insuredPerson.eteaepClassId }
        val eteaepCost: Money = when (insuredPerson.type) {
            // TODO check if any of the insurance types have no eteaep contributions
            InsuranceType.TSMEDE, InsuranceType.OAEE,
            InsuranceType.OGA, InsuranceType.TSAY -> eteaepClass.calculateTotalContributionAmount()
        }
        return (efkaClass.calculateTotalContributionAmount() + eteaepCost) * 12
    }
}
