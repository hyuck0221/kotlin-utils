package model

import org.locationtech.jts.geom.Point
import kotlin.math.sqrt

class Vector(
    val x: Double = 0.0,
    val y: Double = 0.0
) {
    operator fun component1(): Double {
        return x
    }

    operator fun component2(): Double {
        return y
    }

    operator fun minus(vector: Vector): Vector {
        return Vector(
            x = this.x - vector.x,
            y = this.y - vector.y
        )
    }

    operator fun plus(vector: Vector): Vector {
        return Vector(
            x = this.x + vector.x,
            y = this.y + vector.y
        )
    }

    operator fun times(vector: Vector): Double { // 내적
        return this.x * vector.x + this.y * vector.y
    }

    operator fun div(vector: Vector): Double { // 외적
        return this.x * vector.y - this.y * vector.x
    }

    operator fun times(scale: Double): Vector { // 스칼라곱
        return Vector(
            x = scale * this.x,
            y = scale * this.y
        )
    }

    val length: Double
        get() {
            return sqrt(x * x + y * y)
        }
    val squareLength: Double
        get() {
            return x * x + y * y
        }

    constructor(point: Point) : this(point.x.toDouble(), point.y.toDouble())
}