package com.banquemisr.challenge05

import java.io.File
import org.junit.Assert.assertEquals
import org.junit.Test

class TestCoverageValidationTest {
    @Test
    fun `EXPECT all feature modules have test coverage`() {
        val completeCoverageFile = File("../coverage/completeCoverageReport.gradle")
        val featureModules = File("../").listFiles()!!.filter { file ->
            file.isFeatureModule() && file.hasTests()
        }.map { it.name }

        val coverageViolations = getCoverageViolations(completeCoverageFile, featureModules)

        assertEquals(emptyList<String>(), coverageViolations)
    }

    @Test
    fun `EXPECT library modules with tests have coverage`() {
        val completeCoverageFile = File("../coverage/completeCoverageReport.gradle")
        val libraryModules = File("../").listFiles()!!.filter { file ->
            file.isLibraryModule() && file.hasTests()
        }.map { it.name }

        val coverageViolations = getCoverageViolations(completeCoverageFile, libraryModules)

        assertEquals(emptyList<String>(), coverageViolations)
    }

    private fun File.isFeatureModule(): Boolean {
        return name.endsWith("-component") || name.endsWith("-ui")
    }

    private fun File.isLibraryModule(): Boolean {
        return !isFeatureModule() && !isAppModule()
    }

    private fun File.isAppModule(): Boolean {
        return name == "app"
    }

    private fun File.hasTests(): Boolean {
        val testFolder = File("../$name/src/test")
        return testFolder.exists() && testFolder.hasTestFiles()
    }

    private fun File.hasTestFiles(): Boolean {
        return listFiles()!!.firstOrNull { file ->
            if (file.isDirectory) {
                file.hasTestFiles()
            } else {
                file.name.endsWith("Test.kt")
            }
        } != null
    }

    private fun getCoverageViolations(completeCoverageFile: File, modules: List<String>): List<String> {
        val coverageViolations = mutableListOf<String>()
        modules.forEach { module ->
            var moduleHasCoverage = false
            completeCoverageFile.forEachLine { line ->
                if (lineContainsCoverage(line, module)) {
                    moduleHasCoverage = true
                }
            }
            if (!moduleHasCoverage) {
                coverageViolations.add(module)
            }
        }
        return coverageViolations
    }

    private fun lineContainsCoverage(line: String, module: String): Boolean {
        return line.contains("    kover(project(':$module'))") ||
            line.contains("    kover(project(\":$module\"))")
    }
}
