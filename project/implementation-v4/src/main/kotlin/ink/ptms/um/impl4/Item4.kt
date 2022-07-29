package ink.ptms.um.impl4

import ink.ptms.um.Item
import io.lumine.xikage.mythicmobs.items.MythicItem
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.inventory.ItemStack

class Item4(obj: Any) : Item {

    val source = obj as MythicItem

    val root by lazy {
        val yaml = YamlConfiguration()
        yaml.load(source.config.file)
        yaml.getConfigurationSection(source.internalName)!!
    }


    override fun getConfig(): ConfigurationSection {
        return root
    }

    override fun generateItemStack(amount: Int): ItemStack {
        return source.generateItemStack(amount).toBukkit()
    }

}