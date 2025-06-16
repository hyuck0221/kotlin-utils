package util

import com.github.f4b6a3.ulid.Ulid
import org.apache.commons.codec.binary.Base64
import java.nio.ByteBuffer
import java.util.*

/**
 * 공통적으로 사용하는 함수를 모아놓은 object
 * @version 0.0.1
 * @since 0.0.1
 */
object CommonUtil {
    /**
     * uuid를 생성하는 함수
     * @return uuid
     */
    fun uuid(): String {
        val uuid = UUID.randomUUID()
        val bb = ByteBuffer.wrap(ByteArray(16))
        bb.putLong(uuid.mostSignificantBits)
        bb.putLong(uuid.leastSignificantBits)
        return Base64.encodeBase64URLSafeString(bb.array())
    }

    /**
     * ulid를 생성하는 함수
     * @return ulid
     */
    fun ulid() = Ulid.fast().toString()
}