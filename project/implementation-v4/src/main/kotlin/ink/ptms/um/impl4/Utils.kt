package ink.ptms.um.impl4

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity
import io.lumine.xikage.mythicmobs.adapters.AbstractItemStack
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack

fun AbstractItemStack.toBukkit(): ItemStack {
    return BukkitAdapter.adapt(this)
}

fun AbstractLocation.toBukkit(): Location {
    return BukkitAdapter.adapt(this)
}

fun Location.toMythic(): AbstractLocation {
    return BukkitAdapter.adapt(this)
}

fun Entity.toMythic(): AbstractEntity {
    return BukkitAdapter.adapt(this)
}