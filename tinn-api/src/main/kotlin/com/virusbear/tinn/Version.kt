package com.virusbear.tinn

data class Version(
    val major: Int,
    val minor: Int,
    val patch: Int
) {
    operator fun compareTo(other: Version): Int =
        when {
            major - other.major != 0 -> major - other.major
            minor - other.minor != 0 -> minor - other.minor
            else -> patch - other.patch
        }

    companion object {
        fun fromString(versionString: String): Version {
            val parts = versionString.split(Regex("\\.")).mapNotNull { it.toIntOrNull() }
            require(parts.size == 3) { "Unsupported version format" }

            val (major, minor, patch) = parts

            return Version(major, minor, patch)
        }
    }
}

val String.version: Version
    get() = Version.fromString(this)