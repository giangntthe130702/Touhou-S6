package game.enemy;

import game.GameObject;
import game.physics.BoxCollider;
import tklibs.SpriteUtils;

public class EnemyBullet extends GameObject {

    public int damage;

    public EnemyBullet() {
        image = SpriteUtils.loadImage("assets/images/enemies/bullets/blue.png");
        velocity.set(0, -3);
        hitBox = new BoxCollider(this, 24, 24);
        damage = 1;
    }

    @Override
    public void run(){
        super.run();
        deactiveIfNeeded();
    }

    private void deactiveIfNeeded() {
        if(position.y > 600) {
            this.deactive();
        }
    }
}
