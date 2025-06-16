package model

import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.LineSegment
import org.locationtech.jts.geom.Point
import org.locationtech.jts.geom.impl.CoordinateArraySequenceFactory
import kotlin.math.abs

class Line (
    var start: Vector = Vector(),
    var end: Vector = Vector()
){
    operator fun component1(): Vector {
        return start
    }
    operator fun component2(): Vector {
        return end
    }

    /**
     * 선분 상에서 어떤 점과 가장 가까운 점을 구하는 함수
     * @param target 점의 위치벡터
     * @return Vector 가장 가까운 점의 위치벡터
     */
    fun closestPointToPoint(target: Vector): Vector {
        if ((end - start) * (target - start) <= 0.0)
            return start
        if ((start - end) * (target - end) <= 0.0)
            return end

        val u = end - start
        val v = target - start

        val x = (v * u) / (u.squareLength)

        val h = start + u * x

        return h
    }

    /**
     * 점에서 선분에 내린 수선의 발의 길이
     * @param target 점의 위치벡터
     * @return Double? 수선의 길이, 선분의 길이가 0일경우 null
     */
    fun lengthOfPerpendicular(target: Vector): Double? {
        if((end-start).length == 0.0) return null
        return abs((end - start) / (target - start)) / (end - start).length
    }

    constructor(line: LineSegment):this(){
        val geometryFactory = GeometryFactory()
        val coordinateSequenceFactory:
            CoordinateArraySequenceFactory =
            CoordinateArraySequenceFactory.instance()
        start = Vector(Point(coordinateSequenceFactory.create(arrayOf(line.getCoordinate(0))), geometryFactory))
        end = Vector(Point(coordinateSequenceFactory.create(arrayOf(line.getCoordinate(1))), geometryFactory))
    }

}