package gr.senik.netcalculator.domain.model.v2

class Company(
    id: LegalEntityId, type: LegalEntityType = LegalEntityType.COMPANY,
    val companyType: CompanyType,
    efkaClass: EfkaClass,
    eteaepClass: EteaepClass,
    selfEmployedContribution: SelfEmployedContribution,
    incomeTax: IncomeTax,
    solidarityTax: SolidarityTax,
) : LegalEntity(id, type, efkaClass, eteaepClass, selfEmployedContribution, incomeTax, solidarityTax) {
    override fun calculateIncome(): Income {
        TODO("Not yet implemented")
    }
}

enum class CompanyType {
    OE, // omorythymh
    EE, // eterorythmh
    IKE, // kefalaiouxikh
    AE // anwnymh
}
