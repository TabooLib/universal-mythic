package ink.ptms.um.skill.type

import org.bukkit.entity.Entity

/** project name universal-mythic
 *  package ink.ptms.um.skill.type
 *  time 2024/7/3
 *  author 劫
 */
interface EntityCondition:BaseSkill {
    fun check(entity: Entity?):Boolean
}