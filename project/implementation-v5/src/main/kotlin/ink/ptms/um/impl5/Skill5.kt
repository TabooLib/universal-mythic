package ink.ptms.um.impl5

import ink.ptms.um.Skill
import io.lumine.mythic.api.adapters.AbstractEntity
import io.lumine.mythic.api.mobs.GenericCaster
import io.lumine.mythic.api.skills.SkillTrigger
import io.lumine.mythic.api.skills.placeholders.PlaceholderInt
import io.lumine.mythic.bukkit.BukkitAdapter
import io.lumine.mythic.bukkit.adapters.BukkitPlayer
import io.lumine.mythic.core.skills.SkillMechanic
import io.lumine.mythic.core.skills.SkillMetadataImpl
import io.lumine.mythic.core.skills.mechanics.DelaySkill
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import taboolib.library.reflex.Reflex.Companion.getProperty

internal class Skill5(obj: Any) : Skill {

    val source = obj as SkillMechanic

    val placeholderDelay = (source as? DelaySkill)?.getProperty<PlaceholderInt>("ticks")

    override val delay: Int
        get() = placeholderDelay?.get() ?: -1

    override fun execute(
        trigger: Skill.Trigger,
        entity: Entity,
        target: Entity,
        et: Set<Entity>,
        lt: Set<Location>,
        power: Float,
        parameters: Map<String, Any>,
        targetFilter: (Entity) -> Boolean
    ): Boolean {
        val caster = if (entity is Player) BukkitPlayer(entity) else BukkitAdapter.adapt(entity)
        return source.execute(
            SkillMetadataImpl(
                (trigger as Trigger).source,
                CasterImpl(caster, parameters),
                BukkitAdapter.adapt(target),
                BukkitAdapter.adapt(entity.location),
                et.map { BukkitAdapter.adapt(it) }.toHashSet(),
                lt.map { BukkitAdapter.adapt(it) }.toHashSet(),
                power
            )
        )
    }

    /** 获取技能是否正在冷却 */
    override fun onCooldown(caster: Entity): Boolean {
        return source.onCooldown(CasterImpl(BukkitAdapter.adapt(caster), emptyMap()))
    }

    /** 获取技能冷却 */
    override fun getCooldown(caster: Entity): Float {
        return source.getCooldown(CasterImpl(BukkitAdapter.adapt(caster), emptyMap()))
    }

    /** 设置技能冷却 */
    override fun setCooldown(caster: Entity, time: Double) {
        return source.setCooldown(CasterImpl(BukkitAdapter.adapt(caster), emptyMap()), time)
    }

    class Trigger(obj: Any) : Skill.Trigger {

        val source = obj as SkillTrigger<*>

        override val name: String = source.getProperty<String>("name")!!
    }

    class CasterImpl(entity: AbstractEntity?, override val parameters: Map<String, Any>) : GenericCaster(entity), Skill.ActiveCaster
}
