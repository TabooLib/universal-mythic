package ink.ptms.um.impl5

import ink.ptms.um.Mob
import ink.ptms.um.MobType
import io.lumine.mythic.core.mobs.ActiveMob
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import taboolib.common.reflect.Reflex.Companion.getProperty
import taboolib.common.reflect.Reflex.Companion.invokeMethod

/**
 * universal-mythic
 * ink.ptms.um.impl5.Mob5
 *
 * @author 坏黑
 * @since 2022/7/12 13:52
 */
class Mob5(obj: Any) : Mob {

    val source = obj as ActiveMob

    val root by lazy {
        val yaml = YamlConfiguration()
        yaml.load(source.type.config.file)
        yaml.getConfigurationSection(source.type.internalName)!!
    }

    override val id: String
        get() = source.type.internalName

    override val displayName: String
        get() = source.displayName

    override val type: MobType
        get() = MobType5(source.type)

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