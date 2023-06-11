package ink.ptms.um.event

import ink.ptms.um.skill.SkillConfig
import ink.ptms.um.skill.SkillMeta
import ink.ptms.um.skill.type.BaseSkill
import ink.ptms.um.skill.type.EntityTargetSkill
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import taboolib.common.platform.event.SubscribeEvent
import taboolib.platform.type.BukkitProxyEvent

/**
 * universal-mythic
 * ink.ptms.um.event.MobDropLoadEvent
 *
 * @author 坏黑
 * @since 2023/6/11 22:17
 */
class MobSkillLoadEvent(val skillName: String, val config: SkillConfig): BukkitProxyEvent() {

    var registerSkill: BaseSkill? = null

    fun register(skill: BaseSkill) {
        registerSkill = skill
    }

    fun nameIs(vararg name: String): Boolean {
        return name.any { it.equals(skillName, true) }
    }

    fun fire(): MobSkillLoadEvent {
        call()
        return this
    }
}

@SubscribeEvent
fun e(e: MobSkillLoadEvent) {
    if (e.nameIs("damage")) {
        e.register(object : EntityTargetSkill {

            val value = e.config.getPlaceholderDouble(arrayOf("value"), 0.0)

            override fun cast(meta: SkillMeta, entity: Entity): Boolean {
                if (entity !is LivingEntity) return false
                entity.damage(value[meta.caster])
                return true
            }
        })
    }
}