package com.mygdx.game.entities

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.physics.box2d.Body

abstract class Character(
        val body : Body,
        val speed : Float,
        var animation: Animation<TextureAtlas.AtlasRegion>,
        var animationFrameTime: Float = 0f
) {
    var currentFrame: TextureAtlas.AtlasRegion = animation.getKeyFrame(animationFrameTime)
}