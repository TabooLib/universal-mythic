package ink.ptms.um.impl4

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity
import io.lumine.xikage.mythicmobs.adapters.AbstractItemStack
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter
import io.lumine.xikage.mythicmobs.skills.SkillCaster
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

internal fun SkillCaster.toUniversal(): ink.ptms.um.skill.SkillCaster {
    return object : ink.ptms.um.skill.SkillCaster {

        override val entity: Entity
            get() = this@toUniversal.entity.bukkitEntity

        override val location: Location
            get() = this@toUniversal.location.toBukkit()

        override val level: Double
            get() = this@toUniversal.level

        override val power: Float
            get() = this@toUniversal.power

        override var globalCooldown: Int
            get() = this@toUniversal.globalCooldown
            set(value) {
                this@toUniversal.globalCooldown = value
            }
    }
}