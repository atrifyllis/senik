package gr.senik.netcalculator.application.ports.`in`.mapper

import gr.senik.netcalculator.application.ports.`in`.web.dto.ReferenceDataDto
import gr.senik.netcalculator.domain.model.insurance.EfkaClass
import gr.senik.netcalculator.domain.model.insurance.EteaepClass
import gr.senik.netcalculator.domain.model.tax.IncomeTaxLevel
import gr.senik.netcalculator.domain.model.tax.SolidarityContributionTaxLevel
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class ReferenceDataMapper {

    @Mapping(target = "efkaClasses", source = "efkaClasses")
    @Mapping(target = "eteaepClasses", source = "eteaepClasses")
    @Mapping(target = "incomeTaxLevels", source = "incomeTaxLevels")
    @Mapping(target = "solidarityContributionTaxLevels", source = "solidarityContributionTaxLevels")
    abstract fun toReferenceDataDto(
        dummy: Dummy,
        efkaClasses: List<EfkaClass>,
        eteaepClasses: List<EteaepClass>,
        incomeTaxLevels: List<IncomeTaxLevel>,
        solidarityContributionTaxLevels: List<SolidarityContributionTaxLevel>
    ): ReferenceDataDto
}

/**
 * Dummy class to force the mapper to map iterables to non-iterable.
 */
class Dummy {

}
