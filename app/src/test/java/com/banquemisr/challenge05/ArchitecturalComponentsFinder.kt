package com.banquemisr.challenge05

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.ext.list.*

internal fun getInterfaces(interfaceSuffix: String) = Konsist.scopeFromProject()
    .interfaces()
    .withNameEndingWith(interfaceSuffix)

internal fun getClasses(classSuffix: String) = Konsist.scopeFromProject()
    .classes()
    .withNameEndingWith(classSuffix)

internal fun getPackages(containedText: String) = Konsist.scopeFromProject()
    .packages
    .withNameContaining(containedText)

internal fun getFilesOfInterfaces(fileSuffix: String) = Konsist.scopeFromProject()
    .files
    .withNameEndingWith(fileSuffix)
    .interfaces()

internal fun getFilesInPackages(containedText: String) = Konsist.scopeFromProject()
    .files.filter { file ->
        file.path.contains(containedText)
    }
