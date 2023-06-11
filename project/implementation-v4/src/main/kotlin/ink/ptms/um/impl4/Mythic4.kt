package ink.ptms.um.impl4

import ink.ptms.um.*
import ink.ptms.um.Skill
import io.lumine.xikage.mythicmobs.MythicMobs
import io.lumine.xikage.mythicmobs.io.MythicLineConfig
import io.lumine.xikage.mythicmobs.items.ItemManager
import io.lumine.xikage.mythicmobs.mobs.MobManager
import io.lumine.xikage.mythicmobs.skills.SkillManager
import io.lumine.xikage.mythicmobs.skills.SkillTrigger
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.util.unsafeLazy

/**
 * universal-mythic ink.ptms.um.impl4.Mythic4
 *
 * @author 坏黑
 * @since 2022/7/12 13:47
 */
internal class Mythic4 : Mythic {

    val api: MythicMobs
        get() = MythicMobs.inst()

    val mobManager: MobManager by unsafeLazy { api.mobManager }

    val itemManager: ItemManager by unsafeLazy { api.itemManager }

    val skillManager: SkillManager by unsafeLazy { api.skillManager }

    override val isLegacy = true

    override fun getItem(name: String): ink.ptms.um.Item? {
        return Item(itemManager.getItem(name)?.get() ?: return null)
    }

    override fun getItemId(itemStack: ItemStack): String? {
        return getItemList().firstOrNull { item -> itemStack.isSimilar(item.generateItemStack(-1)) }?.internalName
    }

    override fun getItemStack(name: String): ItemStack? {
        return itemManager.getItemStack(name)
    }

    override fun getItemIDList(): List<String> {
        return itemManager.items.map { it.internalName }
    }

    override fun getItemList(): List<ink.ptms.um.Item> {
        return itemManager.items.map { Item(it) }
    }

    override fun getMob(entity: Entity): ink.ptms.um.Mob? {
        return Mob(mobManager.getMythicMobInstance(entity) ?: return null)
    }

    override fun getMobIDList(): List<String> {
        return mobManager.mobNames.toList()
    }

    override fun getMobType(name: String): ink.ptms.um.MobType? {
        return MobType(mobManager.getMythicMob(name) ?: return null)
    }

    override fun getSkillTrigger(name: String): Skill.Trigger {
        return Skill4.Trigger(SkillTrigger.valueOf(name.uppercase()))
    }

    override fun getDefaultSkillTrigger(): Skill.Trigger {
        return Skill4.Trigger(SkillTrigger.DEFAULT)
    }

    override fun getSkillMechanic(skillLine: String): Skill? {
        return Skill4(skillManager.getSkillMechanic(MythicLineConfig.unparseBlock(skillLine)) ?: return null)
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

    object Loader {

        @Awake(LifeCycle.ENABLE)
        fun setup() {
            if (runCatching { Class.forName("io.lumine.xikage.mythicmobs.MythicMobs") }.getOrNull() != null) {
                Mythic.API = Mythic4()
            }
        }
    }
}