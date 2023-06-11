package ink.ptms.um.skill

import ink.ptms.um.skill.data.PlaceholderDouble
import ink.ptms.um.skill.data.PlaceholderFloat
import ink.ptms.um.skill.data.PlaceholderInt
import ink.ptms.um.skill.data.PlaceholderString
import java.awt.Color

/**
 * universal-mythic
 * ink.ptms.um.Config
 *
 * @author 坏黑
 * @since 2023/6/6 15:51
 */
interface SkillConfig {

    /** 获取完整内容 */
    fun line(): String

    /** 获取元素数量 */
    fun size(): Int

    /** 获取所有元素 */
    fun entrySet(): Set<Map.Entry<String, Any>>

    /** 获取 Key */
    fun getKey(): String

    /** 获取 Key */
    fun getKey(s: String): String

    /** 获取布尔值 */
    fun getBoolean(key: String) = getBoolean(key, false)

    /** 获取布尔值 */
    fun getBoolean(key: String, def: Boolean) = getBoolean(arrayOf(key), def)

    /** 获取布尔值 */
    fun getBoolean(key: Array<String>, def: Boolean): Boolean

    /** 获取字符串 */
    fun getString(key: String) = getString(key, "")

    /** 获取字符串 */
    fun getString(key: String, def: String) = getString(arrayOf(key), def)

    /** 获取字符串 */
    fun getString(key: Array<String>, def: String): String

    /** 获取带变量的字符串 */
    fun getPlaceholderString(key: Array<String>, def: String, vararg args: String): PlaceholderString

    /** 获取整数 */
    fun getInt(key: String) = getInt(key, 0)

    /** 获取整数 */
    fun getInt(key: String, def: Int) = getInt(arrayOf(key), def)

    /** 获取整数 */
    fun getInt(key: Array<String>, def: Int): Int

    /** 获取带变量的整数 */
    fun getPlaceholderInt(key: String, def: Int, vararg args: String) = getPlaceholderInt(arrayOf(key), def, *args)

    /** 获取带变量的整数 */
    fun getPlaceholderInt(key: Array<String>, def: Int, vararg args: String): PlaceholderInt

    /** 获取浮点数（双精度）*/
    fun getDouble(key: String) = getDouble(key, 0.0)

    /** 获取浮点数（双精度）*/
    fun getDouble(key: String, def: Double) = getDouble(arrayOf(key), def)

    /** 获取浮点数（双精度）*/
    fun getDouble(key: Array<String>, def: Double): Double

    /** 获取带变量的浮点数（双精度）*/
    fun getPlaceholderDouble(key: String, def: Double, vararg args: String) = getPlaceholderDouble(arrayOf(key), def, *args)

    /** 获取带变量的浮点数（双精度）*/
    fun getPlaceholderDouble(key: Array<String>, def: Double, vararg args: String): PlaceholderDouble

    /** 获取浮点数（单精度）*/
    fun getFloat(key: String) = getFloat(key, 0f)

    /** 获取浮点数（单精度）*/
    fun getFloat(key: String, def: Float) = getFloat(arrayOf(key), def)

    /** 获取浮点数（单精度）*/
    fun getFloat(key: Array<String>, def: Float): Float

    /** 获取带变量的浮点数（单精度）*/
    fun getPlaceholderFloat(key: String, def: Float, vararg args: String) = getPlaceholderFloat(arrayOf(key), def, *args)

    /** 获取带变量的浮点数（单精度）*/
    fun getPlaceholderFloat(key: Array<String>, def: Float, vararg args: String): PlaceholderFloat

    /** 获取长整数 */
    fun getLong(key: String) = getLong(key, 0)

    /** 获取长整数 */
    fun getLong(key: String, def: Long) = getLong(arrayOf(key), def)

    /** 获取长整数 */
    fun getLong(key: Array<String>, def: Long): Long

    /** 获取颜色 */
    fun getColor(key: String, def: String): Color

    /** 获取颜色 */
    fun getColor(key: Array<String>, def: String): Color
}