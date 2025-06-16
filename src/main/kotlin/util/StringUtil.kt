package util

/**
 * String 관련 편의 함수들을 모은 object
 * @version 0.0.1
 * @since 0.0.1
 */
object StringUtil {

    private val camelRegex = "(?<=[a-zA-Z])[A-Z]".toRegex()
    private val snakeRegex = "_[a-z]".toRegex()

    /**
     * camelCase to snakeCase
     * @return string lower snakeCase
     */
    fun String.camelToSnakeCase(): String {
        return camelRegex.replace(this) {
            "_${it.value}"
        }.lowercase()
    }

    /**
     * snakeCase to camelCase
     * @return string camelCase
     */
    fun String.snakeToCamelCase(): String {
        return replace(snakeRegex) {
            it.value.last().uppercase()
        }
    }

    /**
     * 숫자, 문자를 제외한 특수문자를 제거하는 함수
     * @return 특수문자가 제거된 String
     */
    fun String.eraseDelimiter() = this.replace("[^0-9가-힣a-zA-Z]".toRegex(), "")
}
