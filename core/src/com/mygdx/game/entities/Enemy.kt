package com.mygdx.game.entities

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.physics.box2d.Body

class Enemy(body: Body,
            speed: Float,
            animation: Animation<TextureAtlas.AtlasRegion>)
    : Character(body, speed, animation)