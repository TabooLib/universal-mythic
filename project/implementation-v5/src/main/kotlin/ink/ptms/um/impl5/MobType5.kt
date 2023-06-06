package ink.ptms.um.impl5

import ink.ptms.um.Mob
import ink.ptms.um.MobType
import io.lumine.mythic.api.mobs.MythicMob
import org.bukkit.Location
import taboolib.library.configuration.ConfigurationSection

internal class MobType5(val source: MythicMob) : MobType {

    override val id: String
        get() = source.internalName

    override val displayName: String
        get() = source.displayName.get()

    override val entityType: String
        get() = source.entityType

    override val config: ConfigurationSection
        get() = MobConfiguration5(source.config)

    override fun spawn(location: Location, level: Double): Mob {
        return Mob5(source.spawn(location.toMythic(), level))
    }
}