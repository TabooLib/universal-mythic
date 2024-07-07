package ink.ptms.um.skill.condition

import org.bukkit.entity.Entity

/** project name universal-mythic
 *  package ink.ptms.um.skill.condition
 *  time 2024/7/6
 *  author 劫
 */
interface EntityComparisonCondition : BaseCondition {
    fun check(caster: Entity?, entity: Entity?): Boolean
}