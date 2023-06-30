package ink.ptms.um.impl4

import ink.ptms.um.Skill
import ink.ptms.um.skill.SkillConfig
import ink.ptms.um.skill.SkillMeta
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity
import io.lumine.xikage.mythicmobs.adapters.AbstractItemStack
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter
import io.lumine.xikage.mythicmobs.io.MythicLineConfig
import io.lumine.xikage.mythicmobs.skills.SkillCaster
import io.lumine.xikage.mythicmobs.skills.SkillMetadata
import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderDouble
import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderFloat
import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderInt
import io.lumine.xikage.mythicmobs.skills.placeholders.parsers.PlaceholderString
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack
import taboolib.library.reflex.Reflex.Companion.getProperty
import java.awt.Color
import java.util.HashMap

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
    return SkillCasterProxy4(this)
}

internal fun ink.ptms.um.skill.SkillCaster.toMythic(): SkillCaster {
    return (this as SkillCasterProxy4).origin
}

internal class SkillCasterProxy4(val origin: SkillCaster) : ink.ptms.um.skill.SkillCaster {

    override val entity: Entity
        get() = origin.entity.bukkitEntity

    override val location: Location
        get() = origin.location.toBukkit()

    override val level: Double
        get() = origin.level

    override val power: Float
        get() = origin.power

    override var globalCooldown: Int
        get() = origin.globalCooldown
        set(value) {
            origin.globalCooldown = value
        }
}

internal fun PlaceholderString.toUniversal(): ink.ptms.um.skill.data.PlaceholderString {
    return object : ink.ptms.um.skill.data.PlaceholderString {

        override fun get(): String {
            return this@toUniversal.get()
        }

        override fun get(entity: Entity): String {
            return this@toUniversal.get(entity.toMythic())
        }

        override fun get(caster: ink.ptms.um.skill.SkillCaster): String {
            return this@toUniversal.get(caster.toMythic())
        }
    }
}

internal fun PlaceholderInt.toUniversal(): ink.ptms.um.skill.data.PlaceholderInt {
    return object : ink.ptms.um.skill.data.PlaceholderInt {

        override fun get(): Int {
            return this@toUniversal.get()
        }

        override fun get(entity: Entity): Int {
            return this@toUniversal.get(entity.toMythic())
        }

        override fun get(caster: ink.ptms.um.skill.SkillCaster): Int {
            return this@toUniversal.get(caster.toMythic())
        }
    }
}

internal fun PlaceholderDouble.toUniversal(): ink.ptms.um.skill.data.PlaceholderDouble {
    return object : ink.ptms.um.skill.data.PlaceholderDouble {

        override fun get(): Double {
            return this@toUniversal.get()
        }

        override fun get(entity: Entity): Double {
            return this@toUniversal.get(entity.toMythic())
        }

        override fun get(caster: ink.ptms.um.skill.SkillCaster): Double {
            return this@toUniversal.get(caster.toMythic())
        }
    }
}

internal fun PlaceholderFloat.toUniversal(): ink.ptms.um.skill.data.PlaceholderFloat {
    return object : ink.ptms.um.skill.data.PlaceholderFloat {

        override fun get(): Float {
            return this@toUniversal.get()
        }

        override fun get(entity: Entity): Float {
            return this@toUniversal.get(entity.toMythic())
        }

        override fun get(caster: ink.ptms.um.skill.SkillCaster): Float {
            return this@toUniversal.get(caster.toMythic())
        }
    }
}

