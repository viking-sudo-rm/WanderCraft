package net.minecraft.src;

import java.util.Date;

public class CooldownMeter {

	private int clipSize;
	private double fireRate;
	private double coolDownTime;
	private double regenTime;
	private double lastTime = 0D;
	private double newTime;
	private int bulletsLeft;

	public CooldownMeter(int clipSize, double fireRate, double coolDownTime, double regenTime) {
		this.clipSize = clipSize;
		this.fireRate = fireRate;
		this.coolDownTime = coolDownTime;
		this.regenTime = regenTime;
		bulletsLeft = clipSize;
	}
	
	public boolean shoot() {
		update();
		System.out.println(bulletsLeft);
		System.out.println(clipSize);
		if (! isOverheated() && fireRateAllows()) {
			bulletsLeft --;
			lastTime = newTime;
			return true;
		}
		return false;
	}
	
	private boolean fireRateAllows() {
		return newTime - lastTime > fireRate;
	}
	
	public boolean isOverheated() {
		return bulletsLeft < 1;
	}
	
	private void update() {
		newTime = new Date().getTime();
		if (isOverheated()) {
			if (newTime - lastTime >= coolDownTime) {
				bulletsLeft = clipSize;
			}
		}
		else {
			if (newTime - lastTime >= regenTime) {
				bulletsLeft += (int) ((newTime - lastTime) / regenTime);
				if (bulletsLeft > clipSize) {
					bulletsLeft = clipSize;
				}
			}
		}
	}

}