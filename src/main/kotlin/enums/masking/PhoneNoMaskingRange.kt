package enums.masking

enum class PhoneNoMaskingRange(
    val startIndex: Int,
    val endIndex: Int,
    val description: String
) {
    FRONT(0, 2, "전화번호 앞자리"),
    MIDDLE(4, 7, "전화번호 가운데자리"),
    BACK(9, 12, "전화번호 뒷자리"),
}