package util

import org.apache.commons.codec.binary.Base64
import java.io.UnsupportedEncodingException
import java.security.GeneralSecurityException
import java.security.Key
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * 암호화 관련 공통 함수를 모은 object
 * @version 0.0.1
 * @since 0.0.1
 */
object EncryptUtil {

    /**
     * 복호화가 가능한 AES256 으로 암호화 한다.
     *
     * @param str
     * 암호화할 문자열
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     * @throws UnsupportedEncodingException
     */
    @Throws(NoSuchAlgorithmException::class, GeneralSecurityException::class, UnsupportedEncodingException::class)
    fun encryptByAES256(
        message: String,
        key: String,
        iv: String? = null
    ): String {
        val iv = iv ?: getIv(key)
        val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
        c.init(Cipher.ENCRYPT_MODE, getKeySpec(key), IvParameterSpec(iv.toByteArray()))
        val encrypted = c.doFinal(message.toByteArray(charset("UTF-8")))
        return String(Base64.encodeBase64(encrypted))
    }

    /**
     * AES256으로 암호화된 txt 를 복호화한다.
     *
     * @param str
     * 복호화할 문자열
     * @param iv
     * 없다면 key의 1-16번째 문자열
     * @return
     * @throws NoSuchAlgorithmException
     * @throws GeneralSecurityException
     * @throws UnsupportedEncodingException
     */
    @Throws(NoSuchAlgorithmException::class, GeneralSecurityException::class, UnsupportedEncodingException::class)
    fun decryptByAES256(
        message: String,
        key: String,
        iv: String? = null
    ): String {
        val iv = iv ?: getIv(key)
        val c = Cipher.getInstance("AES/CBC/PKCS5Padding")
        c.init(Cipher.DECRYPT_MODE, getKeySpec(key), IvParameterSpec(iv.toByteArray()))
        val byteStr = Base64.decodeBase64(message.toByteArray())
        return String(c.doFinal(byteStr), charset("UTF-8"))
    }

    fun getKeySpec(
        key: String
    ): Key {
        val keyBytes = ByteArray(16)
        val b = key.toByteArray(charset("UTF-8"))
        var len = b.size
        if (len > keyBytes.size) {
            len = keyBytes.size
        }
        System.arraycopy(b, 0, keyBytes, 0, len)
        return SecretKeySpec(keyBytes, "AES")
    }

    fun getIv(
        key: String
    ): String {
        return key.substring(0, 16)
    }

    /**
     * 복호화할 수 없는 SHA256으로 암호화한다.
     */
    fun encryptBySHA256(code: String): String {
        try {
            return java.util.Base64.getEncoder().encodeToString(hashSHA256(code.toByteArray()))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

    private fun hashSHA256(data: ByteArray): ByteArray {
        val digest = MessageDigest.getInstance("SHA-256")
        return digest.digest(data)
    }
}
