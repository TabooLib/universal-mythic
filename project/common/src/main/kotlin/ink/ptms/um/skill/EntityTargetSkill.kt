package ink.ptms.um.skill

import org.bukkit.entity.Entity

/**
 * universal-mythic
 * ink.ptms.um.skill.EntityTargetSkill
 *
 * @author 坏黑
 * @since 2023/6/6 16:06
 */
interface EntityTargetSkill {

    /** 施法 */
    fun cast(meta: SkillMeta, entity: Entity): Boolean
}