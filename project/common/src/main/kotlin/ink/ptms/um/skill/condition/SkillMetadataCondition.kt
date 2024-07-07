package ink.ptms.um.skill.condition

import ink.ptms.um.skill.SkillMeta

/** project name universal-mythic
 *  package ink.ptms.um.skill.condition
 *  time 2024/7/6
 *  author 劫
 */
interface SkillMetadataCondition :BaseCondition{
    fun check(skill:SkillMeta):Boolean
}