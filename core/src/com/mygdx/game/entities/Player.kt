package com.mygdx.game.entities

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.mygdx.game.data.MovementDirection
import com.mygdx.game.SCREEN_SIZE_X
import com.mygdx.game.SCREEN_SIZE_Y
import kotlin.math.abs
import kotlin.math.sqrt

class Player(body: Body,
             speed: Float,
             animation: Animation<TextureAtlas.AtlasRegion>)
    : Character(body, speed, animation) {

    val inputs = HashMap<MovementDirection, Boolean>()

    // Sets velocity according to input
    fun move() {
        inputs.forEach {
            when (it.key) {
                MovementDirection.UP -> if (it.value)
                    body.applyLinearImpulse(Vector2(0f, speed), body.position, true)
                MovementDirection.DOWN -> if (it.value)
                    body.applyLinearImpulse(Vector2(0f, -speed), body.position, true)
                MovementDirection.LEFT -> if (it.value)
                    body.applyLinearImpulse(Vector2(-speed, 0f), body.position, true)
                MovementDirection.RIGHT -> if (it.value)
                    body.applyLinearImpulse(Vector2(speed, 0f), body.position, true)
            }
        }
    }
}
