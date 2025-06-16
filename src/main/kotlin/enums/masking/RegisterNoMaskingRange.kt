package enums.masking

enum class RegisterNoMaskingRange(
    val startIndex: Int,
    val endIndex: Int,
    val description: String
) {
    FRONT(0, 5, "주민번호 앞자리"),
    BACK(7, 13, "주민번호 뒷자리"),
    BACK_SERIAL_NO(8, 13, "주민번호 뒷자리 중 일련번호"),
}