package com.mygdx.game.desktop

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.mygdx.game.ApplicationListener


object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = Lwjgl3ApplicationConfiguration()
        config.setTitle("My game")
        config.setWindowedMode(800, 800)
        Lwjgl3Application(ApplicationListener(), config)
    }
}