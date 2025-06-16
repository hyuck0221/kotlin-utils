package model

import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.LineString
import org.locationtech.jts.geom.Point
import org.locationtech.jts.geom.impl.CoordinateArraySequenceFactory

class MultiLine(
    val lineList: MutableList<Line>
) {
    /**
     * multiline 상에서 어떤 점과 가장 가까운 점을 구하는 함수
     * @param target 점의 위치벡터
     * @return Vector 가장 가까운 점의 위치벡터, multiline이 비어있으면 null
     */
    fun closestPointToPoint(
        target: Vector
    ): Vector? {
        return lineList.map { line ->
            val point = line.closestPointToPoint(target)
            Pair((point - target).length, point)
        }.minByOrNull { it.first }?.second
    }

    fun closestPointToPointWithIndex(
        target: Vector
    ): Pair<Int, Vector>? {
        return lineList.mapIndexed { index, line ->
            val point = line.closestPointToPoint(target)
            Triple(index, (point - target).length, point)
        }.minByOrNull { it.second }
            ?.let { Pair(it.first, it.third) }
    }


    operator fun get(i: Int): Line? {
        if (i > lineList.lastIndex) return null
        return lineList[i]
    }

    constructor(lineString: LineString) : this(mutableListOf()) {
        val geometryFactory = GeometryFactory()
        val coordinateSequenceFactory:
                CoordinateArraySequenceFactory =
            CoordinateArraySequenceFactory.instance()
        for (i in 1..lineString.coordinates.lastIndex) {
            val start =
                Vector(Point(coordinateSequenceFactory.create(arrayOf(lineString.coordinates[i - 1])), geometryFactory))
            val end =
                Vector(Point(coordinateSequenceFactory.create(arrayOf(lineString.coordinates[i])), geometryFactory))
            this.lineList.add(Line(start, end))
        }
    }
}