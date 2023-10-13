package ink.ptms.um.impl5

import ink.ptms.um.*
import ink.ptms.um.Skill
import io.lumine.mythic.api.MythicProvider
import io.lumine.mythic.api.skills.SkillTrigger
import io.lumine.mythic.bukkit.MythicBukkit
import io.lumine.mythic.core.config.MythicLineConfigImpl
import io.lumine.mythic.core.mobs.MobExecutor
import io.lumine.mythic.core.utils.MythicUtil
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.util.orNull
import taboolib.module.nms.getItemTag

/**
 * universal-mythic ink.ptms.um.impl4.Mythic4
 *
 * @author 坏黑
 * @since 2022/7/12 13:47
 */
internal class Mythic5 : Mythic {

    val api: MythicBukkit
        get() = MythicProvider.get() as MythicBukkit

    override val isLegacy = false

    override fun getItem(name: String): ink.ptms.um.Item? {
        return Item(api.itemManager.getItem(name).orNull() ?: return null)
    }

    override fun getItemId(itemStack: ItemStack): String? {
        return itemStack.getItemTag()["MYTHIC_TYPE"]?.asString()
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

    override fun getMobIDList(): List<String> {
        return api.mobManager.mobNames.toList()
    }

    override fun getMobType(name: String): ink.ptms.um.MobType? {
        return MobType(api.mobManager.getMythicMob(name).orNull() ?: return null)
    }

    override fun getSkillTrigger(name: String): Skill.Trigger {
        return Skill5.Trigger(SkillTrigger.get(name.uppercase()))
    }

    override fun getDefaultSkillTrigger(): Skill.Trigger {
        return Skill5.Trigger(SkillTrigger.get("DEFAULT"))
    }

    override fun getSkillMechanic(skillLine: String): Skill? {
        return Skill5(api.skillManager.getMechanic(MythicLineConfigImpl.unparseBlock(skillLine)) ?: return null)
    }

    override fun getTargetedEntity(player: Player): LivingEntity {
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

    object Loader {

        @Awake(LifeCycle.ENABLE)
        fun setup() {
            if (runCatching { Class.forName("io.lumine.mythic.api.MythicProvider") }.getOrNull() != null) {
                Mythic.API = Mythic5()
            }
        }
    }
}