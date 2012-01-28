package net.minecraft.src;

public class EntityDeathStarCorePrimed extends EntityTNTPrimed {
	
	public EntityDeathStarCorePrimed(World world,double d,double d1,double d2) {
		super(world,d,d1,d2);
	}
	
	protected void explode() {
		float f = 40F;
		worldObj.createExplosion(null,posX,posY,posZ,f);
	}
	
}