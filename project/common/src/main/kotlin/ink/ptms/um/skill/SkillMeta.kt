package ink.ptms.um.skill

import ink.ptms.um.Skill
import org.bukkit.Location
import org.bukkit.entity.Entity

/**
 * universal-mythic
 * ink.ptms.um.skill.SkillMeta
 *
 * @author 坏黑
 * @since 2023/6/6 15:28
 */
interface SkillMeta {

    /** 技能施法者 */
    var caster: SkillCaster

    /** 技能触发者 */
    var trigger: Entity

    /** 原点 */
    var origin: Location

    /** 释放方式 */
    val cause: Skill.Trigger

    /** 强度 */
    var power: Float

    /** 是否异步 */
    val isAsync: Boolean

    /** 目标实体 */
    val entityTargets: Set<Entity>

    /** 目标坐标 */
    val locationTargets: Set<Location>

    /** 元数据 */
    val metadata: Map<String, Any>

    /** 参数 */
    val parameters: Map<String, String>

    /** 设置元数据 */
    fun setMetadata(key: String, value: Any)

    /** 设置参数 */
    fun setParameter(key: String, value: String)

    /** 复制 */
    fun clone(): SkillMeta

    /** 深度复制 */
    fun deepClone(): SkillMeta
}
