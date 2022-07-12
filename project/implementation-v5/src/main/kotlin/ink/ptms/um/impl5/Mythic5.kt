package ink.ptms.um.impl5

import ink.ptms.um.*
import io.lumine.mythic.api.MythicProvider
import io.lumine.mythic.api.skills.SkillTrigger
import io.lumine.mythic.core.config.MythicLineConfigImpl
import io.lumine.mythic.core.mobs.MobExecutor
import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.module.nms.getItemTag

/**
 * universal-mythic
 * ink.ptms.um.impl4.Mythic4
 *
 * @author 坏黑
 * @since 2022/7/12 13:47
 */
class Mythic5 : Mythic {

    val api = MythicProvider.get()

    override val isLegacy = false

    override fun getItem(name: String): Item? {
        return Item5(api.itemManager.getItem(name)?.get() ?: return null)
    }

    override fun getItemStack(name: String): ItemStack? {
        return api.itemManager?.getItem(name)?.get()?.generateItemStack(1)?.toBukkit()
    }

    override fun whatMythicItem(itemStack: ItemStack): String? {
        return itemStack.getItemTag()["MYTHIC_TYPE"]?.asString()
    }

    override fun getMob(entity: Entity): Mob? {
        return Mob5((MythicProvider.get().mobManager as MobExecutor).getMythicMobInstance(entity) ?: return null)
    }

    override fun getMobType(name: String): MobType? {
        return MobType5(api.mobManager.getMythicMob(name)?.get() ?: return null)
    }

    override fun getSkillTrigger(name: String): Skill.Trigger {
        return Skill5.Trigger(SkillTrigger.get(name))
    }

    override fun getSkillMechanic(skillLine: String): Skill? {
        return Skill5(api.skillManager.getMechanic(MythicLineConfigImpl.unparseBlock(skillLine)) ?: return null)
    }

    companion object {

        @Awake(LifeCycle.LOAD)
        fun setup() {
            if (kotlin.runCatching { Class.forName("io.lumine.mythic.api.MythicProvider") }.getOrNull() != null) {
                Mythic.API = Mythic5()
            }
        }
    }
}