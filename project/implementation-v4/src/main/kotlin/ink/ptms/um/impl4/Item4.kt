package ink.ptms.um.impl4

import ink.ptms.um.Item
import io.lumine.xikage.mythicmobs.items.MythicItem
import org.bukkit.inventory.ItemStack

class Item4(val source: MythicItem) : Item {

    override val internalName: String
        get() = source.internalName

    override val amount: Int
        get() = source.amount

    override val displayName: String?
        get() = source.displayName

    override val config: taboolib.library.configuration.ConfigurationSection
        get() = Cache.itemConfiguration.getOrPut(internalName) { Mob4Configuration(source.config) }

    private val bukkitItem by lazy {
        source.generateItemStack(amount).toBukkit()
    }

    override fun generateItemStack(amount: Int): ItemStack {
        if (amount < 0) {
            return bukkitItem.clone()
        }
        return bukkitItem.clone().apply {
            this.amount = amount
        }
    }
}