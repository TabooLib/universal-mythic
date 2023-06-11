package ink.ptms.um.impl4

import ink.ptms.um.event.MobSkillLoadEvent
import ink.ptms.um.skill.type.EntityTargetSkill
import ink.ptms.um.skill.type.LocationTargetSkill
import ink.ptms.um.skill.type.NoTargetSkill
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMechanicLoadEvent
import taboolib.common.platform.Ghost
import taboolib.common.platform.event.SubscribeEvent

internal object MobListenerSkill {

    @Ghost
    @SubscribeEvent
    fun onDropLoadEvent(event: MythicMechanicLoadEvent) {
        val e = MobSkillLoadEvent(event.mechanicName, event.config.toUniversal()).fire()
        // 如果注册的技能，不在这三种类型中，那么就是无效的技能类型
        if (e.registerSkill !is EntityTargetSkill && e.registerSkill !is LocationTargetSkill && e.registerSkill !is NoTargetSkill) {
            error("Unsupported skill: ${e.registerSkill}")
        }
    }
}