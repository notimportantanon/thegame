package com.mygdx.game

import com.mygdx.game.screens.GameScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen

class ApplicationListener : KtxGame<KtxScreen>() {

    override fun create() {
        val gameScreen = GameScreen()
        currentScreen = gameScreen
        currentScreen.show()
    }
}