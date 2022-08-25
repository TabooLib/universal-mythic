package ink.ptms.um.impl4

import ink.ptms.um.Item
import io.lumine.xikage.mythicmobs.items.MythicItem
import io.lumine.xikage.mythicmobs.utils.config.file.YamlConfiguration
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.inventory.ItemStack

class Item4(obj: Any) : Item {

    val source = obj as MythicItem

    val root by lazy {
        val config = source.config.fileConfiguration as YamlConfiguration
        val clazz = config.javaClass
        val yamlField = clazz.getDeclaredField("yaml")
        yamlField.isAccessible = true
        yamlField.get(config) as org.bukkit.configuration.file.YamlConfiguration
    }

    override val internalName: String
        get() = source.internalName

    override val amount: Int
        get() = source.amount

    override val displayName: String?
        get() = source.displayName

    override fun getConfig(): ConfigurationSection {
        return root
    }

    override fun generateItemStack(amount: Int): ItemStack {
        return source.generateItemStack(amount).toBukkit()
    }

}