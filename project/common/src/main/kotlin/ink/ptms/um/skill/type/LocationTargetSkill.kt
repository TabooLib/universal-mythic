package ink.ptms.um.skill.type

import ink.ptms.um.skill.SkillMeta
import org.bukkit.Location

/**
 * universal-mythic
 * ink.ptms.um.skill.LocationTargetSkill
 *
 * @author 坏黑
 * @since 2023/6/6 16:06
 */
interface LocationTargetSkill : BaseSkill {

    /** 施法 */
    fun cast(meta: SkillMeta, location: Location): Boolean
}