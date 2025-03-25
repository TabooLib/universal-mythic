package ink.ptms.um.impl5

import ink.ptms.um.Mythic
import ink.ptms.um.Skill
import io.lumine.mythic.api.MythicProvider
import io.lumine.mythic.api.mobs.GenericCaster
import io.lumine.mythic.api.mobs.MythicMob
import io.lumine.mythic.api.mobs.entities.MythicEntity
import io.lumine.mythic.api.skills.SkillTrigger
import io.lumine.mythic.bukkit.BukkitAdapter
import io.lumine.mythic.bukkit.MythicBukkit
import io.lumine.mythic.core.config.MythicConfigImpl
import io.lumine.mythic.core.config.MythicLineConfigImpl
import io.lumine.mythic.core.drops.DropMetadataImpl
import io.lumine.mythic.core.items.MythicItem
import io.lumine.mythic.core.mobs.MobExecutor
import io.lumine.mythic.core.utils.MythicUtil
import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.function.info
import taboolib.common.util.orNull
import taboolib.library.reflex.Reflex.Companion.getProperty
import taboolib.library.reflex.Reflex.Companion.invokeMethod
import taboolib.module.nms.getItemTag
import java.io.File
import java.util.*
import kotlin.jvm.optionals.getOrNull

/**
 * universal-mythic ink.ptms.um.impl5.Mythic5
 *
 * @author 坏黑
 * @since 2022/7/12 13:47
 */
internal class Mythic5 : Mythic {

    val api: MythicBukkit
        get() = MythicProvider.get() as MythicBukkit

    val mmList: MutableMap<String, MythicMob> by lazy {
        api.mobManager.getProperty<MutableMap<String, MythicMob>>("mmList")!!
    }

    override val isLegacy = false

    override fun getItem(name: String): ink.ptms.um.Item? {
        return Item(api.itemManager.getItem(name).orNull() ?: return null)
    }

    override fun getItemId(itemStack: ItemStack): String? {
        return MythicBukkit.inst().itemManager.getMythicTypeFromItem(itemStack)
    }

    override fun getItemStack(name: String, player: Player?): ItemStack? {
        val target = player?.let { BukkitAdapter.adapt(it) }
        val meta = target?.let { DropMetadataImpl(GenericCaster(target), target) }
        return meta?.let { api.itemManager?.getItem(name)?.get()?.generateItemStack(it, 1)?.toBukkit() }
            ?: api.itemManager?.getItem(name)?.get()?.generateItemStack(1)?.toBukkit()
    }

    override fun getItemStack(name: String): ItemStack? {
        return api.itemManager?.getItem(name)?.get()?.generateItemStack(1)?.toBukkit()
    }

    override fun getItemIDList(): List<String> {
        return api.itemManager.items.map { it.internalName }
    }

    override fun getItemList(): List<ink.ptms.um.Item> {
        return api.itemManager.items.map { Item(it) }
    }

    override fun getMob(entity: Entity): ink.ptms.um.Mob? {
        return Mob((MythicProvider.get().mobManager as MobExecutor).getMythicMobInstance(entity) ?: return null)
    }

    override fun getMob(uuid: UUID): ink.ptms.um.Mob? {
        return Mob((MythicProvider.get().mobManager as MobExecutor).getActiveMob(uuid).getOrNull() ?: return null)
    }

    override fun getMobIDList(): List<String> {
        return api.mobManager.mobNames.toList()
    }

    override fun getMobType(name: String): ink.ptms.um.MobType? {
        return MobType(api.mobManager.getMythicMob(name).orNull() ?: return null)
    }

    override fun getSkillTrigger(name: String): Skill.Trigger {
        val invokeMethod = Class.forName(SkillTrigger::class.java.name).invokeMethod<Any>("get", name.uppercase(), isStatic = true) ?: return getDefaultSkillTrigger()
        return Skill5.Trigger(invokeMethod)
    }

    override fun getDefaultSkillTrigger(): Skill.Trigger {
        val invokeMethod = Class.forName(SkillTrigger::class.java.name).invokeMethod<Any>("get", "DEFAULT", isStatic = true)
        return Skill5.Trigger(invokeMethod!!)
    }

    override fun getSkillMechanic(skillLine: String): Skill? {
        return Skill5(api.skillManager.getMechanic(MythicLineConfigImpl.unparseBlock(skillLine)) ?: return null)
    }

    override fun getTargetedEntity(player: Player): LivingEntity? {
        return MythicUtil.getTargetedEntity(player)
    }

    override fun castSkill(
        caster: Entity,
        skillName: String,
        trigger: Entity?,
        origin: Location,
        et: Collection<Entity>,
        lt: Collection<Location>,
        power: Float,
    ): Boolean {
        return api.apiHelper.castSkill(caster, skillName, trigger, origin, et, lt, power)
    }

    override fun registerItem(file: File, node: String): Boolean {
        return api.itemManager.registerItem(
            node,
            MythicItem(null, file, node, MythicConfigImpl(node, file, YamlConfiguration.loadConfiguration(file)))
        )
    }

    override fun unregisterItem(node: String): Boolean {
        return api.itemManager.getProperty<HashMap<String, MythicItem>>("items")!!.let {
            if (it.containsKey(node)) {
                it.remove(node)
                true
            } else false
        }
    }

    override fun registerMob(file: File, node: String): Boolean {
        return if (!mmList.containsKey(node)) {
            mmList[node] = io.lumine.mythic.core.mobs.MobType(
                MythicBukkit.inst().mobManager,
                null,
                file,
                node,
                MythicConfigImpl(node, file, YamlConfiguration.loadConfiguration(file))
            )
            true
        } else false
    }

    override fun unregisterMob(node: String): Boolean {
        return if (mmList.containsKey(node)) {
            mmList.remove(node)
            true
        } else false
    }

    object Loader {

        @Awake(LifeCycle.ENABLE)
        fun setup() {
            if (runCatching { Class.forName("io.lumine.mythic.api.MythicProvider") }.getOrNull() != null) {
                Mythic.API = Mythic5()
            }
        }
    }
}
