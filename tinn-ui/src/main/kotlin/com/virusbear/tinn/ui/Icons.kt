package com.virusbear.tinn.ui

import com.virusbear.tinn.draw.drawable
import com.virusbear.tinn.math.Vec2

object Icons {
    val Cross by lazy {
        drawable {
            noStroke()
            fill = Theme.IconForegroundColor()

            path {
                moveTo(Vec2(301.258, 256.01))
                lineTo(Vec2(502.645, 54.645))
                bezierTo(Vec2(515.146, 42.144), Vec2(515.146, 21.876), Vec2(502.645, 9.376))
                bezierTo(Vec2(490.144, -3.125), Vec2(469.876, -3.125), Vec2(457.376, 9.376))
                lineTo(Vec2(457.376, 9.376))
                lineTo(Vec2(256.01, 210.762))
                lineTo(Vec2(54.645, 9.376))
                bezierTo(Vec2(42.144, -3.125), Vec2(21.876, -3.125), Vec2(9.376, 9.376))
                bezierTo(Vec2(-3.124, 21.877), Vec2(-3.125, 42.145), Vec2(9.376, 54.645))
                lineTo(Vec2(210.762, 256.01))
                lineTo(Vec2(9.376, 457.376))
                bezierTo(Vec2(-3.125, 469.877), Vec2(-3.125, 490.145), Vec2(9.376, 502.645))
                bezierTo(Vec2(21.877, 515.145), Vec2(42.145, 515.146), Vec2(54.645, 502.645))
                lineTo(Vec2(256.01, 301.258))
                lineTo(Vec2(457.375, 502.645))
                bezierTo(Vec2(469.876, 515.146), Vec2(490.144, 515.146), Vec2(502.644, 502.645))
                bezierTo(Vec2(515.145, 490.144), Vec2(515.145, 469.876), Vec2(502.644, 457.376))
                lineTo(Vec2(301.258, 256.01))
            }
        }
    }

    val Check by lazy {
        drawable {
            noStroke()
            fill = Theme.IconForegroundColor()

            path {
                moveTo(Vec2(165.4614, 441.1908))
                bezierTo(Vec2(150.9151, 441.1969), Vec2(136.9645, 435.4151), Vec2(126.6873, 425.1208))
                lineTo(Vec2(9.4603, 307.9371))
                bezierTo(Vec2(-3.1534, 295.3194), Vec2(-3.1534, 274.866), Vec2(9.4603, 262.2483))
                lineTo(Vec2(9.4603, 262.2483))
                bezierTo(Vec2(22.078, 249.6346), Vec2(42.5314, 249.6346), Vec2(55.1491, 262.2483))
                lineTo(Vec2(165.4614, 372.5606))
                lineTo(Vec2(457.3012, 80.7208))
                bezierTo(Vec2(469.919, 68.1071), Vec2(490.3723, 68.1071), Vec2(502.9901, 80.7208))
                lineTo(Vec2(502.9901, 80.7208))
                bezierTo(Vec2(515.6038, 93.3386), Vec2(515.6038, 113.7919), Vec2(502.9901, 126.4097))
                lineTo(Vec2(204.2356, 425.1208))
                bezierTo(Vec2(193.9584, 435.4151), Vec2(180.0078, 441.1969), Vec2(165.4614, 441.1908))
                lineTo(Vec2(165.4614, 441.1908))
            }
        }
    }

    val Hamburger by lazy {
        drawable {
            noStroke()
            fill = Theme.IconForegroundColor()

            path {
                moveTo(Vec2(480.0, 224.0))
                lineTo(Vec2(32.0, 224.0))
                bezierTo(Vec2(14.327, 224.0), Vec2(0.0, 238.327), Vec2(0.0, 256.0))
                bezierTo(Vec2(0.0, 273.673), Vec2(14.327, 288.0), Vec2(32.0, 288.0))
                lineTo(Vec2(480.0, 288.0))
                bezierTo(Vec2(497.673, 288.0), Vec2(512.0, 273.673), Vec2(512.0, 256.0))
                bezierTo(Vec2(512.0, 238.327), Vec2(497.673, 224.0), Vec2(480.0, 224.0))
                lineTo(Vec2(480.0, 224.0))
                moveTo(Vec2(32.0, 138.667))
                lineTo(Vec2(480.0, 138.667))
                bezierTo(Vec2(497.673, 138.667), Vec2(512.0, 124.34), Vec2(512.0, 106.667))
                bezierTo(Vec2(512.0, 88.994), Vec2(497.673, 74.667), Vec2(480.0, 74.667))
                lineTo(Vec2(32.0, 74.667))
                bezierTo(Vec2(14.327, 74.667), Vec2(0.0, 88.994), Vec2(0.0, 106.667))
                bezierTo(Vec2(0.0, 124.34), Vec2(14.327, 138.667), Vec2(32.0, 138.667))
                lineTo(Vec2(32.0, 138.667))
                moveTo(Vec2(480.0, 373.333))
                lineTo(Vec2(32.0, 373.333))
                bezierTo(Vec2(14.327, 373.333), Vec2(0.0, 387.66), Vec2(0.0, 405.333))
                bezierTo(Vec2(0.0, 423.006), Vec2(14.327, 437.333), Vec2(32.0, 437.333))
                lineTo(Vec2(480.0, 437.333))
                bezierTo(Vec2(497.673, 437.333), Vec2(512.0, 423.006), Vec2(512.0, 405.333))
                bezierTo(Vec2(512.0, 387.66), Vec2(497.673, 373.333), Vec2(480.0, 373.333))
                lineTo(Vec2(480.0, 373.33))
            }
        }
    }
}