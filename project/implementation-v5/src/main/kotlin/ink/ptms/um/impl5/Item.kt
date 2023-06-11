package ink.ptms.um.impl5

import ink.ptms.um.Item
import io.lumine.mythic.core.items.MythicItem
import org.bukkit.inventory.ItemStack
import taboolib.library.configuration.ConfigurationSection

internal class Item(val source: MythicItem) : Item {

    override val internalName: String
        get() = source.internalName

    override val amount: Int
        get() = source.amount

    override val displayName: String?
        get() = source.displayName

    override val config: ConfigurationSection
        get() = MobConfiguration(source.config)

    override fun generateItemStack(amount: Int): ItemStack {
        return source.generateItemStack(amount).toBukkit()
    }
}