package com.virusbear.tinn.math.noise

import com.virusbear.tinn.math.Vec3
import kotlin.math.floor

//TODO: fix straight lines at integer positions
class PerlinNoise(
    val octaves: Int = 1,
    val frequency: Double = 1.0,
    val amplitude: Double = 1.0,
    val persistence: Double = 0.5
): Noise {
    override fun sample(pos: Vec3): Double {
        var sample = 0.0
        var f = frequency
        var a = amplitude
        var range = 0.0
        for(i in 0 until octaves) {
            sample += sampleInternal((pos * frequency) * f) * a
            range += a
            a *= persistence
            f *= 2
        }

        return sample / range
    }

    private fun sampleInternal(pos: Vec3): Double {
        val xi = floor(pos.x).toInt() and 255
        val yi = floor(pos.y).toInt() and 255
        val zi = floor(pos.z).toInt() and 255

        val xx = pos.x - floor(pos.x)
        val yy = pos.y - floor(pos.y)
        val zz = pos.z - floor(pos.z)

        val u = fade(xx)
        val v = fade(yy)
        val w = fade(zz)

        val a = permutations[xi] + yi
        val aa = permutations[a] + zi
        val ab = permutations[a + 1] + zi
        val b = permutations[xi + 1] + yi
        val ba = permutations[b] + zi
        val bb = permutations[b + 1] + zi

        return lerp(w, lerp(v,
                lerp(u,
                    grad(permutations[aa], xx, yy, zz),
                    grad(permutations[ba], xx - 1, yy, zz)
                ),
                lerp(u,
                    grad(permutations[ab], xx, yy - 1, zz),
                    grad(permutations[bb], xx - 1, yy - 1, zz)
                )
            ),
            lerp(v, lerp(u,
                    grad(permutations[aa + 1], xx, yy, zz - 1),
                    grad(permutations[ba + 1], xx -1, yy, zz -1)
                ),
                lerp(u,
                    grad(permutations[ab + 1], xx, yy - 1, zz - 1),
                    grad(permutations[bb + 1], xx - 1, yy - 1, zz - 1)
                )
            )
        )
    }

    private fun fade(t: Double) =
        t * t * t * (t * (t * 6 - 15) + 10)

    private inline fun lerp(r: Double, s: Double, t: Double): Double =
        r + t * (s - r)

    private fun grad(hash: Int, x: Double, y: Double, z: Double): Double {
        val h = hash and 0x0F
        val u = if(h < 0x08) x else y
        val v = if(h < 0x04) y else if(h == 0x0C || h == 0x0E) x else z
        return  (if((h and 1) == 0) u else -u) +
                (if((h and 2) == 0) v else -v)
    }

    companion object {
        private val permutations = intArrayOf(
            151, 160, 137, 91 , 90 , 15 , 131, 13 , 201, 95 , 96 , 53 , 194, 233, 7  , 225,
            140, 36 , 103, 30 , 69 , 142, 8  , 99 , 37 , 240, 21 , 10 , 23 , 190, 6  , 148,
            247, 120, 234, 75 , 0  , 26 , 197, 62 , 94 , 252, 219, 203, 117, 35 , 11 , 32 ,
            57 , 177, 33 , 88 , 237, 149, 56 , 87 , 174, 20 , 125, 136, 171, 168, 68 , 175,
            74 , 165, 71 , 134, 139, 48 , 27 , 166, 77 , 146, 158, 231, 83 , 111, 229, 122,
            60 , 211, 133, 230, 220, 105, 92 , 41 , 55 , 46 , 245, 40 , 244, 102, 143, 54 ,
            65 , 25 , 63 , 161, 1  , 216, 80 , 73 , 209, 76 , 132, 187, 208, 89 , 18 , 169,
            200, 196, 135, 130, 116, 188, 159, 86 , 164, 100, 109, 198, 173, 186, 3  , 64 ,
            52 , 217, 226, 250, 124, 123, 5  , 202, 38 , 147, 118, 126, 255, 82 , 85 , 212,
            207, 206, 59 , 227, 47 , 16 , 58 , 17 , 182, 189, 28 , 42 , 223, 183, 170, 213,
            119, 248, 152, 2  , 44 , 154, 163, 70 , 221, 153, 101, 155, 167, 43 , 172, 9  ,
            129, 22 , 39 , 253, 19 , 98 , 108, 110, 79 , 113, 224, 232, 178, 185, 112, 104,
            218, 246, 97 , 228, 251, 34 , 242, 193, 238, 210, 144, 12 , 191, 179, 162, 241,
            81 , 51 , 145, 235, 249, 14 , 239, 107, 49 , 192, 214, 31 , 181, 199, 106, 157,
            184, 84 , 204, 176, 115, 121, 50 , 45 , 127, 4  , 150, 254, 138, 236, 205, 93 ,
            222, 114, 67 , 29 , 24 , 72 , 243, 141, 128, 195, 78 , 66 , 215, 61 , 156, 180,
            151, 160, 137, 91 , 90 , 15 , 131, 13 , 201, 95 , 96 , 53 , 194, 233, 7  , 225,
            140, 36 , 103, 30 , 69 , 142, 8  , 99 , 37 , 240, 21 , 10 , 23 , 190, 6  , 148,
            247, 120, 234, 75 , 0  , 26 , 197, 62 , 94 , 252, 219, 203, 117, 35 , 11 , 32 ,
            57 , 177, 33 , 88 , 237, 149, 56 , 87 , 174, 20 , 125, 136, 171, 168, 68 , 175,
            74 , 165, 71 , 134, 139, 48 , 27 , 166, 77 , 146, 158, 231, 83 , 111, 229, 122,
            60 , 211, 133, 230, 220, 105, 92 , 41 , 55 , 46 , 245, 40 , 244, 102, 143, 54 ,
            65 , 25 , 63 , 161, 1  , 216, 80 , 73 , 209, 76 , 132, 187, 208, 89 , 18 , 169,
            200, 196, 135, 130, 116, 188, 159, 86 , 164, 100, 109, 198, 173, 186, 3  , 64 ,
            52 , 217, 226, 250, 124, 123, 5  , 202, 38 , 147, 118, 126, 255, 82 , 85 , 212,
            207, 206, 59 , 227, 47 , 16 , 58 , 17 , 182, 189, 28 , 42 , 223, 183, 170, 213,
            119, 248, 152, 2  , 44 , 154, 163, 70 , 221, 153, 101, 155, 167, 43 , 172, 9  ,
            129, 22 , 39 , 253, 19 , 98 , 108, 110, 79 , 113, 224, 232, 178, 185, 112, 104,
            218, 246, 97 , 228, 251, 34 , 242, 193, 238, 210, 144, 12 , 191, 179, 162, 241,
            81 , 51 , 145, 235, 249, 14 , 239, 107, 49 , 192, 214, 31 , 181, 199, 106, 157,
            184, 84 , 204, 176, 115, 121, 50 , 45 , 127, 4  , 150, 254, 138, 236, 205, 93 ,
            222, 114, 67 , 29 , 24 , 72 , 243, 141, 128, 195, 78 , 66 , 215, 61 , 156, 180
        )
    }
}