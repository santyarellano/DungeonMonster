package Classes;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Character extends GameObj{
	// Instance variables
	protected int health, sprite_spd, jmpPow, walkSpd;
	protected boolean isAlive, jumping, hitting, inFloor;
	protected double hsp, vsp, grv;
	protected Tools tools;
	
	
	//----------------------- Constructor ----------------------
	public Character(int xPos, int yPos, int width, int height, int health, int sprite_spd, int jmpPow, boolean isAlive, 
			boolean isVisible, String id) {
		super(xPos, yPos, width, height, isVisible, id);
		this.health = health;
		this.sprite_spd = sprite_spd;
		this.isAlive = isAlive;
		this.jmpPow = jmpPow;
		walkSpd = 2;
		vsp = 0;
		grv = 0.2;
		inFloor = false;
		jumping = false;
		hitting = false;
		tools = new Tools();
	}
	//----------------------- Constructor ----------------------
	
	//---------------------- METHODS ---------------------------		
	public void die() {
		isAlive = false;
	}
	
	public void move() {
		vsp += grv;
		if (jmpPow != 0) {
			vsp = -jmpPow;
		}
		
		yPos += vsp;
		xPos += hsp;
	}
	
	public void jump() {
		jmpPow = 6;
	}
	
	public void stopJump() {
		jmpPow = 0;
	}
	
	public boolean getInFloor() {
		return inFloor;
	}
	
	public void setInFloor(boolean f) {
		inFloor = f;
	}
	
	public void getHit() {
		health -= 1;
	}
	
	public void getBullet() {
		health -= 3;
	}	

	public double getVsp() {
		return vsp;
	}
	
	public void setVsp(double vsp) {
		this.vsp = vsp;
	}
	
	public double getHsp() {
		return hsp;
	}
	
	public void setHsp(double hsp) {
		this.hsp = hsp;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getSprite_spd() {
		return sprite_spd;
	}

	public void setSprite_spd(int sprite_spd) {
		this.sprite_spd = sprite_spd;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	public boolean isHitting() {
		return hitting;
	}

	public void setHitting(boolean hitting) {
		this.hitting = hitting;
	}
	//---------------------- METHODS ---------------------------

}
