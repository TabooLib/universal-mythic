package ink.ptms.um.event

import ink.ptms.um.skill.SkillConfig
import ink.ptms.um.skill.condition.BaseCondition
import taboolib.platform.type.BukkitProxyEvent

/** project name universal-mythic
 *  package ink.ptms.um.event
 *  time 2024/7/4
 *  author 劫
 */
class MobConditionLoadEvent(val name: String, val config: SkillConfig) : BukkitProxyEvent() {
    var skillCondition: BaseCondition? = null

    fun register(condition: BaseCondition) {
        skillCondition = condition
    }

    fun fire(): MobConditionLoadEvent {
        call()
        return this
    }
}