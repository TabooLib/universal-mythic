package ink.ptms.um.impl5

import ink.ptms.um.event.MobConditionLoadEvent
import ink.ptms.um.skill.condition.*
import io.lumine.mythic.api.adapters.AbstractEntity
import io.lumine.mythic.api.adapters.AbstractLocation
import io.lumine.mythic.api.config.MythicLineConfig
import io.lumine.mythic.api.skills.SkillCaster
import io.lumine.mythic.api.skills.SkillMetadata
import io.lumine.mythic.api.skills.conditions.*
import io.lumine.mythic.bukkit.events.MythicConditionLoadEvent
import io.lumine.mythic.core.skills.SkillCondition
import taboolib.common.platform.Ghost
import taboolib.common.platform.event.SubscribeEvent

/** project name universal-mythic
 *  package ink.ptms.um.impl5
 *  time 2024/7/6
 *  author 劫
 */
internal object MobListenerCondition {
    @Ghost
    @SubscribeEvent
    fun onMythicConditionLoad(event: MythicConditionLoadEvent) {
        val e = MobConditionLoadEvent(event.conditionName, event.config.toUniversal()).fire()
        val registerCondition = e.skillCondition ?: return
        // 如果注册的条件，不在这些类型中，那么就是无效的技能类型
        if (!BaseCondition.isSubclass(registerCondition)) {
            error("Unsupported skill: $registerCondition")
        }
        event.register(ProxyCondition(registerCondition, event.config))

    }

    class ProxyCondition(private val skillCondition: BaseCondition, config: MythicLineConfig) : SkillCondition(config.line),
        ICasterCondition, IEntityComparisonCondition, IEntityCondition, IEntityLocationComparisonCondition,
        ILocationCondition, ISkillMetaCondition {
        override fun check(p0: SkillCaster?): Boolean {
            return if (skillCondition is CasterCondition) {
                p0?.toUniversal()?.let { skillCondition.check(it) } ?: false
            } else false
        }

        override fun check(p0: AbstractEntity?, p1: AbstractEntity?): Boolean {
            return if (skillCondition is EntityComparisonCondition) {
                skillCondition.check(p0?.bukkitEntity, p1?.bukkitEntity)
            } else false
        }

        override fun check(p0: AbstractEntity?): Boolean {
            return if (skillCondition is EntityCondition) {
                p0?.let { skillCondition.check(it.bukkitEntity) } ?: false
            } else false
        }

        override fun check(p0: AbstractEntity?, p1: AbstractLocation?): Boolean {
            return if (skillCondition is EntityLocationDistanceCondition) {
                p1?.let { skillCondition.check(p0?.bukkitEntity, it.toBukkit()) } ?: false
            } else false
        }

        override fun check(p0: AbstractLocation?): Boolean {
            return if (skillCondition is LocationCondition) {
                p0?.toBukkit()?.let { skillCondition.check(it) } ?: false
            } else false
        }

        override fun check(p0: SkillMetadata?): Boolean {
            return if (skillCondition is SkillMetadataCondition) {
                p0?.toUniversal()?.let { skillCondition.check(it) } ?: false
            } else false
        }


    }
}