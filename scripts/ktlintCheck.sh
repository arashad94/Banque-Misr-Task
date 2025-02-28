#!/bin/sh
ktlint "**/*.kt" "!**/generated/**" "!buildSrc/**" --color --color-name=RED
