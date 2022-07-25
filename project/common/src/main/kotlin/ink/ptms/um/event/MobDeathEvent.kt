package ink.ptms.um.event

import ink.ptms.um.Mob
import org.bukkit.entity.LivingEntity
import org.bukkit.inventory.ItemStack
import taboolib.platform.type.BukkitProxyEvent

class MobDeathEvent(val mob: Mob, val killer: LivingEntity?, val drop: MutableList<ItemStack> = mutableListOf()) : BukkitProxyEvent() {

    fun fire(): MobDeathEvent {
        call()
        return this
    }
}