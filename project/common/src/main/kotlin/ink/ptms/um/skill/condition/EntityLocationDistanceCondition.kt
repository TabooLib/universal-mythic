package ink.ptms.um.skill.condition

import org.bukkit.Location
import org.bukkit.entity.Entity

/** project name universal-mythic
 *  package ink.ptms.um.skill.condition
 *  time 2024/7/6
 *  author 劫
 */
interface EntityLocationDistanceCondition : BaseCondition {
    fun check(entity: Entity?, location: Location?): Boolean
}