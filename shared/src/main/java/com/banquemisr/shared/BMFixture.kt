package com.banquemisr.shared

import com.appmattus.kotlinfixture.kotlinFixture

class BMFixture(private val fixtureConfiguration: BMConfiguration) {

    val fixture = kotlinFixture {
        repeatCount { fixtureConfiguration.listSize }
    }

    inline operator fun <reified T : Any?> invoke(): T {
        return fixture()
    }
}

class BMConfigurationBuilder(configuration: BMConfiguration = BMConfiguration()) {
    var listSize: Int = configuration.listSize

    fun build() = BMConfiguration(
        listSize = listSize
    )
}

class BMConfiguration(val listSize: Int = 1)

inline fun bmFixture(configuration: BMConfigurationBuilder.() -> Unit = {}) =
    BMFixture(BMConfigurationBuilder().apply(configuration).build())

inline fun <reified T : Any?> fixtureOf(
    configuration: BMConfigurationBuilder.() -> Unit = {}
): T = bmFixture(configuration).invoke()
