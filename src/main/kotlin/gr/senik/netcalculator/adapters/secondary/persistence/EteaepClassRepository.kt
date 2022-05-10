package gr.senik.netcalculator.adapters.secondary.persistence

import gr.senik.netcalculator.domain.model.insurance.EteaepClass
import gr.senik.netcalculator.domain.model.insurance.EteaepClassId
import org.springframework.data.jpa.repository.JpaRepository

interface EteaepClassRepository : JpaRepository<EteaepClass, EteaepClassId>
