package com.banquemisr.challenge05

import com.lemonappdev.konsist.api.verify.assertTrue
import org.junit.Test

class DomainLayerValidationTest {
    @Test
    fun `EXPECT no violations in UseCases abstractions`() {
        getFilesOfInterfaces("UseCases")
            .toMutableList().apply {
                assertTrue { it.resideInPackage("..domain.usecase..") }
                assertTrue { it.hasFunModifier }
            }
    }

    @Test
    fun `EXPECT no violations in UseCases implementations`() {
        getClasses("UseCases")
            .toMutableList().assertTrue { it.resideInPackage("..domain.usecase..") }
    }

    @Test
    fun `EXPECT no violations in Repositories abstractions`() {
        getInterfaces("Repository")
            .assertTrue { it.resideInPackage("..domain.repository..") }
    }

    @Test
    fun `EXPECT domain layer does not import frameworks`() {
        getFilesInPackages("domain")
            .assertTrue {
                it.imports.firstOrNull { import ->
                    !(
                        import.name.contains("banquemisr") ||
                            import.name.contains("java") ||
                            import.name.contains("kotlin") ||
                            import.name.contains("androidx.annotation.") ||
                            import.name.contains("android.os.Parcelable") ||
                            import.name.contains("org.junit") ||
                            import.name.contains("org.mockito")
                        )
                } == null
            }
    }
}
