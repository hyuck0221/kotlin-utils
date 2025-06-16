package util

import enums.masking.PhoneNoMaskingRange
import enums.masking.RegisterNoMaskingRange
import model.RegexPattern.PHONE_NO_REGEX
import model.RegexPattern.REGISTER_NO_REGEX
import java.util.regex.Pattern

/**
 * 데이터 마스킹을 위한 함수들을 모은 Object
 * @version 0.0.1
 * @since 0.0.1
 */
object MaskingUtil {

    /**
     * index 범위만큼 masking 해주는 함수
     * @param startIndex : 마스킹 시작 index
     * @param endIndex : 마스킹 종료 index
     * @param mask : 마스킹 되는 문자 default : "*"
     * @return index 범위만큼 masking 된 String 값
     */
    fun String.masking(
        startIndex: Int,
        endIndex: Int,
        mask: String = "*"
    ): String {

        // validate
        when {
            startIndex < 0 -> throw IllegalArgumentException("startIndex is less than 0")
            startIndex > this.lastIndex -> throw IllegalArgumentException("startIndex is out of range for the given string length")
            endIndex > this.lastIndex -> throw IllegalArgumentException("endIndex is out of range for the given string length")
            startIndex > endIndex -> throw IllegalArgumentException("startIndex is greater than endIndex")
        }

        // build maskString
        val maskString = buildString {
            for (i in startIndex..endIndex) {
                append(mask)
            }
        }

        return this.substring(0, startIndex) + maskString + this.substring(endIndex + 1)
    }

    /**
     * 주민등록번호 마스킹 함수
     * @param maskingRange 주민번호 마스킹 범위 default : BACK
     * @param mask : 마스킹 되는 문자 default : "*"
     * @return 마스킹 범위만큼 마스킹 된 String, 주민등록번호 형식이 아니면 원본 그대로 return
     */
    fun String.registerNoMasking(
        maskingRange: RegisterNoMaskingRange = RegisterNoMaskingRange.BACK,
        mask: String = "*"
    ): String {
        val matcher = Pattern.compile(REGISTER_NO_REGEX).matcher(this)
        return when (matcher.find()) {
            true -> this.masking(maskingRange.startIndex, maskingRange.endIndex, mask)
            false -> this
        }
    }

    /**
     * 전화번호 마스킹 함수
     * @param maskingRange 전화번호 마스킹 범위 default : BACK
     * @param mask : 마스킹 되는 문자 default : "*"
     * @return 마스킹 범위만큼 마스킹 된 String, 전화번호 형식이 아니면 원본 그대로 return
     */
    fun String.phoneNoMasking(
        maskingRange: PhoneNoMaskingRange = PhoneNoMaskingRange.BACK,
        mask: String = "*"
    ): String {
        val matcher = Pattern.compile(PHONE_NO_REGEX).matcher(this)
        return when (matcher.find()) {
            true -> this.masking(maskingRange.startIndex, maskingRange.endIndex, mask)
            false -> this
        }
    }
}