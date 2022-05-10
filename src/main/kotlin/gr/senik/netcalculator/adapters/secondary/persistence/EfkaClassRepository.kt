package gr.senik.netcalculator.adapters.secondary.persistence

import gr.senik.netcalculator.domain.model.insurance.EfkaClass
import gr.senik.netcalculator.domain.model.insurance.EfkaClassId
import org.springframework.data.jpa.repository.JpaRepository

interface EfkaClassRepository : JpaRepository<EfkaClass, EfkaClassId>
