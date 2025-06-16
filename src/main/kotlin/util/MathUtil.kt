package util

import kotlin.math.*

/**
 * 수학 계산에 관한 공통 로직을 모은 object
 * @version 0.0.1
 * @since 0.0.1
 */
object MathUtil {
    /**
     * 직선 상에서 x를 구하는 함수
     * (x1, y1)과 (x2, y2)는 직선 상의 두 점
     * @return double y
     */
    fun getXFromLine(y: Double, x1: Double, y1: Double, x2: Double, y2: Double): Double {
        return (y - y1) * ((x2 - x1) / (y2 - y1)) + x1 // = x
    }

    /**
     * 직선 상에서 y를 구하는 함수
     * (x1, y1)과 (x2, y2)는 직선 상의 두 점
     * @return double x
     */
    fun getYFromLine(x: Double, x1: Double, y1: Double, x2: Double, y2: Double): Double {
        return (y2 - y1) / (x2 - x1) * (x - x1) + y1 // = y
    }

    /**
     * 두 위경도 사이의 거리를 계산
     * @return double 두 위경도 사이의 거리
     */
    fun calculateLineDistance(lat1: Double, lat2: Double, lng1: Double, lng2: Double): Double {
        //distance: [m]
        var distance = 0.0
        if (lat1 != lat2 || lng1 != lng2) {
            val tempVal = (sin(deg2rad(lat1)) * sin(deg2rad(lat2))
                    + cos(deg2rad(lat1)) * cos(deg2rad(lat2)) * cos(deg2rad(lng1 - lng2)))
            distance = (rad2deg(acos(max(-1.0, min(tempVal, 1.0)))) * 60 * 1.1515 * 1609.344)
        }
        if (java.lang.Double.isNaN(distance)) {
            println("lat1: " + lat1 + ", lat2: " + lat2 + ", lng1: " + lng1 + "lng2: " + lng2)
            println("distance is NaN")
            distance = 0.0
        }
        return distance
    }

    /** 주어진 도(degree) 값을 라디언으로 변환 */
    fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.toDouble()
    }

    /** 주어진 라디언(radian) 값을 도(degree) 값으로 변환 */
    fun rad2deg(rad: Double): Double {
        return rad * 180.toDouble() / Math.PI
    }

    /**
     * 백분율을 구하는 함수
     * @param total 총량
     * @param part 백분율을 알고자하는
     * @return double 퍼센트
     */
    fun calPercent(total: Int, part: Int): Double {
        return calPercent(total.toDouble(), part.toDouble())
    }

    /**
     * 백분율을 구하는 함수
     * @param total 총량
     * @param part 백분율을 알고자하는
     * @return double 퍼센트
     */
    fun calPercent(total: Double, part: Double): Double {
        return (part / total) * 100.0
    }

    /**
     * 반올림 해주는 함수
     * @param value 소수 데이터
     * @param pos 소숫점 몇째자리 까지 출력할지
     * @return double 반올림 결과값
     */
    fun calRoundToDecimalPoint(value: Double, pos: Int): Double {
        return ((value * 10.0.pow(pos.toDouble())).roundToInt() / 10.0.pow(pos.toDouble()))
    }


}