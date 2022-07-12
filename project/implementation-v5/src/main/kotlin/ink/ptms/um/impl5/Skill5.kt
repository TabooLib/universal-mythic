package ink.ptms.um.impl5

import ink.ptms.um.Skill
import io.lumine.mythic.api.adapters.AbstractEntity
import io.lumine.mythic.api.skills.SkillTrigger
import io.lumine.mythic.api.skills.placeholders.PlaceholderInt
import io.lumine.mythic.core.skills.SkillMechanic
import io.lumine.mythic.core.skills.SkillMetadataImpl
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import taboolib.common.reflect.Reflex.Companion.getProperty
import taboolib.common.reflect.Reflex.Companion.invokeConstructor

class Skill5(obj: Any) : Skill {

    val source = obj as SkillMechanic

    val placeholderDelay = (source as? io.lumine.mythic.core.skills.mechanics.DelaySkill)?.getProperty<PlaceholderInt>("ticks")

    override val delay: Int
        get() = placeholderDelay?.get() ?: -1

    override fun execute(
        trigger: Skill.Trigger,
        entity: Entity,
        target: Entity,
        entityTargets: Set<Entity>,
        locationTargets: Set<Location>,
        power: Float,
        args: Map<String, Any>,
        targetFilter: (Entity) -> Boolean
    ): Boolean {
        val caster: io.lumine.mythic.api.adapters.AbstractEntity = if (entity is Player) {
            io.lumine.mythic.bukkit.adapters.BukkitPlayer(entity)
        } else {
            io.lumine.mythic.bukkit.BukkitAdapter.adapt(entity)
        }
        return source.execute(
            SkillMetadataImpl::class.java.invokeConstructor(
                (trigger as Trigger).source,
                MythicCaster5(caster, args),
                io.lumine.mythic.bukkit.BukkitAdapter.adapt(target),
                io.lumine.mythic.bukkit.BukkitAdapter.adapt(entity.location),
                entityTargets.map { io.lumine.mythic.bukkit.BukkitAdapter.adapt(it) }.toHashSet(),
                locationTargets.map { io.lumine.mythic.bukkit.BukkitAdapter.adapt(it) }.toHashSet(),
                power
            )
        )
    }

    class Trigger(obj: Any) : Skill.Trigger {

        val source = obj as SkillTrigger

        override val name: String = source.name

    }

    class MythicCaster5(entity: AbstractEntity?, override val args: Map<String, Any>) :
        io.lumine.mythic.api.mobs.GenericCaster(entity), Skill.MythicCaster

}