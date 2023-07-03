package ink.ptms.um.event

import ink.ptms.um.skill.SkillConfig
import ink.ptms.um.skill.type.BaseSkill
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

//    @SubscribeEvent
//    fun example(event: MobSkillLoadEvent) {
//        if (event.nameIs("example_damage")) {
//            event.register(object : EntityTargetSkill {
//
//                val value = event.config.getPlaceholderDouble(arrayOf("value"), 0.0)
//
//                override fun cast(meta: SkillMeta, entity: Entity): SkillResult {
//                    if (entity !is LivingEntity) return SkillResult.ERROR
//                    entity.damage(value[meta.caster])
//                    return SkillResult.SUCCESS
//                }
//            })
//        }
//    }
}