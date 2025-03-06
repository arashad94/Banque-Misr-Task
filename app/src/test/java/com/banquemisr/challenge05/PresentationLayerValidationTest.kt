package com.banquemisr.challenge05

import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class PresentationLayerValidationTest {
    @Test
    fun `EXPECT no violations in ViewModels`() {
        getClasses("ViewModel")
            .assertTrue { it.resideInPackage("..presentation.viewmodel..") }
    }
}
