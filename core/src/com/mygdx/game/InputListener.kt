package com.mygdx.game

import com.badlogic.gdx.Input
import com.mygdx.game.data.MovementDirection
import com.mygdx.game.entities.Player
import ktx.app.KtxInputAdapter

class InputListener(private val player: Player) : KtxInputAdapter {
    override fun keyDown(keycode: Int): Boolean {
        with(player) {
            when (keycode) {
                Input.Keys.A -> inputs[MovementDirection.LEFT] = true
                Input.Keys.D -> inputs[MovementDirection.RIGHT] = true
                Input.Keys.W -> inputs[MovementDirection.UP] = true
                Input.Keys.S -> inputs[MovementDirection.DOWN] = true
            }
        }
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        with(player) {
            when (keycode) {
                Input.Keys.A -> inputs[MovementDirection.LEFT] = false
                Input.Keys.D -> inputs[MovementDirection.RIGHT] = false
                Input.Keys.W -> inputs[MovementDirection.UP] = false
                Input.Keys.S -> inputs[MovementDirection.DOWN] = false
            }
        }
        return false
    }
}