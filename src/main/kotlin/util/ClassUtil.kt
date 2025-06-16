package util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.json.JSONObject

/**
 * Class 관련 편의 함수들을 모은 object
 * @version 0.0.1
 * @since 0.0.1
 */
object ClassUtil {

    /**
     * Class 안에 파라미터들을 Map으로 바꿔주는 함수
     * @return Key: parameter name, Value: parameter value 의 Map
     */
    fun Any.classToMap(): MutableMap<String, Any?> {
        val parameterMap = mutableMapOf<String, Any?>()
        this::class.java.declaredFields.forEach { field ->
            field.isAccessible = true
            parameterMap[field.name] = field.get(this)
        }
        return parameterMap
    }

    /**
     * Map을 Class로 바꿔주는 함수
     * @return Custom Class
     */
    inline fun <reified T> Map<String, Any?>.mapToClass(): T? {
        if (this.isEmpty()) return null
        return jacksonObjectMapper().readValue(
            JSONObject(this).toString(),
            T::class.java,
        )
    }

    /**
     * 객체를 json 으로 변환
     * @param needPretty json pretty 여부
     * @return json string
     */
    fun Any.classToJson(needPretty: Boolean = false): String {
        val mapper = jacksonObjectMapper()
        var writer = mapper.writer()
        if (needPretty) writer = mapper.writerWithDefaultPrettyPrinter()
        return writer.writeValueAsString(this)
    }

    /**
     * json 을 객체로 변환
     * @param objString 객체로 변환하고 하는 json string
     * @return object
     */
    inline fun <reified T> String.jsonToClass(): T {
        val mapper = jacksonObjectMapper()
        return mapper.readValue(this)
    }

    /**
     * 같은 Key 값의 데이터를 비교하여 다른 데이터인 Map만 남겨주는 함수
     * @param targetSourceMap1 비교할 타켓 Map 1번
     * @param targetSourceMap2 비교할 타켓 Map 2번
     * @return targetSourceMap 끼리  비교하여 서로 다른 데이터만 모은 Pair 형태의 Map
     */
    inline fun <reified T> findDifferentMapPair(
        targetSourceMap1: Map<T, Any?>,
        targetSourceMap2: Map<T, Any?>,
    ): Pair<Map<T, Any?>, Map<T, Any?>> {
        val updateBeforeColumnToValue = mutableMapOf<T, Any?>()
        val updateAfterColumnToValue = mutableMapOf<T, Any?>()
        targetSourceMap1.keys.forEach { columnName ->
            if (!targetSourceMap2.containsKey(columnName)) return@forEach
            if (targetSourceMap1[columnName] != targetSourceMap2[columnName]) {
                updateBeforeColumnToValue[columnName] = targetSourceMap1[columnName]
                updateAfterColumnToValue[columnName] = targetSourceMap2[columnName]
            }
        }

        return updateBeforeColumnToValue to updateAfterColumnToValue
    }
}