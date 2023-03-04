package gr.senik

import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.CompositeArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import com.tngtech.archunit.lang.syntax.elements.ClassesShouldConjunction
import com.tngtech.archunit.library.Architectures.OnionArchitecture
import com.tngtech.archunit.library.Architectures.onionArchitecture
import com.tngtech.archunit.library.GeneralCodingRules
import com.tngtech.archunit.library.dependencies.SliceRule
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices

private const val DOMAIN_MODEL_LAYER = "domain.model"
private const val DOMAIN_SERVICE_LAYER = "domain.service"
private const val APPLICATION_SERVICE_LAYER = "application.service"
private const val APPLICATION_PORTS_IN_LAYER = "application.ports.in"
private const val APPLICATION_PORTS_OUT_LAYER = "application.ports.out"
private const val PRIMARY_ADAPTERS_LAYER = "adapters.primary"
private const val SECONDARY_ADAPTERS_LAYER = "adapters.secondary"


private const val WEB = "web"
private const val MESSAGING = "messaging"

private const val PERSISTENCE = "persistence"
private const val LOGGING = "logging"


@AnalyzeClasses(packages = ["gr.senik"], importOptions = [ImportOption.DoNotIncludeTests::class])
class ArchUnitTests {

    private val basePackageName: String = this.javaClass.packageName

    @ArchTest
    val noPackageCycles: SliceRule = slices().matching("$basePackageName.(*)..").should().beFreeOfCycles()

    @ArchTest
    val defaultOnionArchitectureRules: OnionArchitecture =
        onionArchitecture()
            .domainModels(
                "..$DOMAIN_MODEL_LAYER..",
                basePackageName
            ) // workaround to allow DomainEventMixIn to "be part" of the domain
            .domainServices("..$DOMAIN_SERVICE_LAYER..")
            .applicationServices(
                "..$APPLICATION_SERVICE_LAYER..",
                // TODO: ports are not application services per-se,
                //  but since they are in the application layer we add them here
                "..$APPLICATION_PORTS_IN_LAYER..",
                "..$APPLICATION_PORTS_OUT_LAYER.."
            )
            .adapter(WEB, "..$PRIMARY_ADAPTERS_LAYER.$WEB..")
            .adapter(MESSAGING, "..$PRIMARY_ADAPTERS_LAYER.$MESSAGING..")
            .adapter(
                PERSISTENCE,
                "..$SECONDARY_ADAPTERS_LAYER.$PERSISTENCE..",
                "..$SECONDARY_ADAPTERS_LAYER.$LOGGING.."
            )
            .withOptionalLayers(true) // allow empty layers

    @ArchTest
    val primaryAdaptersShouldNotAccessApplicationServices: ClassesShouldConjunction =
        ArchRuleDefinition.noClasses()
            .that()
            .resideInAPackage("..$PRIMARY_ADAPTERS_LAYER..")
            .should()
            .accessClassesThat()
            .resideInAPackage("$APPLICATION_SERVICE_LAYER..")

    @ArchTest
    val secondaryAdaptersShouldNotAccessApplicationServices: ClassesShouldConjunction =
        ArchRuleDefinition.noClasses()
            .that()
            .resideInAPackage("..$SECONDARY_ADAPTERS_LAYER..")
            .should()
            .accessClassesThat()
            .resideInAPackage("$APPLICATION_SERVICE_LAYER..")

//    TODO jmolecules rules fail because we have direct reference to aggregates from otehr classes (e.g. ValueObjects)
//    @ArchTest
//    val dddJmoleculesRules: ArchRule = JMoleculesRules.all().allowEmptyShould(true)

    @ArchTest
    val noJpaRepositoriesInDomainLayer: ArchRule = ArchRuleDefinition.noClasses()
        .that()
        .resideInAnyPackage("..$DOMAIN_MODEL_LAYER..")
        .and()
        .resideOutsideOfPackage("..$DOMAIN_MODEL_LAYER.old..")
        .should().dependOnClassesThat()
        .haveNameMatching("org.springframework.data.jpa.repository.JpaRepository")
        .because("JpaRepositories should not be part of the domain layer")

// rules like the one bellow are already checked in defaultOnionArchitectureRules
//    @ArchTest
//    val domainModelClassesShouldNotAccessClassesOutsideOfDomain: ClassesShouldConjunction =
//        ArchRuleDefinition.noClasses()
//            .that()
//            .resideInAPackage("..$DOMAIN_MODEL_LAYER..")
//            .should().accessClassesThat()
//            .resideOutsideOfPackages("..$DOMAIN_MODEL_LAYER..", "..java..", "..kotlin..", "..mu..")


    @ArchTest
    val genericCodingRules: CompositeArchRule = CompositeArchRule
        .of(GeneralCodingRules.NO_CLASSES_SHOULD_ACCESS_STANDARD_STREAMS)
        .and(GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS)
        .and(GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION)
        .and(GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING)
        .and(GeneralCodingRules.NO_CLASSES_SHOULD_USE_JODATIME)
}
