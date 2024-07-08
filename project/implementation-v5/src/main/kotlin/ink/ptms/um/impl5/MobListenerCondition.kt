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
        val config = event.config
        val e = MobConditionLoadEvent(config.key, config.toUniversal()).fire()
        val registerCondition = e.skillCondition ?: return
        // 如果注册的条件，不在这些类型中，那么就是无效的技能类型
        if (!BaseCondition.isSubclass(registerCondition)) {
            error("Unsupported skill: $registerCondition")
        }
        when (registerCondition) {
            is EntityCondition -> event.register(Entity(registerCondition, config))
            is CasterCondition -> event.register(Caster(registerCondition, config))
            is LocationCondition -> event.register(Location(registerCondition, config))
            is SkillMetadataCondition -> event.register(SkillMeta(registerCondition, config))
            is EntityComparisonCondition -> event.register(EntityComparison(registerCondition, config))
            is EntityLocationDistanceCondition -> event.register(EntityLocation(registerCondition, config))
            is SkillMetaComparisonCondition -> event.register(SkillMetaComparison(registerCondition, config))
        }

    }


    class SkillMetaComparison(val condition: SkillMetaComparisonCondition, config: MythicLineConfig) : SkillCondition(config.line),
        ISkillMetaComparisonCondition {
        override fun check(p0: SkillMetadata?, p1: AbstractEntity?): Boolean {
            return condition.check(p0?.toUniversal(), p1?.bukkitEntity)
        }
    }

    class Caster(val condition: CasterCondition, config: MythicLineConfig) : SkillCondition(config.line), ICasterCondition {
        override fun check(p0: SkillCaster?): Boolean {
            return condition.check(p0?.toUniversal())
        }
    }

    class EntityComparison(val condition: EntityComparisonCondition, config: MythicLineConfig) : SkillCondition(config.line),
        IEntityComparisonCondition {
        override fun check(p0: AbstractEntity?, p1: AbstractEntity?): Boolean {
            return condition.check(p0?.bukkitEntity, p1?.bukkitEntity)
        }
    }

    class EntityLocation(val condition: EntityLocationDistanceCondition, config: MythicLineConfig) : SkillCondition(config.line),
        IEntityLocationComparisonCondition {
        override fun check(p0: AbstractEntity?, p1: AbstractLocation?): Boolean {
            return condition.check(p0?.bukkitEntity, p1?.toBukkit())
        }
    }

    class Entity(val condition: EntityCondition, config: MythicLineConfig) : SkillCondition(config.line), IEntityCondition {
        override fun check(p0: AbstractEntity?): Boolean {
            return condition.check(p0?.bukkitEntity)
        }
    }

    class SkillMeta(val condition: SkillMetadataCondition, config: MythicLineConfig) : SkillCondition(config.line), ISkillMetaCondition {
        override fun check(p0: SkillMetadata?): Boolean {
            return condition.check(p0?.toUniversal())
        }
    }

    class Location(val condition: LocationCondition, config: MythicLineConfig) : SkillCondition(config.line), ILocationCondition {
        override fun check(p0: AbstractLocation?): Boolean {
            return  condition.check(p0?.toBukkit())
        }
    }
}