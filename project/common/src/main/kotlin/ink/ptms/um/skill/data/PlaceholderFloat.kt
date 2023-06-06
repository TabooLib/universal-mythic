package ink.ptms.um.skill.data

import ink.ptms.um.skill.SkillCaster
import org.bukkit.entity.Entity

/**
 * universal-mythic
 * ink.ptms.um.data.PlaceholderFloat
 *
 * @author 坏黑
 * @since 2023/6/6 15:57
 */
interface PlaceholderFloat {

    /** 获取 */
    fun get(): Float

    /** 通过实体获取 */
    fun get(entity: Entity): Float

    /** 通过技能施法者获取 */
    fun get(caster: SkillCaster): Float
}