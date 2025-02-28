#!/bin/sh
ktlint "**/*.kt" "!**/generated/**" "!buildSrc/**" -F --color --color-name=RED
