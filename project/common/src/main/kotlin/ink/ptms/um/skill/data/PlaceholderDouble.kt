package ink.ptms.um.skill.data

import ink.ptms.um.skill.SkillCaster
import org.bukkit.entity.Entity

/**
 * universal-mythic
 * ink.ptms.um.data.PlaceholderDouble
 *
 * @author 坏黑
 * @since 2023/6/6 15:57
 */
interface PlaceholderDouble {

    /** 获取 */
    fun get(): Double

    /** 通过实体获取 */
    fun get(entity: Entity): Double

    /** 通过技能施法者获取 */
    fun get(caster: SkillCaster): Double
}