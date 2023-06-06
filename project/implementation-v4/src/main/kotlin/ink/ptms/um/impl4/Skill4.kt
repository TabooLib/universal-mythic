package ink.ptms.um.impl4

import ink.ptms.um.Skill
import io.lumine.xikage.mythicmobs.MythicMobs
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitPlayer
import io.lumine.xikage.mythicmobs.mobs.GenericCaster
import io.lumine.xikage.mythicmobs.skills.SkillCaster
import io.lumine.xikage.mythicmobs.skills.SkillMechanic
import io.lumine.xikage.mythicmobs.skills.SkillMetadata
import io.lumine.xikage.mythicmobs.skills.SkillTrigger
import io.lumine.xikage.mythicmobs.skills.mechanics.DelaySkill
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import java.util.function.Function

internal class Skill4(val source: SkillMechanic) : Skill {

    override val delay: Int = (source as? DelaySkill)?.ticks ?: -1

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
        MythicMobs.inst().skillManager.runSecondPass()
        return source.executeSkills(
            MetadataImpl(
                (trigger as Trigger).source,
                CasterImpl(caster, parameters),
                BukkitAdapter.adapt(target),
                BukkitAdapter.adapt(entity.location),
                et.map { BukkitAdapter.adapt(it) }.toHashSet(),
                lt.map { BukkitAdapter.adapt(it) }.toHashSet(),
                power,
                targetFilter
            )
        )
    }

    class Trigger(obj: Any) : Skill.Trigger {

        val source = obj as SkillTrigger

        override val name: String = source.name
    }

    /**
     * 技能施法者内部实现
     */
    class CasterImpl(entity: AbstractEntity?, override val parameters: Map<String, Any>) : GenericCaster(entity), Skill.ActiveCaster

    /**
     * 技能元数据内部实现
     */
    class MetadataImpl(
        cause: SkillTrigger,
        caster: SkillCaster,
        trigger: AbstractEntity,
        origin: AbstractLocation,
        et: HashSet<AbstractEntity>,
        lt: HashSet<AbstractLocation>,
        power: Float,
        val targetFilter: Function<Entity, Boolean>
    ) : SkillMetadata(cause, caster, trigger, origin, et.filter { targetFilter.apply(it.bukkitEntity) }.toHashSet(), lt, power) {

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