package util

import java.text.DecimalFormat

object NumberUtil {
    private val intFormat = "###,###"

    /**
     * 정수를 ###,### 형태의 string으로 변환
     * @param decimalPoint 소숫점 아래 표시할 자리수
     * @return ###,###.# 형태의 string
     */
    fun Number.toDecimalString(decimalPoint: Int = 0): String {
        var format = intFormat
        if (decimalPoint > 0) format += "." + "0".repeat(decimalPoint)
        val decimalFormat = DecimalFormat(format)
        return decimalFormat.format(this)
    }
}