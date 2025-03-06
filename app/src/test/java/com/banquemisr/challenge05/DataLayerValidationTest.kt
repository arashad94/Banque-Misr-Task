package com.banquemisr.challenge05

import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class DataLayerValidationTest {

    @Test
    fun `EXPECT no violations in Repositories implementations`() {
        getClasses("Repository")
            .assertTrue { it.resideInPackage("..data.repository..") }
        getPackages("data.repository")
            .assertTrue { it.containingFile.name.contains("Repository") }
    }
}
