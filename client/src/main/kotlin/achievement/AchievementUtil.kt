package achievement

import javax.swing.ImageIcon
import preference.Setting
import screen.ScreenCache
import util.ClientGlobals.achievementStore
import utils.Achievement
import utils.CHATTY_THRESHOLD
import utils.VANITY_THRESHOLD

fun getAchievementsEarned() = Achievement.entries.count { it.isUnlocked() }

fun Achievement.isUnlocked(): Boolean {
    val setting = Setting(settingName, false)
    return achievementStore.get(setting)
}

fun Achievement.getIcon() = ImageIcon(javaClass.getResource("/achievements/$settingName.png"))

fun unlockAchievement(achievement: Achievement) {
    if (achievement.isUnlocked()) {
        return
    }

    achievementStore.save(Setting(achievement.settingName, false), true)

    ScreenCache.getAchievementsDialog().refresh(false)
    val icon = achievement.getIcon()

    val screen = ScreenCache.getMainScreen()
    screen.showAchievementPopup(achievement.title, icon)
}

fun updateAndUnlockVanity() {
    updateAchievementAndCounter(
        Achievement.Vanity,
        AchievementSetting.VanityCount,
        VANITY_THRESHOLD,
    )
}

fun updateAndUnlockChatty() {
    updateAchievementAndCounter(
        Achievement.Chatty,
        AchievementSetting.ChattyCount,
        CHATTY_THRESHOLD,
    )
}

private fun updateAchievementAndCounter(
    achievement: Achievement,
    counter: Setting<Int>,
    threshold: Int,
) {
    val count = achievementStore.get(counter) + 1
    achievementStore.save(counter, count)

    if (count >= threshold) {
        unlockAchievement(achievement)
    }
}