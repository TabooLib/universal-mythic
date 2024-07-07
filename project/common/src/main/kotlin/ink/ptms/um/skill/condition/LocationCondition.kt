package ink.ptms.um.skill.condition

import org.bukkit.Location

/** project name universal-mythic
 *  package ink.ptms.um.skill.condition
 *  time 2024/7/6
 *  author 劫
 */
interface LocationCondition:BaseCondition {
    fun check(location: Location):Boolean
}