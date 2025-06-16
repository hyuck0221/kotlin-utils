package model

/**
 * Regex Pattern
 * @version 0.0.1
 * @since 0.0.1
 */
object RegexPattern {
    const val REGISTER_NO_REGEX = "(\\d{6})-?(\\d{7})$"
    const val PHONE_NO_REGEX = "(\\d{3})-?(\\d{4})-?(\\d{4})$"
    const val PLATE_NO_REGEX = "(\\d{2}|\\d{3})?([가-힣])?(\\d{4})$"
    const val FULL_PLATE_NO_REGEX = "([가-힣]{2})?(\\d{2}|\\d{3})?([가-힣])?(\\d{4})$"
    const val EMAIL_REGEX = "^[a-zA-Z0-9]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$"
}