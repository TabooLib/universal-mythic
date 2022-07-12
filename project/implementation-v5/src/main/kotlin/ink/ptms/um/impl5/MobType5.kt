package ink.ptms.um.impl5

import ink.ptms.um.Mob
import ink.ptms.um.MobType
import io.lumine.mythic.api.mobs.MythicMob
import org.bukkit.Location

class MobType5(obj: Any) : MobType {

    val source = obj as MythicMob

    override fun spawn(location: Location, level: Double): Mob {
        return Mob5(source.spawn(location.toMythic(), level))
    }

}