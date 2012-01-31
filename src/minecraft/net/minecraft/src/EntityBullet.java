package net.minecraft.src;

public class EntityBullet extends EntityArrow {

	public EntityBullet(World world,EntityLiving dude, float f) {
		super(world,dude,f);
		bullet = true;
		
	}
	
	public EntityBullet(World world, double x, double y, double z) {
		super(world,x,y,z);
		bullet = true;
	}
	
	public void onCollideWithPlayer(EntityPlayer entityplayer) {
		return;
	}
}