package com.pong.game;
import com.badlogic.gdx.math.Vector2;

public class TrailParticle {
    public Vector2 position;
    public Vector2 velocity;
    public float alpha;

    public TrailParticle(float x, float y, float vx, float vy, float initialAlpha) {
        position = new Vector2(x, y);
        velocity = new Vector2(vx, vy);
        alpha = initialAlpha;
    }
}
