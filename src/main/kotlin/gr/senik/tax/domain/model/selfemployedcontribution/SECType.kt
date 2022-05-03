package gr.senik.tax.domain.model.selfemployedcontribution

enum class SECType {
    SINGLE_EMPLOYER_SMALL_AREA,
    SINGLE_EMPLOYER_LARGE_AREA,
    SINGLE_EMPLOYER_WITH_BRANCHES,

    MULTIPLE_EMPLOYERS,
    MULTIPLE_EMPLOYERS_WITH_BRANCHES,
}