package ink.ptms.um.impl4

import ink.ptms.um.Mob
import ink.ptms.um.MobType
import io.lumine.xikage.mythicmobs.mobs.ActiveMob
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import taboolib.library.configuration.ConfigurationSection

/**
 * universal-mythic
 * ink.ptms.um.impl4.Mob4
 *
 * @author 坏黑
 * @since 2022/7/12 13:51
 */
internal class Mob(val source: ActiveMob) : Mob {

    override val id: String
        get() = source.type.internalName

    override val displayName: String
        get() = source.displayName

    override val type: MobType
        get() = MobType(source.type)

    override val entity: Entity
        get() = source.entity.bukkitEntity

    override val entityType: EntityType
        get() = entity.type

    override val level: Double
        get() = source.level

    override val stance: String
        get() = source.stance

    override val faction: String
        get() = source.faction

    override val config: ConfigurationSection
        get() = type.config
}