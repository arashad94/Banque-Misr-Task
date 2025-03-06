package com.banquemisr.challenge05

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.architecture.KoArchitectureCreator.assertArchitecture
import com.lemonappdev.konsist.api.architecture.Layer
import org.junit.Test

class DependencyRuleValidationTest {

    @Test
    fun `EXPECT no imports in other layers`() {
        Konsist
            .scopeFromProduction()
            .assertArchitecture {
                val domainLayer = Layer("Domain", "..domain..")
                val dataLayer = Layer("Data", "..data..")
                val presentationLayer = Layer("Presentation", "..presentation..")

                domainLayer.dependsOnNothing()
                dataLayer.dependsOn(domainLayer)
                presentationLayer.dependsOn(domainLayer)
            }
    }

    @Test
    fun `EXPECT presentation layer only depends on domain primary port`() {
        Konsist
            .scopeFromProduction()
            .assertArchitecture {
                val domainPrimaryPort = Layer("Domain UseCase", "..domain.usecase..")
                val domainSecondaryPort = Layer("Domain Repository", "..domain.repository..")
                val presentationLayer = Layer("Presentation", "..presentation..")

                presentationLayer.dependsOn(domainPrimaryPort)
                presentationLayer.doesNotDependOn(domainSecondaryPort)
            }
    }

    @Test
    fun `EXPECT data layer only depends on domain secondary port`() {
        Konsist
            .scopeFromProduction()
            .assertArchitecture {
                val domainPrimaryPort = Layer("Domain UseCase", "..domain.usecase..")
                val domainSecondaryPort = Layer("Domain Repository", "..domain.repository..")
                val dataLayer = Layer("Data", "..data..")

                dataLayer.doesNotDependOn(domainPrimaryPort)
                dataLayer.dependsOn(domainSecondaryPort)
            }
    }
}
