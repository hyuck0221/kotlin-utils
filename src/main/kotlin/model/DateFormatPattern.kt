package model

/**
 * 기본적인 날짜 형태
 * @version 0.0.1
 * @since 0.0.1
 */
object DateFormatPattern {
    /**yyyy-MM-dd HH:mm:ss*/
    const val DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss"
    /**yyyy-MM-dd*/
    const val DATE_PATTERN = "yyyy-MM-dd"
    /**HH:mm:ss*/
    const val TIME_PATTERN = "HH:mm:ss"
    /**yyyy-MM-dd HH:mm:ss*/
    const val DATETIME_PATTERN_FOR_QUERY = "%Y-%m-%d %H:%i:%s"
    /**yyyy-MM-dd*/
    const val DATE_PATTERN_FOR_QUERY = "%Y-%m-%d"
    /**HH:mm:ss*/
    const val TIME_PATTERN_FOR_QUERY = "%H:%i:%s"
}