internal fun MythicLineConfig.toUniversal(): SkillConfig {
    return object : SkillConfig {

        override fun line(): String {
            return this@toUniversal.line
        }

        override fun size(): Int {
            return this@toUniversal.size()
        }

        override fun entrySet(): Set<Map.Entry<String, Any>> {
            return this@toUniversal.entrySet()
        }

        override fun getKey(): String {
            return this@toUniversal.key
        }

        override fun getKey(s: String): String {
            return this@toUniversal.key
        }

        override fun getBoolean(key: Array<String>, def: Boolean): Boolean {
            return this@toUniversal.getBoolean(key, def)
        }

        override fun getString(key: Array<String>, def: String): String {
            return this@toUniversal.getString(key, def)
        }

        override fun getPlaceholderString(key: Array<String>, def: String, vararg args: String): ink.ptms.um.skill.data.PlaceholderString {
            return this@toUniversal.getPlaceholderString(key, def, *args).toUniversal()
        }

        override fun getInt(key: Array<String>, def: Int): Int {
            return this@toUniversal.getInteger(key, def)
        }

        override fun getPlaceholderInt(key: Array<String>, def: Int, vararg args: String): ink.ptms.um.skill.data.PlaceholderInt {
            return this@toUniversal.getPlaceholderInteger(key, def, *args).toUniversal()
        }

        override fun getDouble(key: Array<String>, def: Double): Double {
            return this@toUniversal.getDouble(key, def)
        }

        override fun getPlaceholderDouble(key: Array<String>, def: Double, vararg args: String): ink.ptms.um.skill.data.PlaceholderDouble {
            return this@toUniversal.getPlaceholderDouble(key, def, *args).toUniversal()
        }

        override fun getFloat(key: Array<String>, def: Float): Float {
            return this@toUniversal.getFloat(key, def)
        }

        override fun getPlaceholderFloat(key: Array<String>, def: Float, vararg args: String): ink.ptms.um.skill.data.PlaceholderFloat {
            return this@toUniversal.getPlaceholderFloat(key, def, *args).toUniversal()
        }

        override fun getLong(key: Array<String>, def: Long): Long {
            return this@toUniversal.getLong(key, def)
        }

        override fun getColor(key: String, def: String): Color {
            return this@toUniversal.getColor(key, def)
        }

        override fun getColor(key: Array<String>, def: String): Color {
            return this@toUniversal.getColor(key, def)
        }
    }
}

internal fun SkillMetadata.toUniversal(): SkillMeta {
    return object : SkillMeta {

        val metadataMap = this@toUniversal.getProperty<HashMap<String, Any>>("metadata")!!

        override var caster: ink.ptms.um.skill.SkillCaster
            get() = this@toUniversal.caster.toUniversal()
            set(value) {
                this@toUniversal.caster = value.toMythic()
            }

        override var trigger: Entity
            get() = this@toUniversal.trigger.bukkitEntity
            set(value) {
                this@toUniversal.trigger = value.toMythic()
            }

        override var origin: Location
            get() = this@toUniversal.origin.toBukkit()
            set(value) {
                this@toUniversal.origin = value.toMythic()
            }

        override val cause: Skill.Trigger
            get() = Skill4.Trigger(this@toUniversal.cause)

        override var power: Float
            get() = this@toUniversal.power
            set(value) {
                this@toUniversal.power = value
            }

        override var isAsync: Boolean
            get() = this@toUniversal.isAsync
            set(value) {
                this@toUniversal.isAsync = value
            }

        override var entityTargets: Set<Entity>
            get() = this@toUniversal.entityTargets.map { it.bukkitEntity }.toSet()
            set(value) {
                this@toUniversal.setEntityTargets(value.map { it.toMythic() }.toHashSet())
            }

        override var locationTargets: Set<Location>
            get() = this@toUniversal.locationTargets.map { it.toBukkit() }.toSet()
            set(value) {
                this@toUniversal.setLocationTargets(value.map { it.toMythic() }.toHashSet())
            }

        override val metadata: Map<String, Any>
            get() = metadataMap

        override val parameters: Map<String, String>
            get() = emptyMap() // 该版本不支持

        override fun setMetadata(key: String, value: Any) {
            metadataMap[key] = value
        }

        override fun setParameter(key: String, value: String) {
            // 该版本不支持
        }

        override fun clone(): SkillMeta {
            return this@toUniversal.clone().toUniversal()
        }

        override fun deepClone(): SkillMeta {
            return this@toUniversal.deepClone().toUniversal()
        }
    }
}