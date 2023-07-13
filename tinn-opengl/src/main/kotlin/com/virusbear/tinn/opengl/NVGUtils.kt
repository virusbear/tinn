package com.virusbear.tinn.opengl

import com.virusbear.tinn.draw.HorizontalTextAlign
import com.virusbear.tinn.draw.LineCap
import com.virusbear.tinn.draw.LineJoin
import com.virusbear.tinn.draw.VerticalTextAlign
import org.lwjgl.nanovg.NanoVG.*

internal fun nvgTextAlignInt(verticalTextAlign: VerticalTextAlign, horizontalTextAlign: HorizontalTextAlign): Int =
    when(verticalTextAlign) {
        VerticalTextAlign.Top -> NVG_ALIGN_TOP
        VerticalTextAlign.Middle -> NVG_ALIGN_MIDDLE
        VerticalTextAlign.Bottom -> NVG_ALIGN_BOTTOM
        VerticalTextAlign.Baseline -> NVG_ALIGN_BASELINE
    } or when(horizontalTextAlign) {
        HorizontalTextAlign.Left -> NVG_ALIGN_LEFT
        HorizontalTextAlign.Center -> NVG_ALIGN_CENTER
        HorizontalTextAlign.Right -> NVG_ALIGN_RIGHT
    }

internal val LineJoin.nvg: Int
    get() = when(this) {
        LineJoin.None -> 0
        LineJoin.Miter -> NVG_MITER
        LineJoin.Round -> NVG_ROUND
        LineJoin.Bevel -> NVG_BEVEL
    }

internal val LineCap.nvg: Int
    get() = when(this) {
        LineCap.Butt -> NVG_BUTT
        LineCap.Round -> NVG_ROUND
        LineCap.Square -> NVG_SQUARE
        LineCap.Bevel -> NVG_BEVEL
        LineCap.Miter -> NVG_MITER
    }