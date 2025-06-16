package util

import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

/**
 * 시간과 관련된 공통 함수를 모은 object
 * @version 0.0.1
 * @since 0.0.1
 */
object TimeUtil {

    /** 00:00:00의 형태로 변환 */
    fun convertTo2HHMMSS(from: LocalDateTime, to: LocalDateTime): String {
        return convertTo2HHMMSS(calcDuration(from, to))
    }

    /** 00:00:00의 형태로 변환 */
    fun convertTo2HHMMSS(from: Date, to: Date): String {
        return convertTo2HHMMSS(calcDuration(from, to))
    }

    /** 00:00:00의 형태로 변환 */
    fun convertTo2HHMMSS(duration: Duration): String {
        val seconds = duration.seconds
        val remainSeconds = (seconds % 60).toString().padStart(2, '0')
        val minutes = seconds / 60
        val remainMinutes = (minutes % 60).toString().padStart(2, '0')
        val hours = (minutes / 60).toString().padStart(2, '0')

        return "$hours:$remainMinutes:$remainSeconds"
    }

    /** duration으로 변환 */
    fun calcDuration(from: Date, to: Date): Duration {
        val diff = from.time - to.time
        return Duration.ofMillis(diff)
    }

    /** duration으로 변환 */
    fun calcDuration(from: LocalDateTime, to: LocalDateTime): Duration {
        return Duration.between(from, to)
    }

    /** ZonedDateTime으로 변환 */
    fun toZonedDateTime(localDateTime: LocalDateTime): ZonedDateTime {
        return localDateTime.atZone(ZoneId.of("Asia/Seoul"))
    }

    /**
     * duration을 HH:mm:ss 형태의 시간으로 만들어주는 함수
     * @return HH:mm:ss 형태의 String
     */
    fun Duration.toTimeString(): String {
        val hours = this.seconds / 3600
        val minutes = (this.seconds % 3600) / 60
        val seconds = this.seconds % 60

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
    }


}