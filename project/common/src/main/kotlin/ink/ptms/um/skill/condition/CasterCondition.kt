package ink.ptms.um.skill.condition

import ink.ptms.um.skill.SkillCaster

/** project name universal-mythic
 *  package ink.ptms.um.skill.condition
 *  time 2024/7/6
 *  author 劫
 */
interface CasterCondition:BaseCondition {
    fun check(skillCaster: SkillCaster?):Boolean
}