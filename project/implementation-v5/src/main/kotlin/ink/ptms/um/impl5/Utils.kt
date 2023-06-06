package ink.ptms.um.impl5

import io.lumine.mythic.api.adapters.AbstractEntity
import io.lumine.mythic.api.adapters.AbstractItemStack
import io.lumine.mythic.api.adapters.AbstractLocation
import io.lumine.mythic.bukkit.BukkitAdapter
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack

internal fun AbstractItemStack.toBukkit(): ItemStack {
    return BukkitAdapter.adapt(this)
}

internal fun AbstractLocation.toBukkit(): Location {
    return BukkitAdapter.adapt(this)
}

internal fun Location.toMythic(): AbstractLocation {
    return BukkitAdapter.adapt(this)
}

internal fun Entity.toMythic(): AbstractEntity {
    return BukkitAdapter.adapt(this)
}