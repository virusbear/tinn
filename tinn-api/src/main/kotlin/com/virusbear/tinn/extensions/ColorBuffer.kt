package com.virusbear.tinn.extensions

import com.virusbear.tinn.*
import java.awt.Color
import java.io.File

fun colorBuffer(
    width: Int,
    height: Int,
    contentScale: Double = 1.0,
    multisample: MultiSample = MultiSample.None,
    format: ColorFormat = ColorFormat.RGBA8,
    levels: MipMapLevel = MipMapLevel.None
): ColorBuffer =
    Driver.driver.createColorBuffer(width, height, contentScale, format, multisample, levels)

fun loadImage(file: File, format: ColorFormat = ColorFormat.RGBA8): ColorBuffer =
    Driver.driver.loadImage(file, format)