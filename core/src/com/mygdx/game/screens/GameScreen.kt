package com.mygdx.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.*
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType.*
import com.mygdx.game.*
import com.mygdx.game.data.MovementDirection
import com.mygdx.game.entities.Player
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.box2d.body
import ktx.box2d.box
import ktx.box2d.createWorld
import ktx.box2d.edge
import ktx.graphics.use

class GameScreen : KtxScreen {
    val batch = SpriteBatch()
    val textureAtlas = TextureAtlas(Gdx.files.internal("character.txt"))
    val world = createWorld()
    val camera = OrthographicCamera()

    val playerBody = world.body {
        type = DynamicBody
        position.set(PLAYER_STARTING_POSITION_X, PLAYER_STARTING_POSITION_Y)
        linearDamping = 5f
        box(width = PLAYER_WIDTH / 2, height = PLAYER_HEIGHT / 2) {
            friction = 0.3f
            density = 1f
            restitution = 0.1f
        }
    }
    val playerStartingAnimation = Animation(0f, textureAtlas.findRegion("run_down"))

    val player = Player(
            body = playerBody,
            speed = PLAYER_SPEED,
            animation = playerStartingAnimation)

    val edge = world.body {
        type = StaticBody
        edge(from = Vector2(0f, 0f), to = Vector2(0f, SCREEN_SIZE_Y * WORLD_TO_BOX))
        edge(from = Vector2(0f, 0f), to = Vector2(SCREEN_SIZE_X * WORLD_TO_BOX, 0f))
        edge(from = Vector2(SCREEN_SIZE_X * WORLD_TO_BOX, 0f), to = Vector2(SCREEN_SIZE_X * WORLD_TO_BOX, SCREEN_SIZE_Y * WORLD_TO_BOX))
        edge(from = Vector2(0f, SCREEN_SIZE_Y * WORLD_TO_BOX), to = Vector2(SCREEN_SIZE_X * WORLD_TO_BOX, SCREEN_SIZE_Y * WORLD_TO_BOX))
    }

    override fun show() {
        val playerInputListener = InputListener(player)
        Gdx.input.inputProcessor = playerInputListener
        camera.viewportHeight = Gdx.graphics.height * WORLD_TO_BOX
        camera.viewportWidth = Gdx.graphics.width * WORLD_TO_BOX
        camera.position.set(player.body.position.x / 2, player.body.position.y / 2, 0f)
        camera.update()
    }

    override fun render(delta: Float) {
        // Clearing the screen
        clearScreen(1f, 1f, 1f, 1f)
        camera.position.set(player.body.position.x / 2, player.body.position.y / 2, 0f)
        camera.update()
        animatePlayerMovement(player)
        batch.use {
            // Drawing the player
            with(player) {
                batch.draw(currentFrame,
                        body.position.x * BOX_TO_WORLD,
                        body.position.y * BOX_TO_WORLD,
                        PLAYER_WIDTH * BOX_TO_WORLD,
                        PLAYER_HEIGHT * BOX_TO_WORLD)
            }
        }

        // Update player position
        player.move()
        world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS)
    }

    private fun animatePlayerMovement(character: Player) {
        with(character) {
            if (inputs[MovementDirection.UP] == true)
                animation = Animation(PLAYER_FRAME_DURATION, textureAtlas.findRegions("run_up"))
            if (inputs[MovementDirection.DOWN] == true)
                animation = Animation(PLAYER_FRAME_DURATION, textureAtlas.findRegions("run_down"))
            if (inputs[MovementDirection.RIGHT] == true)
                animation = Animation(PLAYER_FRAME_DURATION, textureAtlas.findRegions("run_right"))
            if (inputs[MovementDirection.LEFT] == true)
                animation = Animation(PLAYER_FRAME_DURATION, textureAtlas.findRegions("run_left"))
            if (inputs[MovementDirection.UP] == true && inputs[MovementDirection.RIGHT] == true)
                animation = Animation(PLAYER_FRAME_DURATION, textureAtlas.findRegions("run_right_up"))
            if (inputs[MovementDirection.DOWN] == true && inputs[MovementDirection.RIGHT] == true)
                animation = Animation(PLAYER_FRAME_DURATION, textureAtlas.findRegions("run_right_down"))
            if (inputs[MovementDirection.DOWN] == true && inputs[MovementDirection.LEFT] == true)
                animation = Animation(PLAYER_FRAME_DURATION, textureAtlas.findRegions("run_left_down"))
            if (inputs[MovementDirection.UP] == true && inputs[MovementDirection.LEFT] == true)
                animation = Animation(PLAYER_FRAME_DURATION, textureAtlas.findRegions("run_left_up"))
            if (inputs.all { entry -> !entry.value })
                animation = Animation(0f, textureAtlas.findRegion("run_down"))

            animationFrameTime += Gdx.graphics.deltaTime
            currentFrame = animation.getKeyFrame(animationFrameTime, true)
        }
    }
}


