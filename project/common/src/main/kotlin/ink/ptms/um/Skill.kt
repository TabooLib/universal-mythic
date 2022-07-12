package ink.ptms.um

import org.bukkit.Location
import org.bukkit.entity.Entity

/**
 * universal-mythic
 * ink.ptms.um.Skill
 *
 * @author 坏黑
 * @since 2022/7/12 13:34
 */
interface Skill {

    val delay: Int

    fun execute(
        trigger: Trigger,
        entity: Entity,
        target: Entity,
        entityTargets: Set<Entity> = emptySet(),
        locationTargets: Set<Location> = emptySet(),
        power: Float = 0f,
        args: Map<String, Any> = emptyMap(),
        targetFilter: (Entity) -> Boolean = { true },
    ): Boolean

    interface Trigger {

        val name: String
    }
}