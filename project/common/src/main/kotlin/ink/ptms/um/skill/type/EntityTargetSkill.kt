package ink.ptms.um.skill.type

import ink.ptms.um.skill.SkillMeta
import ink.ptms.um.skill.SkillResult
import org.bukkit.entity.Entity

/**
 * universal-mythic
 * ink.ptms.um.skill.EntityTargetSkill
 *
 * @author 坏黑
 * @since 2023/6/6 16:06
 */
interface EntityTargetSkill : BaseSkill {

    /** 施法 */
    fun cast(meta: SkillMeta, entity: Entity): SkillResult
}