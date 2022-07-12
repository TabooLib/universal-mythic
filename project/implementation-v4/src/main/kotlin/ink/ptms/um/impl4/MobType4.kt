package ink.ptms.um.impl4

import ink.ptms.um.Mob
import ink.ptms.um.MobType
import io.lumine.xikage.mythicmobs.mobs.MythicMob
import org.bukkit.Location

class MobType4(obj: Any) : MobType {

    val source = obj as MythicMob

    override fun spawn(location: Location, level: Double): Mob {
        return Mob4(source.spawn(location.toMythic(), level))
    }
}