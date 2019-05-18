package game.player;

import game.GameObject;
import game.KeyEventPress;
import game.Vector2D;
import game.physics.BoxCollider;
import tklibs.Mathx;
import tklibs.SpriteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends GameObject {

    public Player() {
        image = SpriteUtils.loadImage("assets/images/players/straight/0.png");
//        position = new Vector2D(200, 500);
        position.set(200, 500);
        hitBox = new BoxCollider(this, 28, 28);
    }


    public int hp;
    public void takePlayerDamage(int damage) {
        hp -= damage;
        if(hp <= 0) {
            hp = 0;
            JOptionPane.showMessageDialog(null, "You have lost");
            System.exit(0);
        }
    }

    int count = 0; // dem so khung hinh
    @Override
    public void run() { // 60 fps >> 60 vien dan dc tao ra 1s >> 3 vien duoc tao ra 1s
        this.move();
        this.limitPosition();
        this.fire();
    }

    private void fire() {
        count++;
        if(KeyEventPress.isFirePress && count > 20) {
//            PlayerBullet bullet = new PlayerBullet();
            PlayerBullet bullet = GameObject.recycle(PlayerBullet.class);
            bullet.position.set(this.position.x, this.position.y);
            bullet.velocity.setAngle(Math.toRadians(-90));

            PlayerBullet bullet2 = GameObject.recycle(PlayerBullet.class);
            bullet2.position.set(this.position.x - 10, this.position.y);
            bullet2.velocity.setAngle(Math.toRadians(-135));

            PlayerBullet bullet3 = GameObject.recycle(PlayerBullet.class);
            bullet3.position.set(this.position.x + 10, this.position.y);
            bullet3.velocity.setAngle(Math.toRadians(-45));

            count = 0;
        }
    }

    private void limitPosition() {
        position.x = Mathx.clamp(position.x, 0, 384 - 32);
        position.y = Mathx.clamp(position.y, 0, 600 - 48);
    }

    private void move() {
        if(KeyEventPress.isUpPress) {
            position.y-= 5;
        }
        if(KeyEventPress.isLeftPress) {
            position.x-=5;
        }
        if(KeyEventPress.isRightPress) {
            position.x+=5;
        }
        if(KeyEventPress.isDownPress) {
            position.y+=5;
        }
    }
}
