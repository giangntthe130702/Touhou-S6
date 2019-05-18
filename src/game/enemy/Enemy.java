package game.enemy;

import game.GameObject;
import game.physics.BoxCollider;
import game.player.Player;
import tklibs.SpriteUtils;

import javax.swing.*;

public class Enemy extends GameObject {
    public int hp;
    public int damage;
    public Enemy() {
        image = SpriteUtils.loadImage("assets/images/enemies/level0/pink/0.png");
        position.set(0, -50);
        velocity.set(0, 5);
        velocity.setAngle(Math.toRadians(25));
        hitBox = new BoxCollider(this, 28, 28);
        hp = 3;
        damage = 1;
    }


    private void checkPlayer() {
        Player player = GameObject.findIntersects(Player.class, hitBox);
        if(player != null) {
            player.takePlayerDamage(damage);
            this.deactive();
        }
    }

    int count = 0;
    public void enemeyFire(){
        count++;
        if(count > 60){
            EnemyBullet enemyBullet = new EnemyBullet();
            enemyBullet.position.set(this.position.x, this.position.y);
            enemyBullet.velocity.setAngle(Math.toRadians(90));

            EnemyBullet enemyBullet2 = new EnemyBullet();
            enemyBullet2.position.set(this.position.x - 10, this.position.y);
            enemyBullet2.velocity.setAngle(Math.toRadians(135));

            EnemyBullet enemyBullet3 = new EnemyBullet();
            enemyBullet3.position.set(this.position.x + 10, this.position.y);
            enemyBullet3.velocity.setAngle(Math.toRadians(45));



            count = 0;
        }

    }


    public void takeDamage(int damage) {
        hp -= damage;
        if(hp <= 0) {
            hp = 0;
            this.deactive();
        }
    }

    @Override
    public void run() {
        super.run(); // velocity
        if(this.onGoingRight() && this.outOfBoundRight()) {
            this.reverseVelocityX();
        }
        if(this.onGoingLeft() && this.outOfBoundLeft()) {
            this.reverseVelocityX();
        }
        this.deactiveIfNeeded();
        this.checkPlayer();
        this.enemeyFire();
    }

    @Override
    public void reset() {
        super.reset(); // active = true
        position.set(0, -50);
        velocity.set(0, 5);
        velocity.setAngle(Math.toRadians(25));
        hp = 3;
    }

    private void deactiveIfNeeded() {
        if(position.y > 600) {
            this.deactive();
        }
    }

    private void reverseVelocityX() {
        velocity.x = -velocity.x;
    }

    private boolean outOfBoundRight() {
        return position.x > 384 - 32;
    }

    private boolean outOfBoundLeft() {
        return position.x < 0;
    }

    private boolean onGoingRight() {
        return velocity.x > 0;
    }

    private boolean onGoingLeft() {
        return velocity.x < 0;
    }
}
