package ink.ptms.um.impl4

import ink.ptms.um.event.MobSkillLoadEvent
import ink.ptms.um.skill.SkillResult
import ink.ptms.um.skill.type.BaseSkill
import ink.ptms.um.skill.type.EntityTargetSkill
import ink.ptms.um.skill.type.LocationTargetSkill
import ink.ptms.um.skill.type.NoTargetSkill
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMechanicLoadEvent
import io.lumine.xikage.mythicmobs.io.MythicLineConfig
import io.lumine.xikage.mythicmobs.skills.*
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
        event.register(ProxySkill(registerSkill, event.mechanicName, event.config))
    }

    class ProxySkill(val skill: BaseSkill, name: String, mlc: MythicLineConfig) : SkillMechanic(name, mlc), ITargetedEntitySkill, ITargetedLocationSkill, INoTargetSkill {

        override fun castAtEntity(metadata: SkillMetadata, entity: AbstractEntity): Boolean {
            if (skill is EntityTargetSkill) {
                return skill.cast(metadata.toUniversal(), entity.bukkitEntity) == SkillResult.SUCCESS
            }
            warning("$skill is not ITargetedEntitySkill")
            return false
        }

        override fun castAtLocation(metadata: SkillMetadata, location: AbstractLocation): Boolean {
            if (skill is LocationTargetSkill) {
                return skill.cast(metadata.toUniversal(), location.toBukkit()) == SkillResult.SUCCESS
            }
            warning("$skill is not ITargetedLocationSkill")
            return false
        }

        override fun cast(metadata: SkillMetadata): Boolean {
            if (skill is NoTargetSkill) {
                return skill.cast(metadata.toUniversal()) == SkillResult.SUCCESS
            }
            warning("$skill is not INoTargetSkill")
            return false
        }
    }
}
