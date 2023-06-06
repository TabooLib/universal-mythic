package ink.ptms.um.skill.data

import ink.ptms.um.skill.SkillCaster
import org.bukkit.entity.Entity

/**
 * universal-mythic
 * ink.ptms.um.data.PlaceholderInt
 *
 * @author 坏黑
 * @since 2023/6/6 15:57
 */
interface PlaceholderInt {

    /** 获取 */
    fun get(): Int

    /** 通过实体获取 */
    fun get(entity: Entity): Int

    /** 通过技能施法者获取 */
    fun get(caster: SkillCaster): Int
}