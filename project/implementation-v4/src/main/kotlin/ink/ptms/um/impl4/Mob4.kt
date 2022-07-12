package ink.ptms.um.impl4

import ink.ptms.um.Mob
import ink.ptms.um.MobType
import io.lumine.xikage.mythicmobs.mobs.ActiveMob
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import taboolib.common.reflect.Reflex.Companion.getProperty
import taboolib.common.reflect.Reflex.Companion.invokeMethod

/**
 * universal-mythic
 * ink.ptms.um.impl4.Mob4
 *
 * @author 坏黑
 * @since 2022/7/12 13:51
 */
class Mob4(obj: Any) : Mob {

    val source = obj as ActiveMob

    val root by lazy {
        val yaml = YamlConfiguration()
        yaml.loadFromString(source.type.config.getProperty<Any>("fc")!!.invokeMethod("saveToString")!!)
        yaml
    }

    override val id: String
        get() = source.type.internalName

    override val displayName: String
        get() = source.displayName

    override val type: MobType
        get() = TODO("Not yet implemented")

    override val entity: Entity
        get() = source.entity.bukkitEntity

    override val entityType: EntityType
        get() = entity.type

    override val level: Double
        get() = source.level

    override val stance: String
        get() = source.stance

    override val faction: String
        get() = source.faction

    override val config: ConfigurationSection
        get() = root
}