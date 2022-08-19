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

    override val isLegacy = true

    override fun getItem(name: String): Item? {
        return Item4(api.itemManager.getItem(name)?.get() ?: return null)
    }

    override fun getItemId(itemStack: ItemStack): String? {
        return api.itemManager.items.firstOrNull {
            itemStack.isSimilar(
                it.generateItemStack(itemStack.amount).toBukkit()
            )
        }?.internalName
    }

    override fun getItemStack(name: String): ItemStack? {
        return api.itemManager.getItemStack(name)
    }

    override fun getItemIDList(): List<String> {
        return api.itemManager.items.map { it.internalName }
    }

    override fun getItemList(): List<Item> {
        return api.itemManager.items.map { Item4(it) }
    }

    override fun getMob(entity: Entity): Mob? {
        return Mob4(MythicMobs.inst().mobManager.getMythicMobInstance(entity) ?: return null)
    }

    override fun getMobIDList(): List<String> {
        return api.mobManager.mobNames.toList()
    }

    override fun getMobType(name: String): MobType? {
        return MobType4(api.mobManager.getMythicMob(name) ?: return null)
    }

    override fun getSkillTrigger(name: String): Skill.Trigger {
        return Skill4.Trigger(SkillTrigger.valueOf(name.uppercase()))
    }

    override fun getSkillMechanic(skillLine: String): Skill? {
        return Skill4(
            MythicMobs.inst().skillManager.getSkillMechanic(MythicLineConfig.unparseBlock(skillLine)) ?: return null
        )
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
        return MythicMobs.inst().apiHelper.castSkill(caster, skillName, trigger, origin, eTargets, lTargets, power)
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