package ink.ptms.um.impl5

import ink.ptms.um.event.MobSkillLoadEvent
import ink.ptms.um.skill.type.BaseSkill
import ink.ptms.um.skill.type.EntityTargetSkill
import ink.ptms.um.skill.type.LocationTargetSkill
import ink.ptms.um.skill.type.NoTargetSkill
import io.lumine.mythic.api.adapters.AbstractEntity
import io.lumine.mythic.api.adapters.AbstractLocation
import io.lumine.mythic.api.config.MythicLineConfig
import io.lumine.mythic.api.skills.*
import io.lumine.mythic.bukkit.MythicBukkit
import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent
import io.lumine.mythic.core.skills.SkillExecutor
import io.lumine.mythic.core.skills.SkillMechanic
import taboolib.common.platform.Ghost
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.warning

internal object MobListenerSkill {

    @Ghost
    @SubscribeEvent
    fun onDropLoadEvent(event: MythicMechanicLoadEvent) {
        val e = MobSkillLoadEvent(event.mechanicName, event.config.toUniversal()).fire()
        val registerSkill = e.registerSkill ?: return
        // 如果注册的技能，不在这三种类型中，那么就是无效的技能类型
        if (registerSkill !is EntityTargetSkill && registerSkill !is LocationTargetSkill && registerSkill !is NoTargetSkill) {
            error("Unsupported skill: $registerSkill")
        }
        event.register(ProxySkill(registerSkill, MythicBukkit.inst().skillManager, event.mechanicName, event.config))
    }

    class ProxySkill(val skill: BaseSkill, manager: SkillExecutor, name: String, mlc: MythicLineConfig) : SkillMechanic(manager, name, mlc), ITargetedEntitySkill, ITargetedLocationSkill, INoTargetSkill {

        override fun castAtEntity(metadata: SkillMetadata, entity: AbstractEntity): SkillResult {
            if (skill is EntityTargetSkill) {
                return skill.cast(metadata.toUniversal(), entity.bukkitEntity).toMythic()
            }
            warning("$skill is not ITargetedEntitySkill")
            return SkillResult.ERROR
        }

        override fun castAtLocation(metadata: SkillMetadata, location: AbstractLocation): SkillResult {
            if (skill is LocationTargetSkill) {
                return skill.cast(metadata.toUniversal(), location.toBukkit()).toMythic()
            }
            warning("$skill is not ITargetedLocationSkill")
            return SkillResult.ERROR
        }

        override fun cast(metadata: SkillMetadata): SkillResult {
            if (skill is NoTargetSkill) {
                return skill.cast(metadata.toUniversal()).toMythic()
            }
            warning("$skill is not INoTargetSkill")
            return SkillResult.ERROR
        }

        private fun ink.ptms.um.skill.SkillResult.toMythic(): SkillResult {
            return try {
                SkillResult.values()[ordinal]
            } catch (_: Throwable) {
                SkillResult.ERROR
            }
        }
    }
}
