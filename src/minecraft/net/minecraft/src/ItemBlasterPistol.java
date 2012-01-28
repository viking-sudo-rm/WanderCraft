package net.minecraft.src;

import java.util.Date;

//finish auto fire code

public class ItemBlasterPistol extends Item {

	private boolean isOverheated = false;
	private int shotCount;
	private long lastTime;
	private long newTime;
	private long deltaTime;

	public ItemBlasterPistol(int i) {
		super(i);
		maxStackSize = 1;
		shotCount = 25;
	}
	
	public void fire(World world,EntityPlayer entityPlayer) {
		EntityBullet bullet = new EntityBullet(world,entityPlayer,2.0F);
		world.playSoundAtEntity(entityPlayer,"random.drr",1.0F,1.0F);
		if (! world.multiplayerWorld) {
			world.entityJoinedWorld(bullet);
		}
	}
	
	public boolean getHeat() {
		calculateHeat();
		return isOverheated;
	}
	
	private void calculateHeat() {
		newTime = new Date().getTime();
		if (isOverheated) {
			isOverheated = (newTime - lastTime <= 3000.0D);
			shotCount = 1;
		}
		else {
			if (newTime - lastTime > 1000.0D) {
				shotCount += (int) ((newTime - lastTime) / 200.0D);
				if (shotCount > 25 || shotCount < 0) {
					shotCount = 25;
				}
			}
		}
	}
	
	public ItemStack onItemRightClick(ItemStack itemstack,World world,EntityPlayer entityPlayer) {
		calculateHeat();
		if (! isOverheated) {
			if (shotCount < 1) {
				isOverheated = true;
			}
			lastTime = newTime;
			fire(world,entityPlayer);
			shotCount --;
		}
		return itemstack;
	}

}