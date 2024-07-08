package ink.ptms.um.skill.condition

import ink.ptms.um.skill.SkillMeta
import org.bukkit.entity.Entity

/** project name universal-mythic
 *  package ink.ptms.um.skill.condition
 *  time 2024/7/8
 *  author 劫
 */
interface SkillMetaComparisonCondition:BaseCondition {
    fun check(skillMeta: SkillMeta?, entity: Entity?):Boolean
}