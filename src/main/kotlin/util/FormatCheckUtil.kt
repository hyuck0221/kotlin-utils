package util

import model.DateFormatPattern.DATETIME_PATTERN
import model.DateFormatPattern.DATE_PATTERN
import model.RegexPattern.FULL_PLATE_NO_REGEX
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.regex.Pattern

/**
 * 데이터 포맷을 체크하는 함수를 모든 object
 * @version 0.0.1
 * @since 0.0.1
 * @see CustomPageable
 */
object FormatCheckUtil {
    private const val mobileNoRegex = "(?:\\d{2}|\\d{3})-(?:\\d{3}|\\d{4})-(\\d{4})$"
    private const val plateNoRegex = "(?:\\d{2}|\\d{3})-[가-힣]-(\\d{4})\$"
    private const val emailRegex = "^[a-zA-Z0-9]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$"

    /**
     * yyyy-MM-dd format인지 체크하는 함수
     * @return true: format과 동일 false: format과 다름
     */
    fun isValidDateFormat(dateStr: String): Boolean {
        return try {
            DateTimeFormatter.ofPattern(DATE_PATTERN).parse(dateStr)
            true
        } catch (e: DateTimeParseException) {
            false
        }
    }

    /**
     * yyyy-MM-dd hh:mm:ss format인지 체크하는 함수
     * @return true: format과 동일 false: format과 다름
     */
    fun isValidDateTimeFormat(dateStr: String): Boolean {
        return try {
            DateTimeFormatter.ofPattern(DATETIME_PATTERN).parse(dateStr)
            true
        } catch (e: DateTimeParseException) {
            false
        }
    }

    /**
     * 연락처 format이 맞는지 확인하는 함수(2 or 3자리 - 3 or 4자리 - 4자리)
     * @return true: format과 동일 false: format과 다름
     */
    fun isValidMobileNo(mobileNo: String): Boolean {
        return Pattern.matches(mobileNoRegex, mobileNo)
    }

    /**
     * 차량 번호 format이 맞는지 확인하는 함수(2 or 3 자리 - 한글 - 4자리)
     * @return true: format과 동일 false: format과 다름
     */
    fun isValidPlateNo(plateNo: String): Boolean {
        return Pattern.matches(plateNoRegex, plateNo)
    }

    /**
     * 전체 차량 번호 format이 맞는지 확인하는 함수(한글 2자리 - 2 or 3자리 - 한글 - 4자리)
     * @return true: format과 동일 false: format과 다름
     */
    fun isValidFullPlateNo(plateNo: String): Boolean {
        return Pattern.matches(FULL_PLATE_NO_REGEX, plateNo)
    }

    /**
     * email format이 맞는지 확인하는 함수"(a@b.com)"
     * @return true: format과 동일 false: format과 다름
     */
    fun isValidEmail(email: String): Boolean {
        return Pattern.matches(emailRegex, email)
    }
}