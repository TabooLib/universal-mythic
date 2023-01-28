package ink.ptms.um.impl4

import ink.ptms.um.Skill
import io.lumine.xikage.mythicmobs.MythicMobs
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitPlayer
import io.lumine.xikage.mythicmobs.skills.SkillCaster
import io.lumine.xikage.mythicmobs.skills.SkillMechanic
import io.lumine.xikage.mythicmobs.skills.SkillMetadata
import io.lumine.xikage.mythicmobs.skills.SkillTrigger
import io.lumine.xikage.mythicmobs.skills.mechanics.DelaySkill
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import java.util.function.Function

class Skill4(val source: SkillMechanic) : Skill {

    override val delay: Int = (source as? DelaySkill)?.ticks ?: -1

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
        val caster: AbstractEntity = if (entity is Player) BukkitPlayer(entity) else BukkitAdapter.adapt(entity)
        MythicMobs.inst().skillManager.runSecondPass()
        return source.executeSkills(
            MythicSkillMetadata4(
                (trigger as Trigger).source,
                MythicCaster4(caster, args),
                BukkitAdapter.adapt(target),
                BukkitAdapter.adapt(entity.location),
                entityTargets.map { BukkitAdapter.adapt(it) }.toHashSet(),
                locationTargets.map { BukkitAdapter.adapt(it) }.toHashSet(),
                power,
                targetFilter
            )
        )
    }

    class Trigger(obj: Any) : Skill.Trigger {

        val source = obj as SkillTrigger

        override val name: String = source.name

    }

    class MythicCaster4(entity: AbstractEntity?, override val args: Map<String, Any>) :
        io.lumine.xikage.mythicmobs.mobs.GenericCaster(entity), Skill.MythicCaster


    class MythicSkillMetadata4(
        cause: SkillTrigger,
        am: SkillCaster,
        trigger: AbstractEntity,
        origin: AbstractLocation,
        eTargets: HashSet<AbstractEntity>,
        lTargets: HashSet<AbstractLocation>,
        power: Float,
        val targetFilter: Function<Entity, Boolean>
    ) : SkillMetadata(
        cause,
        am,
        trigger,
        origin,
        eTargets.filter { targetFilter.apply(it.bukkitEntity) }.toHashSet(),
        lTargets,
        power
    ) {

        override fun setEntityTarget(target: AbstractEntity): SkillMetadata {
            if (targetFilter.apply(target.bukkitEntity)) {
                return super.setEntityTarget(target)
            }
            return this
        }

        override fun setEntityTargets(targets: java.util.HashSet<AbstractEntity>): SkillMetadata {
            return super.setEntityTargets(targets.filter { targetFilter.apply(it.bukkitEntity) }.toHashSet())
        }
    }
}