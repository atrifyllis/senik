package gr.senik.common.domain.service

import gr.senik.common.domain.model.Money
import gr.senik.insurance.domain.model.EfkaClass
import gr.senik.insurance.domain.model.EteaepClass
import gr.senik.insurance.domain.model.InsuredPerson

class NetIncomeCalculator {

    fun calculate(insuredPerson: InsuredPerson, efkaClass: EfkaClass, eteaepClass: EteaepClass): Money {
        return Money(0)
    }
}
