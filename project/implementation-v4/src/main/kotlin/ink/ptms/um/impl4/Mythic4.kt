package ink.ptms.um.impl4

import ink.ptms.um.*
import io.lumine.xikage.mythicmobs.MythicMobs
import io.lumine.xikage.mythicmobs.io.MythicLineConfig
import io.lumine.xikage.mythicmobs.skills.SkillTrigger
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake

/**
 * universal-mythic ink.ptms.um.impl4.Mythic4
 *
 * @author 坏黑
 * @since 2022/7/12 13:47
 */
class Mythic4 : Mythic {

    val api: MythicMobs
        get() = MythicMobs.inst()

    val itemManager by lazy {
        api.itemManager
    }

    val mobManager by lazy {
        api.mobManager
    }

    val skillManager by lazy {
        api.skillManager
    }

    override val isLegacy = true

    override fun getItem(name: String): Item? {
        return Item4(itemManager.getItem(name)?.get() ?: return null)
    }

    override fun getItemId(itemStack: ItemStack): String? {
        return getItemList().firstOrNull { item: Item ->
            itemStack.isSimilar(
                item.generateItemStack(-1)
            )
        }?.internalName
    }

    override fun getItemStack(name: String): ItemStack? {
        return itemManager.getItemStack(name)
    }

    override fun getItemIDList(): List<String> {
        return itemManager.items.map { it.internalName }
    }

    override fun getItemList(): List<Item> {
        if (itemManager.items.size == Cache.item.size) {
            return Cache.item.values.toList()
        }
        return itemManager.items.map {
            Cache.item.getOrPut(it.internalName) { Item4(it) }
        }
    }

    override fun getMob(entity: Entity): Mob? {
        return mobManager.getMythicMobInstance(entity)?.let {
            return Cache.mob.getOrPut(it.uniqueId) { Mob4(it) }
        }
    }

    override fun getMobIDList(): List<String> {
        return mobManager.mobNames.toList()
    }

    override fun getMobType(name: String): MobType? {
        return mobManager.getMythicMob(name)?.let {
            Cache.mobType.getOrPut(it.internalName) { MobType4(it) }
        }
    }

    override fun getSkillTrigger(name: String): Skill.Trigger {
        return Skill4.Trigger(SkillTrigger.valueOf(name.uppercase()))
    }

    override fun getDefaultSkillTrigger(): Skill.Trigger {
        return Skill4.Trigger(SkillTrigger.DEFAULT)
    }

    override fun getSkillMechanic(skillLine: String): Skill? {
        return skillManager.getSkillMechanic(MythicLineConfig.unparseBlock(skillLine))?.let {
            Skill4(it)
        }
    }

    override fun castSkill(
        caster: Entity,
        skillName: String,
        trigger: Entity?,
        origin: Location,
        eTargets: Collection<Entity>,
        lTargets: Collection<Location>,
        power: Float,
    ): Boolean {
        return api.apiHelper.castSkill(caster, skillName, trigger, origin, eTargets, lTargets, power)
    }

    object Loader {

        @Awake(LifeCycle.ENABLE)
        fun setup() {
            if (kotlin.runCatching { Class.forName("io.lumine.xikage.mythicmobs.MythicMobs") }.getOrNull() != null) {
                Mythic.API = Mythic4()
            }
        }
    }
}