package ink.ptms.um.impl5

import ink.ptms.um.Item
import io.lumine.mythic.bukkit.utils.config.file.YamlConfiguration
import io.lumine.mythic.core.config.MythicConfigImpl
import io.lumine.mythic.core.items.MythicItem
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.inventory.ItemStack
import org.yaml.snakeyaml.Yaml

class Item5(obj: Any) : Item {

    val source = obj as MythicItem

    val root by lazy {
        val config = (source.config as MythicConfigImpl).fileConfiguration as YamlConfiguration
        val clazz = config.javaClass
        val yamlField = clazz.getDeclaredField("yaml")
        yamlField.isAccessible = true
        yamlField.get(config) as Yaml
    }

    override val internalName: String
        get() = source.internalName

    override val amount: Int
        get() = source.amount

    override val displayName: String?
        get() = source.displayName

    override fun getConfig(): Yaml {
        return root
    }

    override fun generateItemStack(amount: Int): ItemStack {
        return source.generateItemStack(amount).toBukkit()
    }

}