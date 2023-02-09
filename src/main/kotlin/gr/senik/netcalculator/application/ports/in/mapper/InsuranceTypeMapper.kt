package gr.senik.netcalculator.application.ports.`in`.mapper

import gr.senik.netcalculator.application.ports.`in`.web.dto.InsuranceTypeDto
import gr.senik.netcalculator.domain.model.InsuranceType
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy

/**
 * There is no obvious way to map enum to POJO in mapstruct
 *
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class InsuranceTypeMapper {

    fun toInsuranceTypeDto(insuranceType: InsuranceType): InsuranceTypeDto {
        return InsuranceTypeDto(insuranceType.name, insuranceType.name)
    }
}
