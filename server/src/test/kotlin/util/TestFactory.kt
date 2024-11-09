package util

import auth.Session
import auth.UserConnection
import game.GameMode
import game.GameSettings
import java.util.*

fun makeSession(
    id: UUID = UUID.randomUUID(),
    name: String = "Alyssa",
    ip: String = "1.2.3.4",
    achievementCount: Int = 4,
    apiVersion: Int = OnlineConstants.API_VERSION,
) = Session(id, name, ip, achievementCount, apiVersion)

fun makeUserConnection(session: Session) = UserConnection(session.name)

fun makeGameSettings(
    mode: GameMode = GameMode.Entropy,
    jokerQuantity: Int = 0,
    jokerValue: Int = 0,
    includeMoons: Boolean = false,
    includeStars: Boolean = false,
    negativeJacks: Boolean = false,
    cardReveal: Boolean = false,
    illegalAllowed: Boolean = true,
) =
    GameSettings(
        mode,
        jokerQuantity,
        jokerValue,
        includeMoons,
        includeStars,
        negativeJacks,
        cardReveal,
        illegalAllowed,
    )
