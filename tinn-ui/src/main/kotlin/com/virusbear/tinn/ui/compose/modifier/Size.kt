package com.virusbear.tinn.ui.compose.modifier

import androidx.compose.runtime.Stable
import com.virusbear.tinn.ui.compose.*
import javax.management.modelmbean.ModelMBeanConstructorInfo

@Stable
fun Modifier.width(width: Dp) =
    this then SizeModifier(
        minWidth = width,
        maxWidth = width,
        enforceIncoming = true
    )

@Stable
fun Modifier.height(height: Dp) =
    this then SizeModifier(
        minHeight = height,
        maxHeight = height,
        enforceIncoming = true
    )

@Stable
fun Modifier.size(size: Dp) =
    this then SizeModifier(
        minWidth = size,
        maxWidth = size,
        minHeight = size,
        maxHeight = size,
        enforceIncoming = true
    )

@Stable
fun Modifier.size(
    width: Dp = Dp.Unspecified,
    height: Dp = Dp.Unspecified
) =
    this then SizeModifier(
        minWidth = width,
        maxWidth = width,
        minHeight = height,
        maxHeight = height,
        enforceIncoming = true
    )

@Stable
fun Modifier.widthIn(
    min: Dp = Dp.Unspecified,
    max: Dp = Dp.Unspecified
) =
    this then SizeModifier(
        minWidth = min,
        maxWidth = max,
        enforceIncoming = true
    )

@Stable
fun Modifier.heightIn(
    min: Dp = Dp.Unspecified,
    max: Dp = Dp.Unspecified
) =
    this then SizeModifier(
        minHeight = min,
        maxHeight = max,
        enforceIncoming = true
    )

@Stable
fun Modifier.sizeIn(
    minWidth: Dp = Dp.Unspecified,
    maxWidth: Dp = Dp.Unspecified,
    minHeight: Dp = Dp.Unspecified,
    maxHeight: Dp = Dp.Unspecified
) =
    this then SizeModifier(
        minWidth = minWidth,
        maxWidth = maxWidth,
        minHeight = minHeight,
        maxHeight = maxHeight,
        enforceIncoming = true
    )

@Stable
fun Modifier.requiredWidth(width: Dp) =
    this then SizeModifier(
        minWidth = width,
        maxWidth = width,
        enforceIncoming = false
    )

@Stable
fun Modifier.requiredHeight(height: Dp) =
    this then SizeModifier(
        minHeight = height,
        maxHeight = height,
        enforceIncoming = false
    )

@Stable
fun Modifier.requiredSize(size: Dp) =
    this then SizeModifier(
        minWidth = size,
        maxWidth = size,
        minHeight = size,
        maxHeight = size,
        enforceIncoming = false
    )

@Stable
fun Modifier.requiredSize(
    width: Dp = Dp.Unspecified,
    height: Dp = Dp.Unspecified
) =
    this then SizeModifier(
        minWidth = width,
        maxWidth = width,
        minHeight = height,
        maxHeight = height,
        enforceIncoming = false
    )

@Stable
fun Modifier.requiredWidthIn(
    min: Dp = Dp.Unspecified,
    max: Dp = Dp.Unspecified
) =
    this then SizeModifier(
        minWidth = min,
        maxWidth = max,
        enforceIncoming = false
    )

@Stable
fun Modifier.requiredHeightIn(
    min: Dp = Dp.Unspecified,
    max: Dp = Dp.Unspecified
) =
    this then SizeModifier(
        minHeight = min,
        maxHeight = max,
        enforceIncoming = false
    )

@Stable
fun Modifier.requiredSizeIn(
    minWidth: Dp = Dp.Unspecified,
    maxWidth: Dp = Dp.Unspecified,
    minHeight: Dp = Dp.Unspecified,
    maxHeight: Dp = Dp.Unspecified
) =
    this then SizeModifier(
        minWidth = minWidth,
        maxWidth = maxWidth,
        minHeight = minHeight,
        maxHeight = maxHeight,
        enforceIncoming = false
    )

private class SizeModifier(
    private val minWidth: Dp = Dp.Unspecified,
    private val minHeight: Dp = Dp.Unspecified,
    private val maxWidth: Dp = Dp.Unspecified,
    private val maxHeight: Dp = Dp.Unspecified,
    private val enforceIncoming: Boolean
): LayoutModifier {
    private val Density.targetConstraints: Constraints
        get() {
            val maxWidth = if(maxWidth.isSpecified) {
                maxWidth.roundToPx().coerceAtLeast(0)
            } else {
                Constraints.Infinity
            }
            val maxHeight = if(maxHeight.isSpecified) {
                maxHeight.roundToPx().coerceAtLeast(0)
            } else {
                Constraints.Infinity
            }
            val minWidth = if(minWidth.isSpecified) {
                minWidth.roundToPx().coerceIn(0, maxWidth).let {
                    if(it != Constraints.Infinity) it else 0
                }
            } else {
                0
            }
            val minHeight = if(minHeight.isSpecified) {
                minHeight.roundToPx().coerceIn(0, maxHeight).let {
                    if(it != Constraints.Infinity) it else 0
                }
            } else {
                0
            }

            return Constraints(
                minWidth = minWidth,
                minHeight = minHeight,
                maxWidth = maxWidth,
                maxHeight = maxHeight
            )
        }

    override fun MeasureScope.measure(constraints: Constraints, measurable: Measurable): MeasureResult {
        val wrappedConstraints = targetConstraints.let { targetConstraints ->
            if(enforceIncoming) {
                constraints.constrain(targetConstraints)
            } else {
                val resolvedMinWidth = if(minWidth.isSpecified) {
                    targetConstraints.minWidth
                } else {
                    constraints.minWidth.coerceAtMost(targetConstraints.maxWidth)
                }
                val resolvedMaxWidth = if(maxWidth.isSpecified) {
                    targetConstraints.maxWidth
                } else {
                    constraints.maxWidth.coerceAtLeast(targetConstraints.minWidth)
                }
                val resolvedMinHeight = if(minHeight.isSpecified) {
                    targetConstraints.minHeight
                } else {
                    constraints.minWidth.coerceAtMost(targetConstraints.maxHeight)
                }
                val resolvedMaxHeight = if(maxHeight.isSpecified) {
                    targetConstraints.maxHeight
                } else {
                    constraints.maxHeight.coerceAtLeast(targetConstraints.minHeight)
                }

                Constraints(
                    minWidth = resolvedMinWidth,
                    minHeight = resolvedMinHeight,
                    maxWidth = resolvedMaxWidth,
                    maxHeight = resolvedMaxHeight
                )
            }
        }

        val placeable = measurable.measure(wrappedConstraints)
        return layout(placeable.width, placeable.height) {
            placeable.place(0, 0)
        }
    }
}