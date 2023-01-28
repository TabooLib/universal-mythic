package ink.ptms.um.impl4

import ink.ptms.um.Mob
import ink.ptms.um.MobType
import io.lumine.xikage.mythicmobs.mobs.MythicMob
import org.bukkit.Location
import taboolib.library.configuration.ConfigurationSection

class MobType4(val source: MythicMob) : MobType {

    override val id: String
        get() = source.internalName

    override val displayName: String
        get() = source.displayName.get()

    override val entityType: String
        get() = source.entityType

    override val config: ConfigurationSection
        get() = Cache.mobConfiguration.getOrPut(id) { Mob4Configuration(source.config) }

    override fun spawn(location: Location, level: Double): Mob {
        return source.spawn(location.toMythic(), level).let {
            Mob4(it).apply {
                Cache.mob[it.uniqueId] = this
            }
        }
    }
}