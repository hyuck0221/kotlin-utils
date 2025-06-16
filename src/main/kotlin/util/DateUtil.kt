package util

import model.DateFormatPattern
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * 날짜와 관련된 공통 함수를 모은 object
 * @version 0.0.1
 * @since 0.0.1
 * @see DateFormatPattern
 */
object DateUtil {

    /**
     * LocalDate를 pattern에 맞는 string 으로 변환하는 함수
     * @param pattern : format 형태 with default yyyy-MM-dd
     * @return pattern에 해당하는 형태를 가진 string
     */
    @JvmName("LocalDateToString_notNull")
    fun LocalDate.dateToString(
        pattern: String = DateFormatPattern.DATE_PATTERN
    ): String {
        val sdf = DateTimeFormatter.ofPattern(pattern)
        return sdf.format(this)
    }

    /**
     * pattern에 맞는 string으로 변환하는 함수 <LocalTime>
     * @param pattern : format 형태 with default HH:mm:ss
     * @return pattern에 해당하는 형태를 가진 string
     */
    @JvmName("LocalTimeToString_notNull")
    fun LocalTime.dateToString(
        pattern: String = DateFormatPattern.TIME_PATTERN
    ): String {
        val sdf = DateTimeFormatter.ofPattern(pattern)
        return sdf.format(this)
    }

    /**
     * pattern에 맞는 string으로 변환하는 함수 <LocalDateTime>
     * @param pattern : format 형태 with default yyyy-MM-dd HH:mm:ss
     * @return pattern에 해당하는 형태를 가진 string
     */
    @JvmName("LocalDateTimeToString_notNull")
    fun LocalDateTime.dateToString(
        pattern: String = DateFormatPattern.DATETIME_PATTERN
    ): String {
        val sdf = DateTimeFormatter.ofPattern(pattern)
        return sdf.format(this)
    }

    /**
     * pattern 형태의 string을 날짜 및 시간 데이터로 반환값에 맞춰 변경
     * @param pattern : date의 format with default "yyyy-MM-dd" or "HH:mm:ss" or "yyyy-MM-dd HH:mm:ss"
     * @return LocalDate or LocalTime or LocalDateTime
     * @exception RuntimeException date의 형태와 pattern이 다르면 exception
     */
    @JvmName("stringToDate_notNull")
    inline fun <reified T> String.stringToDate(
        pattern: String? = null
    ): T {
        val sdf = DateTimeFormatter.ofPattern(
            pattern ?: when (T::class.java) {
                LocalDate::class.java -> DateFormatPattern.DATE_PATTERN
                LocalTime::class.java -> DateFormatPattern.TIME_PATTERN
                LocalDateTime::class.java -> DateFormatPattern.DATETIME_PATTERN
                else -> throw Exception("반환값이 올바르지 않습니다")
            }
        )
        return when (T::class.java) {
            LocalDate::class.java -> LocalDate.parse(this, sdf) as T
            LocalTime::class.java -> LocalTime.parse(this, sdf) as T
            LocalDateTime::class.java -> LocalDateTime.parse(this, sdf) as T
            else -> throw Exception("반환값이 올바르지 않습니다")
        }
    }

    /**
     * 최근 날짜 조회
     * @param ifEmptyRes : 넣은 날짜 데이터가 null 이면 출력할 값
     */
    fun getLatestDateString(
        dateList: List<Date>,
        ifEmptyRes: String? = null
    ): String? {
        val latestDate = dateList.maxByOrNull { it.time }

        return if (latestDate != null) getDateString(latestDate)
        else ifEmptyRes
    }

    private fun getDateString(date: Date): String {
        val sdf = SimpleDateFormat(DateFormatPattern.DATETIME_PATTERN)
        return sdf.format(date)
    }

    /**
     * LocalDate 형식의 date 두개를 Pair<LocalDateTime, LocalDateTime> 형식으로 바꿔주는 함수
     * @param startDate : 시작날짜
     * @param endDate : 종료날짜
     */
    fun makeDateToDateTimeRange(
        startDate: LocalDate,
        endDate: LocalDate,
    ) = startDate.atTime(LocalTime.MIN) to endDate.atTime(LocalTime.MAX)

    /**
     * String을 YearMonth 형태로 바꿔주는 함수
     * @return YearMonth
     */
    @JvmName("stringToYearMonthNotNull")
    fun String.stringToYearMonth() = YearMonth.parse(this)

    /**
     * String?을 YearMonth? 형태로 바꿔주는 함수
     * @return YearMonth?
     */
    @JvmName("stringToYearMonthNull")
    fun String?.stringToYearMonth() = this?.stringToYearMonth()

    /**
     * LocalDate를 YearMonth 형태로 바꿔주는 ㅎ마수
     * @return YearMonth
     */
    fun LocalDate.dateToYearMonth() = YearMonth.of(this.year, this.month)
}