package ink.ptms.um.impl4

import ink.ptms.um.Item
import io.lumine.xikage.mythicmobs.items.MythicItem
import io.lumine.xikage.mythicmobs.utils.config.file.YamlConfiguration
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.inventory.ItemStack
import org.yaml.snakeyaml.Yaml

class Item4(val source: MythicItem) : Item {

    override val internalName: String
        get() = source.internalName

    override val amount: Int
        get() = source.amount

    override val displayName: String?
        get() = source.displayName

    override val config: taboolib.library.configuration.ConfigurationSection
        get() = Mob4Configuration(source.config)

    override fun generateItemStack(amount: Int): ItemStack {
        return source.generateItemStack(amount).toBukkit()
    }
}