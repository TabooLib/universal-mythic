package ink.ptms.um

import org.bukkit.Location

/**
 * universal-mythic
 * ink.ptms.um.MobType
 *
 * @author 坏黑
 * @since 2022/7/12 13:40
 */
interface MobType {

    /**
     * 生成到指定位置
     */
    fun spawn(location: Location, level: Double): Mob
}