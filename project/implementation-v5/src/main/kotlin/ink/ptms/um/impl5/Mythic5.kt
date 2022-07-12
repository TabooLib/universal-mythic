package ink.ptms.um.impl5

import ink.ptms.um.*
import io.lumine.mythic.api.MythicProvider
import io.lumine.mythic.core.mobs.MobExecutor
import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.inventory.ItemStack
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake

/**
 * universal-mythic
 * ink.ptms.um.impl4.Mythic4
 *
 * @author 坏黑
 * @since 2022/7/12 13:47
 */
class Mythic5 : Mythic {

    override val isLegacy = false

    override fun getItem(name: String): Item? {
        TODO("Not yet implemented")
    }

    override fun getItemStack(name: String): ItemStack? {
        TODO("Not yet implemented")
    }

    override fun getMob(entity: Entity): Mob? {
        return Mob5((MythicProvider.get().mobManager as MobExecutor).getMythicMobInstance(entity) ?: return null)
    }

    override fun getMobType(name: String): MobType? {
        TODO("Not yet implemented")
    }

    override fun getSkillTrigger(name: String): Skill.Trigger {
        TODO("Not yet implemented")
    }

    override fun getSkillMechanic(skillLine: String): Skill? {
        TODO("Not yet implemented")
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