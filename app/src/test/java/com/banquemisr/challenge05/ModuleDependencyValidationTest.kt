package com.banquemisr.challenge05

import java.io.File
import org.junit.Assert.assertEquals
import org.junit.Test

class ModuleDependencyValidationTest {
    @Test
    fun `EXPECT component modules do not depend on UI modules`() {
        val gradleFiles = getGradleFilesForAllModules("-component")

        val filesViolations = getComponentViolationsForGradleFiles(gradleFiles)

        assertEquals(emptyMap<String, List<String>>(), filesViolations)
    }

    @Test
    fun `EXPECT root UI modules don't depend on each other`() {
        val gradleFiles = getGradleFilesForAllModules("-ui")

        val filesViolations = getRootUIViolationsForGradleFiles(gradleFiles)

        assertEquals(emptyMap<String, List<String>>(), filesViolations)
    }

    private fun getGradleFilesForAllModules(moduleSuffix: String): List<File> {
        val rootDirectory = File("../")
        val componentModules = rootDirectory.listFiles()!!.filter { file ->
            file.name.endsWith(moduleSuffix)
        }
        val gradleFiles = componentModules.mapNotNull { file ->
            val gradleFile = File("../${file.name}/build.gradle.kts")
            if (gradleFile.exists()) {
                gradleFile
            } else {
                null
            }
        }
        return gradleFiles
    }

    private fun getComponentViolationsForGradleFiles(gradleFiles: List<File>): MutableMap<String, List<String>> {
        val filesViolations = mutableMapOf<String, List<String>>()
        gradleFiles.forEach { gradleFile ->
            val violations = mutableListOf<String>()
            gradleFile.forEachLine { line ->
                violations.addViolationIfPresent(line) { contains("implementation(project(") && contains("-ui") }
                violations.addViolationIfPresent(line) { contains("implementation(project(") && contains("designsystem") }
                violations.addViolationIfPresent(line) { contains("viewBinding true") }
            }
            if (violations.isNotEmpty()) {
                filesViolations[gradleFile.path] = violations
            }
        }
        return filesViolations
    }

    private fun getRootUIViolationsForGradleFiles(gradleFiles: List<File>): MutableMap<String, List<String>> {
        val filesViolations = mutableMapOf<String, List<String>>()
        gradleFiles.forEach { gradleFile ->
                val violations = mutableListOf<String>()

                gradleFile.forEachLine { line ->
                    violations.addViolationIfPresent(line) {
                        contains("implementation(project(") && INDEPENDENT_UI_MODULES.any { uiModule -> contains(uiModule) }
                    }
                }
                if (violations.isNotEmpty()) {
                    filesViolations[gradleFile.path] = violations
                }
            }
        return filesViolations
    }

    private fun MutableList<String>.addViolationIfPresent(line: String, containsChecks: String.() -> Boolean) {
        val containsViolation = containsChecks(line)
        if (containsViolation) {
            add(line)
        }
    }

    private companion object {
        val INDEPENDENT_UI_MODULES = listOf("home-ui", "pdp-ui")
    }
}
