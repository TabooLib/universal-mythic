package ink.ptms.um.impl4

import ink.ptms.um.Mob
import ink.ptms.um.MobType
import io.lumine.xikage.mythicmobs.mobs.MythicMob
import org.bukkit.Location
import taboolib.library.configuration.ConfigurationSection

internal class MobType(val source: MythicMob) : MobType {

    override val id: String
        get() = source.internalName

    override val displayName: String
        get() = source.displayName.get()

    override val entityType: String
        get() = source.entityType

    override val config: ConfigurationSection
        get() = MobConfiguration(source.config)

    override fun spawn(location: Location, level: Double): Mob {
        return Mob(source.spawn(location.toMythic(), level))
    }
}